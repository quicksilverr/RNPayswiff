# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/Cellar/android-sdk/24.3.3/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:
-keep class com.ps.epay.transaction.** { *; } 
-keep class com.ps.epay.interfaces.IListener{*;} 
-keep class com.ps.epay.enum.** {*;}
-keep class com.ps.epay.model.** { *; }
-keep class com.ps.epay.listeners.PaymentPage{*;}
-keep class com.ps.epay.listeners.PaymentGatewayListener{*;}