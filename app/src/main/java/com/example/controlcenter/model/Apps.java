package com.example.controlcenter.model;

import android.graphics.drawable.Drawable;

public class Apps {
    private String appName;
    private Drawable appIcon;
    private String packageName;

    public Apps(String appName, Drawable appIcon, String packageName) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.packageName = packageName;
    }

    public Apps(String appName, Drawable appIcon) {
        this.appName = appName;
        this.appIcon = appIcon;

    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getPackageName() {
        return packageName;
    }
}