package org.webrtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.p2p.WifiP2pGroup;
import android.os.Build;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NetworkMonitorAutoDetect extends BroadcastReceiver {
    static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private final ConnectivityManager.NetworkCallback allNetworkCallback;
    private ConnectionType connectionType;
    /* access modifiers changed from: private */
    public ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    private boolean isRegistered;
    private final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    /* access modifiers changed from: private */
    public final Observer observer;
    private WifiDirectManagerDelegate wifiDirectManagerDelegate;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_UNKNOWN_CELLULAR,
        CONNECTION_BLUETOOTH,
        CONNECTION_NONE
    }

    public interface Observer {
        void onConnectionTypeChanged(ConnectionType connectionType);

        void onNetworkConnect(NetworkInformation networkInformation);

        void onNetworkDisconnect(long j);
    }

    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] address2) {
            this.address = address2;
        }
    }

    public static class NetworkInformation {
        public final long handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;

        public NetworkInformation(String name2, ConnectionType type2, long handle2, IPAddress[] addresses) {
            this.name = name2;
            this.type = type2;
            this.handle = handle2;
            this.ipAddresses = addresses;
        }
    }

    static class NetworkState {
        /* access modifiers changed from: private */
        public final boolean connected;
        private final int subtype;
        private final int type;

        public NetworkState(boolean connected2, int type2, int subtype2) {
            this.connected = connected2;
            this.type = type2;
            this.subtype = subtype2;
        }

        public boolean isConnected() {
            return this.connected;
        }

        public int getNetworkType() {
            return this.type;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }
    }

    @SuppressLint({"NewApi"})
    private class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        private SimpleNetworkCallback() {
        }

        public void onAvailable(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network becomes available: " + network.toString());
            onNetworkChanged(network);
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "capabilities changed: " + networkCapabilities.toString());
            onNetworkChanged(network);
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "link properties changed: " + linkProperties.toString());
            onNetworkChanged(network);
        }

        public void onLosing(Network network, int maxMsToLive) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is about to lose in " + maxMsToLive + "ms");
        }

        public void onLost(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(NetworkMonitorAutoDetect.networkToNetId(network));
        }

        private void onNetworkChanged(Network network) {
            NetworkInformation networkInformation = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            if (networkInformation != null) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkInformation);
            }
        }
    }

    static class ConnectivityManagerDelegate {
        private final ConnectivityManager connectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            this.connectivityManager = null;
        }

        /* access modifiers changed from: package-private */
        public NetworkState getNetworkState() {
            if (this.connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(this.connectivityManager.getActiveNetworkInfo());
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public NetworkState getNetworkState(Network network) {
            if (this.connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(this.connectivityManager.getNetworkInfo(network));
        }

        /* access modifiers changed from: package-private */
        public NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return new NetworkState(false, -1, -1);
            }
            return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype());
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public Network[] getAllNetworks() {
            if (this.connectivityManager == null) {
                return new Network[0];
            }
            return this.connectivityManager.getAllNetworks();
        }

        /* access modifiers changed from: package-private */
        public List<NetworkInformation> getActiveNetworkList() {
            if (!supportNetworkCallback()) {
                return null;
            }
            ArrayList<NetworkInformation> netInfoList = new ArrayList<>();
            for (Network network : getAllNetworks()) {
                NetworkInformation info = networkToInfo(network);
                if (info != null) {
                    netInfoList.add(info);
                }
            }
            return netInfoList;
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public long getDefaultNetId() {
            NetworkInfo networkInfo;
            if (!supportNetworkCallback()) {
                return -1;
            }
            NetworkInfo defaultNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
            if (defaultNetworkInfo == null) {
                return -1;
            }
            long defaultNetId = -1;
            for (Network network : getAllNetworks()) {
                if (hasInternetCapability(network) && (networkInfo = this.connectivityManager.getNetworkInfo(network)) != null && networkInfo.getType() == defaultNetworkInfo.getType()) {
                    if (defaultNetId != -1) {
                        throw new RuntimeException("Multiple connected networks of same type are not supported.");
                    }
                    defaultNetId = NetworkMonitorAutoDetect.networkToNetId(network);
                }
            }
            return defaultNetId;
        }

        /* access modifiers changed from: private */
        @SuppressLint({"NewApi"})
        public NetworkInformation networkToInfo(Network network) {
            LinkProperties linkProperties = this.connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Detected unknown network: " + network.toString());
                return null;
            } else if (linkProperties.getInterfaceName() == null) {
                Logging.w(NetworkMonitorAutoDetect.TAG, "Null interface name for network " + network.toString());
                return null;
            } else {
                NetworkState networkState = getNetworkState(network);
                if (networkState.connected && networkState.getNetworkType() == 17) {
                    networkState = getNetworkState();
                }
                ConnectionType connectionType = NetworkMonitorAutoDetect.getConnectionType(networkState);
                if (connectionType == ConnectionType.CONNECTION_NONE) {
                    Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " is disconnected");
                    return null;
                }
                if (connectionType == ConnectionType.CONNECTION_UNKNOWN || connectionType == ConnectionType.CONNECTION_UNKNOWN_CELLULAR) {
                    Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + network.toString() + " connection type is " + connectionType + " because it has type " + networkState.getNetworkType() + " and subtype " + networkState.getNetworkSubType());
                }
                return new NetworkInformation(linkProperties.getInterfaceName(), connectionType, NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties));
            }
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public boolean hasInternetCapability(Network network) {
            NetworkCapabilities capabilities;
            if (this.connectivityManager == null || (capabilities = this.connectivityManager.getNetworkCapabilities(network)) == null || !capabilities.hasCapability(12)) {
                return false;
            }
            return true;
        }

        @SuppressLint({"NewApi"})
        public void registerNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), networkCallback);
        }

        @SuppressLint({"NewApi"})
        public void requestMobileNetwork(ConnectivityManager.NetworkCallback networkCallback) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"NewApi"})
        public IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            IPAddress[] ipAddresses = new IPAddress[linkProperties.getLinkAddresses().size()];
            int i = 0;
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                ipAddresses[i] = new IPAddress(linkAddress.getAddress().getAddress());
                i++;
            }
            return ipAddresses;
        }

        @SuppressLint({"NewApi"})
        public void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        public boolean supportNetworkCallback() {
            return Build.VERSION.SDK_INT >= 21 && this.connectivityManager != null;
        }
    }

    static class WifiManagerDelegate {
        private final Context context;

        WifiManagerDelegate(Context context2) {
            this.context = context2;
        }

        WifiManagerDelegate() {
            this.context = null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
            r1 = (r2 = (android.net.wifi.WifiInfo) r0.getParcelableExtra("wifiInfo")).getSSID();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getWifiSSID() {
            /*
                r7 = this;
                android.content.Context r3 = r7.context
                r4 = 0
                android.content.IntentFilter r5 = new android.content.IntentFilter
                java.lang.String r6 = "android.net.wifi.STATE_CHANGE"
                r5.<init>(r6)
                android.content.Intent r0 = r3.registerReceiver(r4, r5)
                if (r0 == 0) goto L_0x0021
                java.lang.String r3 = "wifiInfo"
                android.os.Parcelable r2 = r0.getParcelableExtra(r3)
                android.net.wifi.WifiInfo r2 = (android.net.wifi.WifiInfo) r2
                if (r2 == 0) goto L_0x0021
                java.lang.String r1 = r2.getSSID()
                if (r1 == 0) goto L_0x0021
            L_0x0020:
                return r1
            L_0x0021:
                java.lang.String r1 = ""
                goto L_0x0020
            */
            throw new UnsupportedOperationException("Method not decompiled: org.webrtc.NetworkMonitorAutoDetect.WifiManagerDelegate.getWifiSSID():java.lang.String");
        }
    }

    static class WifiDirectManagerDelegate extends BroadcastReceiver {
        private static final int WIFI_P2P_NETWORK_HANDLE = 0;
        private final Context context;
        private final Observer observer;
        private NetworkInformation wifiP2pNetworkInfo = null;

        WifiDirectManagerDelegate(Observer observer2, Context context2) {
            this.context = context2;
            this.observer = observer2;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            context2.registerReceiver(this, intentFilter);
        }

        @SuppressLint({"InlinedApi"})
        public void onReceive(Context context2, Intent intent) {
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(intent.getAction())) {
                onWifiP2pGroupChange((WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo"));
            } else if ("android.net.wifi.p2p.STATE_CHANGED".equals(intent.getAction())) {
                onWifiP2pStateChange(intent.getIntExtra("wifi_p2p_state", 0));
            }
        }

        public void release() {
            this.context.unregisterReceiver(this);
        }

        public List<NetworkInformation> getActiveNetworkList() {
            if (this.wifiP2pNetworkInfo != null) {
                return Collections.singletonList(this.wifiP2pNetworkInfo);
            }
            return Collections.emptyList();
        }

        private void onWifiP2pGroupChange(WifiP2pGroup wifiP2pGroup) {
            if (wifiP2pGroup != null && wifiP2pGroup.getInterface() != null) {
                try {
                    List<InetAddress> interfaceAddresses = Collections.list(NetworkInterface.getByName(wifiP2pGroup.getInterface()).getInetAddresses());
                    IPAddress[] ipAddresses = new IPAddress[interfaceAddresses.size()];
                    for (int i = 0; i < interfaceAddresses.size(); i++) {
                        ipAddresses[i] = new IPAddress(interfaceAddresses.get(i).getAddress());
                    }
                    this.wifiP2pNetworkInfo = new NetworkInformation(wifiP2pGroup.getInterface(), ConnectionType.CONNECTION_WIFI, 0, ipAddresses);
                    this.observer.onNetworkConnect(this.wifiP2pNetworkInfo);
                } catch (SocketException e) {
                    Logging.e(NetworkMonitorAutoDetect.TAG, "Unable to get WifiP2p network interface", e);
                }
            }
        }

        private void onWifiP2pStateChange(int state) {
            if (state == 1) {
                this.wifiP2pNetworkInfo = null;
                this.observer.onNetworkDisconnect(0);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public NetworkMonitorAutoDetect(Observer observer2, Context context2) {
        this.observer = observer2;
        this.context = context2;
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(context2);
        this.wifiManagerDelegate = new WifiManagerDelegate(context2);
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        if (PeerConnectionFactory.fieldTrialsFindFullName("IncludeWifiDirect").equals("Enabled")) {
            this.wifiDirectManagerDelegate = new WifiDirectManagerDelegate(observer2, context2);
        }
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback()) {
            ConnectivityManager.NetworkCallback tempNetworkCallback = new ConnectivityManager.NetworkCallback();
            try {
                this.connectivityManagerDelegate.requestMobileNetwork(tempNetworkCallback);
            } catch (SecurityException e) {
                Logging.w(TAG, "Unable to obtain permission to request a cellular network.");
                tempNetworkCallback = null;
            }
            this.mobileNetworkCallback = tempNetworkCallback;
            this.allNetworkCallback = new SimpleNetworkCallback();
            this.connectivityManagerDelegate.registerNetworkCallback(this.allNetworkCallback);
            return;
        }
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }

    /* access modifiers changed from: package-private */
    public void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate delegate) {
        this.connectivityManagerDelegate = delegate;
    }

    /* access modifiers changed from: package-private */
    public void setWifiManagerDelegateForTests(WifiManagerDelegate delegate) {
        this.wifiManagerDelegate = delegate;
    }

    /* access modifiers changed from: package-private */
    public boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    /* access modifiers changed from: package-private */
    public List<NetworkInformation> getActiveNetworkList() {
        List<NetworkInformation> connectivityManagerList = this.connectivityManagerDelegate.getActiveNetworkList();
        if (connectivityManagerList == null) {
            return null;
        }
        ArrayList<NetworkInformation> result = new ArrayList<>(connectivityManagerList);
        if (this.wifiDirectManagerDelegate == null) {
            return result;
        }
        result.addAll(this.wifiDirectManagerDelegate.getActiveNetworkList());
        return result;
    }

    public void destroy() {
        if (this.allNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.allNetworkCallback);
        }
        if (this.mobileNetworkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(this.mobileNetworkCallback);
        }
        if (this.wifiDirectManagerDelegate != null) {
            this.wifiDirectManagerDelegate.release();
        }
        unregisterReceiver();
    }

    private void registerReceiver() {
        if (!this.isRegistered) {
            this.isRegistered = true;
            this.context.registerReceiver(this, this.intentFilter);
        }
    }

    private void unregisterReceiver() {
        if (this.isRegistered) {
            this.isRegistered = false;
            this.context.unregisterReceiver(this);
        }
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    public static ConnectionType getConnectionType(NetworkState networkState) {
        if (!networkState.isConnected()) {
            return ConnectionType.CONNECTION_NONE;
        }
        switch (networkState.getNetworkType()) {
            case 0:
                switch (networkState.getNetworkSubType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return ConnectionType.CONNECTION_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return ConnectionType.CONNECTION_3G;
                    case 13:
                        return ConnectionType.CONNECTION_4G;
                    default:
                        return ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
                }
            case 1:
                return ConnectionType.CONNECTION_WIFI;
            case 6:
                return ConnectionType.CONNECTION_4G;
            case 7:
                return ConnectionType.CONNECTION_BLUETOOTH;
            case 9:
                return ConnectionType.CONNECTION_ETHERNET;
            default:
                return ConnectionType.CONNECTION_UNKNOWN;
        }
    }

    private String getWifiSSID(NetworkState networkState) {
        if (getConnectionType(networkState) != ConnectionType.CONNECTION_WIFI) {
            return "";
        }
        return this.wifiManagerDelegate.getWifiSSID();
    }

    public void onReceive(Context context2, Intent intent) {
        NetworkState networkState = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(networkState);
        }
    }

    private void connectionTypeChanged(NetworkState networkState) {
        ConnectionType newConnectionType = getConnectionType(networkState);
        String newWifiSSID = getWifiSSID(networkState);
        if (newConnectionType != this.connectionType || !newWifiSSID.equals(this.wifiSSID)) {
            this.connectionType = newConnectionType;
            this.wifiSSID = newWifiSSID;
            Logging.d(TAG, "Network connectivity changed, type is: " + this.connectionType);
            this.observer.onConnectionTypeChanged(newConnectionType);
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public static long networkToNetId(Network network) {
        if (Build.VERSION.SDK_INT >= 23) {
            return network.getNetworkHandle();
        }
        return (long) Integer.parseInt(network.toString());
    }
}
