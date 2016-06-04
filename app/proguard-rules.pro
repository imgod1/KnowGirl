# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }
# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# okhttputils
#-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}

# okhttputils
#-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.kk.imgod.knowgirl.model.** { *; }

##---------------End: proguard configuration for Gson  ----------


# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * implements java.io.Serializable {*;}

# ProGuard configurations for Bugtags
-keepattributes LineNumberTable,SourceFile

-keep class com.bugtags.library.** {*;}
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.bugtags.library.**
# End Bugtags

#umeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class [您的应用包名].R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
