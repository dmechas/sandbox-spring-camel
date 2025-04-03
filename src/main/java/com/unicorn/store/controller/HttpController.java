package com.unicorn.store.controller;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "camel.component.http", name = "enabled", havingValue = "true")
public class HttpController extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("servlet://hello")
                .routeId("helloRoute")
                .to("bean:myBean?method=createRoute");

        from("direct:hello")
                .routeId("helloRouteHandler")
                .setBody(constant("Hello from Camel direct!"))
                .log("HTTP Response: ${body}");
    }

}
