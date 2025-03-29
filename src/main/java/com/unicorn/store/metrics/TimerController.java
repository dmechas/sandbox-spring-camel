package com.unicorn.store.metrics;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "camel.component.cenas", name="enabled", havingValue = "true")
public class TimerController  extends RouteBuilder {

    @Value("${camel.component.cenas.timer.interval}")
    private String interval;

    @Override
    public void configure() throws Exception {
        log.debug("Creating Cenas route with interval: {}", interval);

        from("timer:hello?period=" + interval)
            .routeId("cenasRoute")
            .to("bean:myBean?method=saySomething")
            .log("Cenas: ${body}");
    }
}
