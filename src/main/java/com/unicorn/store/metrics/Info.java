package com.unicorn.store.metrics;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.unicorn.store.schema.AppInfo;

@Component
@Endpoint(id = "app")
public class Info {

    private final CamelContext camelContext;

    public Info(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Value("${app.version}")
    private String appVersion;
    @Value("${app.spring.version}")
    private String appSpringVersion;
    @Value("${management.endpoints.web.base-path}")
    private String metricsContextPath;

    @ReadOperation
    public AppInfo getInfo() {
        AppInfo info = new AppInfo();
        info.setUptime(camelContext.getUptime().toString());
        info.setCamelVersion(camelContext.getVersion());
        info.setTotalRoutes(camelContext.getRoutesSize());
        info.setAppVersion(appVersion);
        info.setAppStringVersion(appSpringVersion);
        info.setMetricsContextPath(metricsContextPath);
        info.setJavaVersion(String.valueOf(Runtime.version()));
        return info;
    }
}