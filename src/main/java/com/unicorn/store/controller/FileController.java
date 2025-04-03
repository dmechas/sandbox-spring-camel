package com.unicorn.store.controller;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileController extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://inputdir/?recursive=true&delete=true")
                .routeId("fileRoute")
                .log("Processing file: ${header.CamelFileName}")
                .convertBodyTo(String.class) // make sure it's a string
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    var lineCount = body.lines().count();
                    exchange.getIn().setBody("Line count: " + lineCount);
                })
                .to("file://outputdir?fileName=${file:name.noext}-lines.out");
    }

}
