package com.unicorn.store.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppInfo {
    private String javaVersion;
    private String appVersion;
    private String appSpringVersion;
    private String camelVersion;
    private Integer totalRoutes;
    private String uptime;
    private String metricsContextPath;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppSpringVersion() {
        return appSpringVersion;
    }

    public void setAppStringVersion(String appSpringVersion) {
        this.appSpringVersion = appSpringVersion;
    }

    public void setAppSpringVersion(String appSpringVersion) {
        this.appSpringVersion = appSpringVersion;
    }

    public String getCamelVersion() {
        return camelVersion;
    }

    public void setCamelVersion(String camelVersion) {
        this.camelVersion = camelVersion;
    }

    public Integer getTotalRoutes() {
        return totalRoutes;
    }

    public void setTotalRoutes(Integer totalRoutes) {
        this.totalRoutes = totalRoutes;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getMetricsContextPath() {
        return metricsContextPath;
    }

    public void setMetricsContextPath(String metricsContextPath) {
        this.metricsContextPath = metricsContextPath;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }
}