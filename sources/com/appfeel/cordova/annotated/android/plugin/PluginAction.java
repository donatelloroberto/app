package com.appfeel.cordova.annotated.android.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginAction {
    String actionName() default "";

    boolean isAutofinish() default true;

    ExecutionThread thread() default ExecutionThread.MAIN;
}
