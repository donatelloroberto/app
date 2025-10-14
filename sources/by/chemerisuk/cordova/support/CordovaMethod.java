package by.chemerisuk.cordova.support;

import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CordovaMethod {
    String action() default "";

    ReflectiveCordovaPlugin.ExecutionThread value() default ReflectiveCordovaPlugin.ExecutionThread.MAIN;
}
