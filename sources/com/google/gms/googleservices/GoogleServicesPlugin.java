package com.google.gms.googleservices;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.dependencies.DependencyAnalyzer;
import com.google.android.gms.dependencies.DependencyInspector;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.typehandling.ShortTypeHandling;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/* compiled from: GoogleServicesPlugin.groovy */
public class GoogleServicesPlugin implements Plugin<Project>, GroovyObject {
    private static /* synthetic */ SoftReference $callSiteArray = null;
    private static /* synthetic */ ClassInfo $staticClassInfo = null;
    private static /* synthetic */ ClassInfo $staticClassInfo$ = null;
    public static final Pattern FLAVOR_PATTERN = ((Pattern) ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.bitwiseNegate("(\\p{javaUpperCase}[^\\p{javaUpperCase}]*)"), Pattern.class));
    public static final String JSON_FILE_NAME = "google-services.json";
    public static final String MINIMUM_VERSION = "9.0.0";
    public static final String MODULE_CORE = "firebase-core";
    public static final String MODULE_GROUP = "com.google.android.gms";
    public static final String MODULE_GROUP_FIREBASE = "com.google.firebase";
    public static final String MODULE_VERSION = "11.4.2";
    public static final Pattern VARIANT_PATTERN = ((Pattern) ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.bitwiseNegate("(?:([^\\p{javaUpperCase}]+)((?:\\p{javaUpperCase}[^\\p{javaUpperCase}]*)*)/)?([^/]*)"), Pattern.class));
    public static transient /* synthetic */ boolean __$stMC;
    public static GoogleServicesPluginConfig config = ((GoogleServicesPluginConfig) ScriptBytecodeAdapter.castToType($getCallSiteArray()[115].callConstructor(GoogleServicesPluginConfig.class), GoogleServicesPluginConfig.class));
    private transient /* synthetic */ MetaClass metaClass = $getStaticMetaClass();

    /* compiled from: GoogleServicesPlugin.groovy */
    public static class GoogleServicesPluginConfig implements GroovyObject {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        private static /* synthetic */ ClassInfo $staticClassInfo$;
        public static transient /* synthetic */ boolean __$stMC;
        private boolean disableVersionCheck = false;
        private transient /* synthetic */ MetaClass metaClass = $getStaticMetaClass();

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            return new CallSiteArray(GoogleServicesPluginConfig.class, new String[0]);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        public GoogleServicesPluginConfig() {
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != GoogleServicesPluginConfig.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public boolean getDisableVersionCheck() {
            return this.disableVersionCheck;
        }

        public /* synthetic */ MetaClass getMetaClass() {
            MetaClass metaClass2 = this.metaClass;
            if (metaClass2 != null) {
                return metaClass2;
            }
            this.metaClass = $getStaticMetaClass();
            return this.metaClass;
        }

        public /* synthetic */ Object getProperty(String str) {
            return getMetaClass().getProperty(this, str);
        }

        public /* synthetic */ Object invokeMethod(String str, Object obj) {
            return getMetaClass().invokeMethod(this, str, obj);
        }

        public boolean isDisableVersionCheck() {
            return this.disableVersionCheck;
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            $getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            $getCallSiteArray();
            return ScriptBytecodeAdapter.getProperty(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            $getCallSiteArray();
            ScriptBytecodeAdapter.setProperty(val, (Class) null, GoogleServicesPlugin.class, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
        }

        public void setDisableVersionCheck(boolean z) {
            this.disableVersionCheck = z;
        }

        public /* synthetic */ void setMetaClass(MetaClass metaClass2) {
            this.metaClass = metaClass2;
        }

        public /* synthetic */ void setProperty(String str, Object obj) {
            getMetaClass().setProperty(this, str, obj);
        }
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] strArr = new String[116];
        $createCallSiteArray_1(strArr);
        return new CallSiteArray(GoogleServicesPlugin.class, strArr);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
        strArr[0] = "create";
        strArr[1] = "extensions";
        strArr[2] = "afterEvaluate";
        strArr[3] = "iterator";
        strArr[4] = "values";
        strArr[5] = "iterator";
        strArr[6] = "plugins";
        strArr[7] = "hasPlugin";
        strArr[8] = "plugins";
        strArr[9] = "setupPlugin";
        strArr[10] = "showWarningForPluginLocation";
        strArr[11] = "withId";
        strArr[12] = "plugins";
        strArr[13] = "withId";
        strArr[14] = "plugins";
        strArr[15] = "withId";
        strArr[16] = "plugins";
        strArr[17] = "warn";
        strArr[18] = "getLogger";
        strArr[19] = "APPLICATION";
        strArr[20] = "all";
        strArr[21] = "applicationVariants";
        strArr[22] = "android";
        strArr[23] = "LIBRARY";
        strArr[24] = "all";
        strArr[25] = "libraryVariants";
        strArr[26] = "android";
        strArr[27] = "FEATURE";
        strArr[28] = "all";
        strArr[29] = "featureVariants";
        strArr[30] = "android";
        strArr[31] = "MODEL_APPLICATION";
        strArr[32] = "all";
        strArr[33] = "applicationVariants";
        strArr[34] = "android";
        strArr[35] = "model";
        strArr[36] = "MODEL_LIBRARY";
        strArr[37] = "all";
        strArr[38] = "libraryVariants";
        strArr[39] = "android";
        strArr[40] = "model";
        strArr[41] = "getJsonLocations";
        strArr[42] = "dirName";
        strArr[43] = "lineSeparator";
        strArr[44] = "iterator";
        strArr[45] = "file";
        strArr[46] = "plus";
        strArr[47] = "plus";
        strArr[48] = "plus";
        strArr[49] = "plus";
        strArr[50] = "getPath";
        strArr[51] = "lineSeparator";
        strArr[52] = "isFile";
        strArr[53] = "file";
        strArr[54] = "plus";
        strArr[55] = "getPath";
        strArr[56] = "file";
        strArr[57] = "buildDir";
        strArr[58] = "dirName";
        strArr[59] = "create";
        strArr[60] = "tasks";
        strArr[61] = "capitalize";
        strArr[62] = "name";
        strArr[63] = "respondsTo";
        strArr[64] = "metaClass";
        strArr[65] = "hasProperty";
        strArr[66] = "metaClass";
        strArr[67] = "applicationIdTextResource";
        strArr[68] = "dependsOn";
        strArr[69] = "applicationIdTextResource";
        strArr[70] = "applicationId";
        strArr[71] = "registerResGeneratingTask";
        strArr[72] = "emptyList";
        strArr[73] = "<$constructor$>";
        strArr[74] = "matcher";
        strArr[75] = "find";
        strArr[76] = "group";
        strArr[77] = ProductAction.ACTION_ADD;
        strArr[78] = "toLowerCase";
        strArr[79] = "count";
        strArr[80] = "filter";
        strArr[81] = "codePoints";
        strArr[82] = "matcher";
        strArr[83] = "<$constructor$>";
        strArr[84] = "matches";
        strArr[85] = "warn";
        strArr[86] = "getLogger";
        strArr[87] = "plus";
        strArr[88] = ProductAction.ACTION_ADD;
        strArr[89] = "<$constructor$>";
        strArr[90] = "group";
        strArr[91] = ProductAction.ACTION_ADD;
        strArr[92] = "toLowerCase";
        strArr[93] = "group";
        strArr[94] = "addAll";
        strArr[95] = "splitVariantNames";
        strArr[96] = "group";
        strArr[97] = "group";
        strArr[98] = "group";
        strArr[99] = "group";
        strArr[100] = ProductAction.ACTION_ADD;
        strArr[101] = ProductAction.ACTION_ADD;
        strArr[102] = ProductAction.ACTION_ADD;
        strArr[103] = ProductAction.ACTION_ADD;
        strArr[104] = ProductAction.ACTION_ADD;
        strArr[105] = "capitalize";
        strArr[106] = ProductAction.ACTION_ADD;
        strArr[107] = "iterator";
        strArr[108] = "plus";
        strArr[109] = ProductAction.ACTION_ADD;
        strArr[110] = ProductAction.ACTION_ADD;
        strArr[111] = ProductAction.ACTION_ADD;
        strArr[112] = "capitalize";
        strArr[113] = "sort";
        strArr[114] = "unique";
        strArr[115] = "<$constructor$>";
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
            callSiteArray = $createCallSiteArray();
            $callSiteArray = new SoftReference(callSiteArray);
        }
        return callSiteArray.array;
    }

    public GoogleServicesPlugin() {
        $getCallSiteArray();
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ MetaClass $getStaticMetaClass() {
        if (getClass() != GoogleServicesPlugin.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            classInfo = ClassInfo.getClassInfo(getClass());
            $staticClassInfo = classInfo;
        }
        return classInfo.getMetaClass();
    }

    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass2 = this.metaClass;
        if (metaClass2 != null) {
            return metaClass2;
        }
        this.metaClass = $getStaticMetaClass();
        return this.metaClass;
    }

    public /* synthetic */ Object getProperty(String str) {
        return getMetaClass().getProperty(this, str);
    }

    public /* synthetic */ Object invokeMethod(String str, Object obj) {
        return getMetaClass().invokeMethod(this, str, obj);
    }

    public /* synthetic */ void setMetaClass(MetaClass metaClass2) {
        this.metaClass = metaClass2;
    }

    public /* synthetic */ void setProperty(String str, Object obj) {
        getMetaClass().setProperty(this, str, obj);
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    enum PluginType implements GroovyObject {
        ;
        
        public static final PluginType MAX_VALUE = null;
        public static final PluginType MIN_VALUE = null;
        private final Collection plugins;

        /* access modifiers changed from: public */
        PluginType(Collection plugins2) {
            $getCallSiteArray();
            this.metaClass = $getStaticMetaClass();
            this.plugins = (Collection) ScriptBytecodeAdapter.castToType(plugins2, Collection.class);
        }

        public Collection plugins() {
            $getCallSiteArray();
            return this.plugins;
        }
    }

    public void apply(Project project) {
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        config = (GoogleServicesPluginConfig) ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty((Project) project2.get()), "googleServices", GoogleServicesPluginConfig.class), GoogleServicesPluginConfig.class);
        $getCallSiteArray[2].call((Project) project2.get(), new _apply_closure1(this, this, project2));
        Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].call(PluginType.class)), Iterator.class);
        while (it.hasNext()) {
            PluginType pluginType = (PluginType) ShortTypeHandling.castToEnum(it.next(), PluginType.class);
            Iterator it2 = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call($getCallSiteArray[6].call(pluginType)), Iterator.class);
            while (true) {
                if (it2.hasNext()) {
                    if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty((Project) project2.get()), ShortTypeHandling.castToString(it2.next())))) {
                        $getCallSiteArray[9].callCurrent(this, (Project) project2.get(), pluginType);
                        return;
                    }
                }
            }
        }
        $getCallSiteArray[10].callCurrent(this, (Project) project2.get());
        $getCallSiteArray[11].call($getCallSiteArray[12].callGetProperty((Project) project2.get()), "android", new _apply_closure2(this, this, project2));
        $getCallSiteArray[13].call($getCallSiteArray[14].callGetProperty((Project) project2.get()), "android-library", new _apply_closure3(this, this, project2));
        $getCallSiteArray[15].call($getCallSiteArray[16].callGetProperty((Project) project2.get()), "android-feature", new _apply_closure4(this, this, project2));
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure1 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[9];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure1.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "disableVersionCheck";
            strArr[1] = "config";
            strArr[2] = "<$constructor$>";
            strArr[3] = "addListener";
            strArr[4] = "getGradle";
            strArr[5] = "<$constructor$>";
            strArr[6] = "getName";
            strArr[7] = "plus";
            strArr[8] = "plus";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure1(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(this)))) {
                return null;
            }
            return $getCallSiteArray[3].call($getCallSiteArray[4].call(this.project.get()), $getCallSiteArray[5].callConstructor(DependencyInspector.class, (DependencyAnalyzer) ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor(DependencyAnalyzer.class), DependencyAnalyzer.class), $getCallSiteArray[6].call(this.project.get()), $getCallSiteArray[7].call($getCallSiteArray[8].call("This error message came from the google-services Gradle plugin, report", " issues at https://github.com/google/play-services-plugins and disable by "), "adding \"googleServices { disableVersionCheck = false }\" to your build.gradle file.")));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure2 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure2.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "APPLICATION";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure2(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure3 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure3.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "LIBRARY";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure3(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure4 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure4.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "FEATURE";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure4(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    private void showWarningForPluginLocation(Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[17].call($getCallSiteArray[18].call(project), "Warning: Please apply google-services plugin at the bottom of the build file.");
    }

    private void setupPlugin(Project project, PluginType pluginType) {
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[19].callGetProperty(PluginType.class))) {
            $getCallSiteArray[20].call($getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGetProperty((Project) project2.get())), new _setupPlugin_closure5(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[23].callGetProperty(PluginType.class))) {
            $getCallSiteArray[24].call($getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGetProperty((Project) project2.get())), new _setupPlugin_closure6(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[27].callGetProperty(PluginType.class))) {
            $getCallSiteArray[28].call($getCallSiteArray[29].callGetProperty($getCallSiteArray[30].callGetProperty((Project) project2.get())), new _setupPlugin_closure7(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[31].callGetProperty(PluginType.class))) {
            $getCallSiteArray[32].call($getCallSiteArray[33].callGetProperty($getCallSiteArray[34].callGetProperty($getCallSiteArray[35].callGetProperty((Project) project2.get()))), new _setupPlugin_closure8(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[36].callGetProperty(PluginType.class))) {
            $getCallSiteArray[37].call($getCallSiteArray[38].callGetProperty($getCallSiteArray[39].callGetProperty($getCallSiteArray[40].callGetProperty((Project) project2.get()))), new _setupPlugin_closure9(this, this, project2));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure5 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure5.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure5(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure6 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure6.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure6(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure7 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure7.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure7(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure8 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure8.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure8(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure8.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure9 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure9.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure9(Object _outerInstance, Object _thisObject, Reference project2) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure9.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    private static void handleVariant(Project project, Object variant) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        File quickstartFile = null;
        String searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[43].call(System.class));
        Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[44].call((List) ScriptBytecodeAdapter.castToType($getCallSiteArray[41].callStatic(GoogleServicesPlugin.class, new GStringImpl(new Object[]{$getCallSiteArray[42].callGetProperty(variant)}, new String[]{"", ""}), project), List.class)), Iterator.class);
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File jsonFile = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[45].call(project, $getCallSiteArray[46].call($getCallSiteArray[47].call(ShortTypeHandling.castToString(it.next()), "/"), JSON_FILE_NAME)), File.class);
            searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[48].call($getCallSiteArray[49].call(searchedLocation, $getCallSiteArray[50].call(jsonFile)), $getCallSiteArray[51].call(System.class)));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[52].call(jsonFile))) {
                quickstartFile = jsonFile;
                break;
            }
        }
        if (ScriptBytecodeAdapter.compareEqual(quickstartFile, (Object) null)) {
            quickstartFile = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[53].call(project, JSON_FILE_NAME), File.class);
            searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[54].call(searchedLocation, $getCallSiteArray[55].call(quickstartFile)));
        }
        File outputDir = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[56].call(project, new GStringImpl(new Object[]{$getCallSiteArray[57].callGetProperty(project), $getCallSiteArray[58].callGetProperty(variant)}, new String[]{"", "/generated/res/google-services/", ""})), File.class);
        GoogleServicesTask task = (GoogleServicesTask) ScriptBytecodeAdapter.castToType($getCallSiteArray[59].call($getCallSiteArray[60].callGetProperty(project), new GStringImpl(new Object[]{$getCallSiteArray[61].call($getCallSiteArray[62].callGetProperty(variant))}, new String[]{"process", "GoogleServices"}), GoogleServicesTask.class), GoogleServicesTask.class);
        ScriptBytecodeAdapter.setProperty(quickstartFile, (Class) null, task, "quickstartFile");
        ScriptBytecodeAdapter.setProperty(outputDir, (Class) null, task, "intermediateDir");
        ScriptBytecodeAdapter.setProperty(searchedLocation, (Class) null, task, "searchedLocation");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[63].call($getCallSiteArray[64].callGetProperty(variant), variant, "applicationIdTextResource")) || DefaultTypeTransformation.booleanUnbox($getCallSiteArray[65].call($getCallSiteArray[66].callGetProperty(variant), variant, "applicationIdTextResource"))) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[67].callGetProperty(variant), (Class) null, task, "packageNameXOR2");
            $getCallSiteArray[68].call(task, $getCallSiteArray[69].callGetProperty(variant));
        } else {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[70].callGetProperty(variant), (Class) null, task, "packageNameXOR1");
        }
        $getCallSiteArray[71].call(variant, task, outputDir);
    }

    private static List<String> splitVariantNames(String variant) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(variant, (Object) null)) {
            return (List) ScriptBytecodeAdapter.castToType($getCallSiteArray[72].call(Collections.class), List.class);
        }
        List flavors = (List) ScriptBytecodeAdapter.castToType($getCallSiteArray[73].callConstructor(ArrayList.class), List.class);
        Matcher flavorMatcher = (Matcher) ScriptBytecodeAdapter.castToType($getCallSiteArray[74].call(FLAVOR_PATTERN, variant), Matcher.class);
        while (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[75].call(flavorMatcher))) {
            String match = ShortTypeHandling.castToString($getCallSiteArray[76].call(flavorMatcher, 1));
            if (ScriptBytecodeAdapter.compareNotEqual(match, (Object) null)) {
                $getCallSiteArray[77].call(flavors, $getCallSiteArray[78].call(match));
            }
        }
        return flavors;
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _countSlashes_closure10 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            return new CallSiteArray(_countSlashes_closure10.class, new String[0]);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _countSlashes_closure10(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _countSlashes_closure10.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall(Object x) {
            $getCallSiteArray();
            return Boolean.valueOf(ScriptBytecodeAdapter.compareEqual(x, "/"));
        }
    }

    private static long countSlashes(String input) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.longUnbox($getCallSiteArray[79].call($getCallSiteArray[80].call($getCallSiteArray[81].call(input), new _countSlashes_closure10(GoogleServicesPlugin.class, GoogleServicesPlugin.class))));
    }

    public static List<String> getJsonLocations(String variantDirname, Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        Matcher variantMatcher = (Matcher) ScriptBytecodeAdapter.castToType($getCallSiteArray[82].call(VARIANT_PATTERN, variantDirname), Matcher.class);
        List fileLocations = (List) ScriptBytecodeAdapter.castToType($getCallSiteArray[83].callConstructor(ArrayList.class), List.class);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[84].call(variantMatcher))) {
            $getCallSiteArray[85].call($getCallSiteArray[86].call(project), $getCallSiteArray[87].call(new GStringImpl(new Object[]{variantDirname}, new String[]{"", " failed to parse into flavors. Please start "}), "all flavors with a lowercase character"));
            $getCallSiteArray[88].call(fileLocations, new GStringImpl(new Object[]{variantDirname}, new String[]{"src/", ""}));
        } else {
            List flavorNames = (List) ScriptBytecodeAdapter.castToType($getCallSiteArray[89].callConstructor(ArrayList.class), List.class);
            if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[90].call(variantMatcher, 1), (Object) null)) {
                $getCallSiteArray[91].call(flavorNames, $getCallSiteArray[92].call($getCallSiteArray[93].call(variantMatcher, 1)));
            }
            $getCallSiteArray[94].call(flavorNames, $getCallSiteArray[95].callStatic(GoogleServicesPlugin.class, $getCallSiteArray[96].call(variantMatcher, 2)));
            String buildType = ShortTypeHandling.castToString($getCallSiteArray[97].call(variantMatcher, 3));
            String flavorName = ShortTypeHandling.castToString(new GStringImpl(new Object[]{$getCallSiteArray[98].call(variantMatcher, 1), $getCallSiteArray[99].call(variantMatcher, 2)}, new String[]{"", "", ""}));
            $getCallSiteArray[100].call(fileLocations, new GStringImpl(new Object[]{flavorName, buildType}, new String[]{"src/", "/", ""}));
            $getCallSiteArray[101].call(fileLocations, new GStringImpl(new Object[]{buildType, flavorName}, new String[]{"src/", "/", ""}));
            $getCallSiteArray[102].call(fileLocations, new GStringImpl(new Object[]{flavorName}, new String[]{"src/", ""}));
            $getCallSiteArray[103].call(fileLocations, new GStringImpl(new Object[]{buildType}, new String[]{"src/", ""}));
            $getCallSiteArray[104].call(fileLocations, new GStringImpl(new Object[]{flavorName, $getCallSiteArray[105].call(buildType)}, new String[]{"src/", "", ""}));
            $getCallSiteArray[106].call(fileLocations, new GStringImpl(new Object[]{buildType}, new String[]{"src/", ""}));
            String fileLocation = "src";
            Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[107].call(flavorNames), Iterator.class);
            while (it.hasNext()) {
                fileLocation = ShortTypeHandling.castToString($getCallSiteArray[108].call(fileLocation, new GStringImpl(new Object[]{ShortTypeHandling.castToString(it.next())}, new String[]{"/", ""})));
                $getCallSiteArray[109].call(fileLocations, fileLocation);
                $getCallSiteArray[110].call(fileLocations, new GStringImpl(new Object[]{fileLocation, buildType}, new String[]{"", "/", ""}));
                $getCallSiteArray[111].call(fileLocations, new GStringImpl(new Object[]{fileLocation, $getCallSiteArray[112].call(buildType)}, new String[]{"", "", ""}));
            }
            $getCallSiteArray[113].call($getCallSiteArray[114].call(fileLocations), new _getJsonLocations_closure11(GoogleServicesPlugin.class, GoogleServicesPlugin.class));
        }
        return fileLocations;
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _getJsonLocations_closure11 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[3];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_getJsonLocations_closure11.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "countSlashes";
            strArr[1] = "countSlashes";
            strArr[2] = "doCall";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray) $callSiteArray.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _getJsonLocations_closure11(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _getJsonLocations_closure11.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object call(Object a, Object b) {
            return $getCallSiteArray()[2].callCurrent(this, a, b);
        }

        public Object doCall(Object a, Object b) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return ScriptBytecodeAdapter.compareTo($getCallSiteArray[0].callCurrent(this, b), $getCallSiteArray[1].callCurrent(this, a));
        }
    }
}
