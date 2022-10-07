package top.niunaijun.bcore.core.env;

import android.content.ComponentName;

import java.util.ArrayList;
import java.util.List;

import top.niunaijun.bcore.BlackBoxCore;

public class AppSystemEnv {
    private static final List<String> sSystemPackages = new ArrayList<>();
    private static final List<String> sSuPackages = new ArrayList<>();
    private static final List<String> sXposedPackages = new ArrayList<>();
    private static final List<String> sPreInstallPackages = new ArrayList<>();

    static {
        sSystemPackages.add("android");
        sSystemPackages.add("com.google.android.webview");
        sSystemPackages.add("com.google.android.webview.dev");
        sSystemPackages.add("com.google.android.webview.beta");
        sSystemPackages.add("com.google.android.webview.canary");
        sSystemPackages.add("com.android.webview");
        sSystemPackages.add("com.android.camera");
        sSystemPackages.add("com.android.talkback");
        sSystemPackages.add("com.miui.gallery");

        // google Gboard
        sSystemPackages.add("com.google.android.inputmethod.latin");
        // sSystemPackages.add(BlackBoxCore.getHostPkg());

        // 华为
        sSystemPackages.add("com.huawei.webview");

        // miui
        sSystemPackages.add("com.miui.contentcatcher");
        sSystemPackages.add("com.miui.catcherpatch");

        // oppo
        sSystemPackages.add("com.coloros.safecenter");

        // su
        sSuPackages.add("com.noshufou.android.su");
        sSuPackages.add("com.noshufou.android.su.elite");
        sSuPackages.add("eu.chainfire.supersu");
        sSuPackages.add("com.koushikdutta.superuser");
        sSuPackages.add("com.thirdparty.superuser");
        sSuPackages.add("com.yellowes.su");

        sXposedPackages.add("de.robv.android.xposed.installer");
    }

    public static boolean isOpenPackage(String packageName) {
        return sSystemPackages.contains(packageName);
    }

    public static boolean isOpenPackage(ComponentName componentName) {
        return componentName != null && isOpenPackage(componentName.getPackageName());
    }

    public static boolean isBlackPackage(String packageName) {
        if (BlackBoxCore.get().isHideRoot() && sSuPackages.contains(packageName)) {
            return true;
        } else return BlackBoxCore.get().isHideXposed() && sXposedPackages.contains(packageName);
    }

    public static List<String> getPreInstallPackages() {
        return sPreInstallPackages;
    }
}
