package com.unicorn.store.service;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class MyBean {
    private String routeId = "afterStartRoute";

    public String saySomething(CamelContext camelContext) throws Exception {
        return "Hello from MyBean!";
    }

    public String createRoute(CamelContext camelContext) throws Exception {
        camelContext.getRouteController().stopRoute(routeId);
        camelContext.removeRoute(routeId);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("servlet://bar")
                        .routeId(routeId)
                        .to("direct:hello");
            }
        });
        return "Hello from MyBean.createRoute!";
    }

}
