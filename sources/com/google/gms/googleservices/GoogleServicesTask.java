package com.google.gms.googleservices;

import android.support.v4.app.NotificationCompat;
import com.adobe.phonegap.push.PushConstants;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.resources.TextResource;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

public class GoogleServicesTask extends DefaultTask {
    private static final Pattern GOOGLE_APP_ID_REGEX = Pattern.compile("(\\d+):(\\d+):(\\p{Alnum}+):(\\p{XDigit}+)");
    private static final String GOOGLE_APP_ID_VERSION = "1";
    private static final String OAUTH_CLIENT_TYPE_WEB = "3";
    private static final String STATUS_DISABLED = "1";
    private static final String STATUS_ENABLED = "2";
    @OutputDirectory
    public File intermediateDir;
    @Input
    public String packageNameXOR1;
    @Input
    public TextResource packageNameXOR2;
    @InputFile
    @Optional
    public File quickstartFile;
    @Input
    public String searchedLocation;

    @TaskAction
    public void action() throws IOException {
        if (!this.quickstartFile.isFile()) {
            throw new GradleException(String.format("File %s is missing. The Google Services Plugin cannot function without it. %n Searched Location: %s", new Object[]{this.quickstartFile.getName(), this.searchedLocation}));
        }
        getProject().getLogger().warn("Parsing json file: " + this.quickstartFile.getPath());
        deleteFolder(this.intermediateDir);
        if (!this.intermediateDir.mkdirs()) {
            throw new GradleException("Failed to create folder: " + this.intermediateDir);
        }
        JsonElement root = new JsonParser().parse((Reader) Files.newReader(this.quickstartFile, Charsets.UTF_8));
        if (!root.isJsonObject()) {
            throw new GradleException("Malformed root json");
        }
        JsonObject rootObject = root.getAsJsonObject();
        Map<String, String> resValues = new TreeMap<>();
        Map<String, Map<String, String>> resAttributes = new TreeMap<>();
        handleProjectNumberAndProjectId(rootObject, resValues);
        handleFirebaseUrl(rootObject, resValues);
        JsonObject clientObject = getClientForPackageName(rootObject);
        if (clientObject != null) {
            handleAnalytics(clientObject, resValues);
            handleMapsService(clientObject, resValues);
            handleGoogleApiKey(clientObject, resValues);
            handleGoogleAppId(clientObject, resValues);
            handleWebClientId(clientObject, resValues);
            File values = new File(this.intermediateDir, "values");
            if (values.exists() || values.mkdirs()) {
                Files.write(getValuesContent(resValues, resAttributes), new File(values, "values.xml"), Charsets.UTF_8);
                return;
            }
            throw new GradleException("Failed to create folder: " + values);
        }
        throw new GradleException("No matching client found for package name '" + getPackageName() + "'");
    }

    private void handleFirebaseUrl(JsonObject rootObject, Map<String, String> resValues) throws IOException {
        JsonObject projectInfo = rootObject.getAsJsonObject("project_info");
        if (projectInfo == null) {
            throw new GradleException("Missing project_info object");
        }
        JsonPrimitive firebaseUrl = projectInfo.getAsJsonPrimitive("firebase_url");
        if (firebaseUrl != null) {
            resValues.put("firebase_database_url", firebaseUrl.getAsString());
        }
    }

    private void handleProjectNumberAndProjectId(JsonObject rootObject, Map<String, String> resValues) throws IOException {
        JsonObject projectInfo = rootObject.getAsJsonObject("project_info");
        if (projectInfo == null) {
            throw new GradleException("Missing project_info object");
        }
        JsonPrimitive projectNumber = projectInfo.getAsJsonPrimitive("project_number");
        if (projectNumber == null) {
            throw new GradleException("Missing project_info/project_number object");
        }
        resValues.put(PushConstants.GCM_DEFAULT_SENDER_ID, projectNumber.getAsString());
        JsonPrimitive projectId = projectInfo.getAsJsonPrimitive("project_id");
        if (projectId == null) {
            throw new GradleException("Missing project_info/project_id object");
        }
        resValues.put("project_id", projectId.getAsString());
        JsonPrimitive bucketName = projectInfo.getAsJsonPrimitive("storage_bucket");
        if (bucketName != null) {
            resValues.put("google_storage_bucket", bucketName.getAsString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r7 = r6.getAsJsonObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleWebClientId(com.google.gson.JsonObject r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r10 = this;
            java.lang.String r8 = "oauth_client"
            com.google.gson.JsonArray r0 = r11.getAsJsonArray(r8)
            if (r0 == 0) goto L_0x0047
            int r4 = r0.size()
            r5 = 0
        L_0x000d:
            if (r5 >= r4) goto L_0x0047
            com.google.gson.JsonElement r6 = r0.get(r5)
            if (r6 == 0) goto L_0x001b
            boolean r8 = r6.isJsonObject()
            if (r8 != 0) goto L_0x001e
        L_0x001b:
            int r5 = r5 + 1
            goto L_0x000d
        L_0x001e:
            com.google.gson.JsonObject r7 = r6.getAsJsonObject()
            java.lang.String r8 = "client_type"
            com.google.gson.JsonPrimitive r2 = r7.getAsJsonPrimitive(r8)
            if (r2 == 0) goto L_0x001b
            java.lang.String r3 = r2.getAsString()
            java.lang.String r8 = "3"
            boolean r8 = r8.equals(r3)
            if (r8 == 0) goto L_0x001b
            java.lang.String r8 = "client_id"
            com.google.gson.JsonPrimitive r1 = r7.getAsJsonPrimitive(r8)
            if (r1 == 0) goto L_0x001b
            java.lang.String r8 = "default_web_client_id"
            java.lang.String r9 = r1.getAsString()
            r12.put(r8, r9)
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesTask.handleWebClientId(com.google.gson.JsonObject, java.util.Map):void");
    }

    private void handleAnalytics(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        JsonObject analyticsProp;
        JsonPrimitive trackingId;
        JsonObject analyticsService = getServiceByName(clientObject, "analytics_service");
        if (analyticsService != null && (analyticsProp = analyticsService.getAsJsonObject("analytics_property")) != null && (trackingId = analyticsProp.getAsJsonPrimitive("tracking_id")) != null) {
            resValues.put("ga_trackingId", trackingId.getAsString());
            File xml = new File(this.intermediateDir, "xml");
            if (xml.exists() || xml.mkdirs()) {
                Files.write(getGlobalTrackerContent(trackingId.getAsString()), new File(xml, "global_tracker.xml"), Charsets.UTF_8);
                return;
            }
            throw new GradleException("Failed to create folder: " + xml);
        }
    }

    private void handleMapsService(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        if (getServiceByName(clientObject, "maps_service") != null) {
            String apiKey = getAndroidApiKey(clientObject);
            if (apiKey != null) {
                resValues.put("google_maps_key", apiKey);
                return;
            }
            throw new GradleException("Missing api_key/current_key object");
        }
    }

    private void handleGoogleApiKey(JsonObject clientObject, Map<String, String> resValues) {
        String apiKey = getAndroidApiKey(clientObject);
        if (apiKey != null) {
            resValues.put("google_api_key", apiKey);
            resValues.put("google_crash_reporting_api_key", apiKey);
            return;
        }
        throw new GradleException("Missing api_key/current_key object");
    }

    private String getAndroidApiKey(JsonObject clientObject) {
        JsonPrimitive currentKey;
        JsonArray array = clientObject.getAsJsonArray("api_key");
        if (array != null) {
            int count = array.size();
            for (int i = 0; i < count; i++) {
                JsonElement apiKeyElement = array.get(i);
                if (apiKeyElement != null && apiKeyElement.isJsonObject() && (currentKey = apiKeyElement.getAsJsonObject().getAsJsonPrimitive("current_key")) != null) {
                    return currentKey.getAsString();
                }
            }
        }
        return null;
    }

    private static void findStringByName(JsonObject jsonObject, String stringName, Map<String, String> resValues) {
        JsonPrimitive id = jsonObject.getAsJsonPrimitive(stringName);
        if (id != null) {
            resValues.put(stringName, id.getAsString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r4 = r2.getAsJsonObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.gson.JsonObject getClientForPackageName(com.google.gson.JsonObject r11) {
        /*
            r10 = this;
            java.lang.String r8 = "client"
            com.google.gson.JsonArray r1 = r11.getAsJsonArray(r8)
            if (r1 == 0) goto L_0x0049
            int r6 = r1.size()
            r7 = 0
        L_0x000d:
            if (r7 >= r6) goto L_0x0049
            com.google.gson.JsonElement r2 = r1.get(r7)
            if (r2 == 0) goto L_0x001b
            boolean r8 = r2.isJsonObject()
            if (r8 != 0) goto L_0x001e
        L_0x001b:
            int r7 = r7 + 1
            goto L_0x000d
        L_0x001e:
            com.google.gson.JsonObject r4 = r2.getAsJsonObject()
            java.lang.String r8 = "client_info"
            com.google.gson.JsonObject r3 = r4.getAsJsonObject(r8)
            if (r3 == 0) goto L_0x001b
            java.lang.String r8 = "android_client_info"
            com.google.gson.JsonObject r0 = r3.getAsJsonObject(r8)
            if (r0 == 0) goto L_0x001b
            java.lang.String r8 = "package_name"
            com.google.gson.JsonPrimitive r5 = r0.getAsJsonPrimitive(r8)
            if (r5 == 0) goto L_0x001b
            java.lang.String r8 = r10.getPackageName()
            java.lang.String r9 = r5.getAsString()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x001b
        L_0x0048:
            return r4
        L_0x0049:
            r4 = 0
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesTask.getClientForPackageName(com.google.gson.JsonObject):com.google.gson.JsonObject");
    }

    private void handleGoogleAppId(JsonObject clientObject, Map<String, String> resValues) throws IOException {
        JsonObject clientInfo = clientObject.getAsJsonObject("client_info");
        if (clientInfo == null) {
            throw new GradleException("Client does not have client info");
        }
        JsonPrimitive googleAppId = clientInfo.getAsJsonPrimitive("mobilesdk_app_id");
        String googleAppIdStr = googleAppId == null ? null : googleAppId.getAsString();
        if (Strings.isNullOrEmpty(googleAppIdStr)) {
            throw new GradleException("Missing Google App Id. Please follow instructions on https://firebase.google.com/ to get a valid config file that contains a Google App Id");
        }
        Matcher matcher = GOOGLE_APP_ID_REGEX.matcher(googleAppIdStr);
        if (!matcher.matches()) {
            throw new GradleException("Unexpected format of Google App ID. Please follow instructions on https://firebase.google.com/ to get a config file that contains a valid Google App Id or update the plugin version if you believe your Google App Id [" + googleAppIdStr + "] is correct.");
        } else if (!"1".equals(matcher.group(1))) {
            throw new GradleException("Google App Id Version is incompatible with this plugin. Please update the plugin version.");
        } else {
            String platform = matcher.group(3);
            if (!platform.equals("android")) {
                throw new GradleException("Expect Google App Id for Android App, but get " + platform);
            }
            resValues.put(PushConstants.GOOGLE_APP_ID, googleAppIdStr);
        }
    }

    private JsonObject getServiceByName(JsonObject clientObject, String serviceName) {
        JsonObject services = clientObject.getAsJsonObject("services");
        if (services == null) {
            return null;
        }
        JsonObject service = services.getAsJsonObject(serviceName);
        if (service == null) {
            return null;
        }
        JsonPrimitive status = service.getAsJsonPrimitive(NotificationCompat.CATEGORY_STATUS);
        if (status == null) {
            return null;
        }
        String statusStr = status.getAsString();
        if ("1".equals(statusStr)) {
            return null;
        }
        if (STATUS_ENABLED.equals(statusStr)) {
            return service;
        }
        getLogger().warn(String.format("Status with value '%1$s' for service '%2$s' is unknown", new Object[]{statusStr, serviceName}));
        return null;
    }

    private static String getGlobalTrackerContent(String ga_trackingId) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <string name=\"ga_trackingId\" translatable=\"false\">" + ga_trackingId + "</string>\n</resources>\n";
    }

    private static String getValuesContent(Map<String, String> values, Map<String, Map<String, String>> attributes) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String name = entry.getKey();
            sb.append("    <string name=\"").append(name).append("\" translatable=\"false\"");
            if (attributes.containsKey(name)) {
                for (Map.Entry<String, String> attr : attributes.get(name).entrySet()) {
                    sb.append(" ").append(attr.getKey()).append("=\"").append(attr.getValue()).append("\"");
                }
            }
            sb.append(">").append(entry.getValue()).append("</string>\n");
        }
        sb.append("</resources>\n");
        return sb.toString();
    }

    private static void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else if (!file.delete()) {
                        throw new GradleException("Failed to delete: " + file);
                    }
                }
            }
            if (!folder.delete()) {
                throw new GradleException("Failed to delete: " + folder);
            }
        }
    }

    private String getPackageName() {
        if (this.packageNameXOR1 == null) {
            return this.packageNameXOR2.asString();
        }
        return this.packageNameXOR1;
    }
}
