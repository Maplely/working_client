##-------------------------------general Settting---------------------------------------------------
-repackageclasses qeqeqe
-obfuscationdictionary dic1.txt
-classobfuscationdictionary dic1.txt
-packageobfuscationdictionary dic.txt
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontnote
-dontoptimize
-ignorewarnings
-printmapping proguardMapping.txt
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# --------------------------------AndroidMainfest.xml keep------------------------------------------
#-keep public class * extends android.app.Activity
##-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keep public class * extends android.view.View
#-keep public class com.android.vending.licensing.ILicensingService
#
##----------------------------------default keep-----------------------------------------------------
#-keep class android.support.** {*;}
-keep class com.j256.ormlite.** {*;}
#-keepclasseswithmembers class * {
#    native <methods>;
#}
##-keepclassmembers class * extends android.app.ActivityActivity{
##    public void *(android.view.View);
##}
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#-keep public class * extends android.view.View{
#    *** get*();
#    void set*(***);
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    private static final java.io.ObjectStreamField[] serialPersistentFields;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}
#-keep class **.R$*{
#      *;
#}
#
#-keepclassmembers class * {
#    void *(**On*Event);
#}
##---------------------------------------------- webview---------------------------------------------
#-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
#   public *;
#}
#-keepclassmembers class * extends android.webkit.WebViewClient {
#    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
#    public boolean *(android.webkit.WebView, java.lang.String);
#}
#-keepclassmembers class * extends android.webkit.WebViewClient {
#    public void *(android.webkit.WebView, jav.lang.String);
#}
###---------------------------------------------source package---------------------------------------
#-dontwarn java.awt.**
#-dontwarn java.rmi.**
#-dontwarn org.**
#-dontwarn javax.**
#-dontwarn com.sun.org.apache.**
#-dontwarn java.nio.**
#-dontwarn java.lang.invoke.**
#-dontwarn sun.misc.**
#-dontwarn android.support.**
#-dontwarn android.**
#-dontwarn com.google.**
#
##-------------------tdclound-----------------------------------------------------------------------
#-dontwarn android.annotation
#-keep class * extends java.lang.annotation.Annotation
##混淆前后的映射
##-printmapping mapping.txt
#-dontwarn javax.annotation.**
#-dontwarn javax.inject.**
#-dontwarn android.support.design.**

# proguard.cfg

-keepattributes Signature
-dontwarn com.jcraft.jzlib.**
-keep class com.jcraft.jzlib.**  { *;}

-dontwarn sun.misc.**
-keep class sun.misc.** { *;}

-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *;}

-dontwarn sun.security.**
-keep class sun.security.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *;}

-dontwarn com.avos.**
-keep class com.avos.** { *;}

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

-dontwarn android.support.**

-dontwarn org.apache.**
-keep class org.apache.** { *;}

-dontwarn org.jivesoftware.smack.**
-keep class org.jivesoftware.smack.** { *;}

-dontwarn com.loopj.**
-keep class com.loopj.** { *;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-keep interface com.squareup.okhttp.** { *; }

-dontwarn okio.**

-dontwarn org.xbill.**
-keep class org.xbill.** { *;}

-keepattributes *Annotation*

