package com.example.controlcenter.model;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "Apps")
public class Apps {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String appName;

    private String appIconCode;

    private String packageName;

    private boolean use;

    public Apps() {

    }

    public Apps(String appName, String appIconCode, String packageName, boolean use) {
        this.appName = appName;
        this.appIconCode = appIconCode;
        this.packageName = packageName;
        this.use = use;
    }

    public Apps(String appName, String appIconCode, String packageName) {
        this.appName = appName;
        this.appIconCode = appIconCode;
        this.packageName = packageName;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public Apps(String appName, Drawable appIcon) {
        this.appName = appName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apps)) return false;
        Apps apps = (Apps) o;
        return id == apps.id && Objects.equals(appName, apps.appName) && Objects.equals(packageName, apps.packageName);
    }

    public void setAppIconCode(String appIconCode) {
        this.appIconCode = appIconCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appName, packageName);
    }

    @Override
    public String toString() {
        return "Apps{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", use=" + use +
                '}';
    }

    public String getAppIconCode() {
        return appIconCode;
    }
}