package com.amazon.device.ads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class BridgeSelector {
    private static BridgeSelector instance = new BridgeSelector();
    private HashMap<AAXCreative, HashSet<AdSDKBridgeFactory>> bridgesForCT;
    private HashMap<String, HashSet<AdSDKBridgeFactory>> bridgesForPattern;
    private HashMap<String, HashSet<AdSDKBridgeFactory>> bridgesForResourcePattern;
    private HashMap<String, Pattern> patterns;

    public static BridgeSelector getInstance() {
        return instance;
    }

    BridgeSelector() {
        initialize();
    }

    /* access modifiers changed from: package-private */
    public void initialize() {
        this.bridgesForCT = new HashMap<>();
        this.bridgesForPattern = new HashMap<>();
        this.patterns = new HashMap<>();
        this.bridgesForResourcePattern = new HashMap<>();
        addBridgeFactoryForScript("amazon.js", new AmazonAdSDKBridgeFactory());
        MraidAdSDKBridgeFactory mraidBridgeFactory = new MraidAdSDKBridgeFactory();
        addBridgeFactory(AAXCreative.MRAID1, mraidBridgeFactory);
        addBridgeFactory(AAXCreative.MRAID2, mraidBridgeFactory);
        addBridgeFactory(AAXCreative.INTERSTITIAL, mraidBridgeFactory);
        addBridgeFactoryForScript("mraid.js", mraidBridgeFactory);
    }

    public void addBridgeFactory(AAXCreative creativeType, AdSDKBridgeFactory bridgeFactory) {
        HashSet<AdSDKBridgeFactory> bridges = this.bridgesForCT.get(creativeType);
        if (bridges == null) {
            bridges = new HashSet<>();
            this.bridgesForCT.put(creativeType, bridges);
        }
        bridges.add(bridgeFactory);
    }

    public void addBridgeFactoryForScript(String filename, AdSDKBridgeFactory bridgeFactory) {
        addBridgeFactoryForHtmlScriptTag(filename, bridgeFactory);
        addBridgeFactoryForResourceLoad(filename, bridgeFactory);
    }

    public void addBridgeFactoryForHtmlScriptTag(String filename, AdSDKBridgeFactory bridgeFactory) {
        String regex = String.format("<[Ss][Cc][Rr][Ii][Pp][Tt](\\s[^>]*\\s|\\s)[Ss][Rr][Cc]\\s*=\\s*[\"']%s[\"']", new Object[]{filename});
        HashSet<AdSDKBridgeFactory> bridges = this.bridgesForPattern.get(regex);
        if (bridges == null) {
            bridges = new HashSet<>();
            this.bridgesForPattern.put(regex, bridges);
        }
        bridges.add(bridgeFactory);
    }

    public void addBridgeFactoryForResourceLoad(String filename, AdSDKBridgeFactory bridgeFactory) {
        String regex = String.format(".*\\W%s$|^%s$", new Object[]{filename, filename});
        HashSet<AdSDKBridgeFactory> bridges = this.bridgesForResourcePattern.get(regex);
        if (bridges == null) {
            bridges = new HashSet<>();
            this.bridgesForResourcePattern.put(regex, bridges);
        }
        bridges.add(bridgeFactory);
    }

    public Set<AdSDKBridgeFactory> getBridgeFactories(AAXCreative creativeType) {
        return this.bridgesForCT.get(creativeType);
    }

    public Set<AdSDKBridgeFactory> getBridgeFactories(String input) {
        Set<AdSDKBridgeFactory> bridges = new HashSet<>();
        for (String regex : this.bridgesForPattern.keySet()) {
            if (getPattern(regex).matcher(input).find()) {
                bridges.addAll(this.bridgesForPattern.get(regex));
            }
        }
        return bridges;
    }

    public Set<AdSDKBridgeFactory> getBridgeFactoriesForResourceLoad(String input) {
        Set<AdSDKBridgeFactory> bridges = new HashSet<>();
        for (String regex : this.bridgesForResourcePattern.keySet()) {
            if (getPattern(regex).matcher(input).find()) {
                bridges.addAll(this.bridgesForResourcePattern.get(regex));
            }
        }
        return bridges;
    }

    private Pattern getPattern(String regex) {
        Pattern pattern = this.patterns.get(regex);
        if (pattern != null) {
            return pattern;
        }
        Pattern pattern2 = Pattern.compile(regex);
        this.patterns.put(regex, pattern2);
        return pattern2;
    }
}
