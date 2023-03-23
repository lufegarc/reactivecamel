package com.reactive.reactive.examples.ReactorToCamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class BasicReactorToCamelExampleRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Transform the body of received items and log
        from("reactive-streams:elements")
                .setBody().simple("BasicReactorToCamel - Camel received ${body}")
                .to("log:INFO");
    }


}
