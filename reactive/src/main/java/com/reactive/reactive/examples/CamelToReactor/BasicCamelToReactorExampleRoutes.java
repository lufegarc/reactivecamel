package com.reactive.reactive.examples.CamelToReactor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class BasicCamelToReactorExampleRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Generating numbers every 5 seconds and forwarding to the stream "numbers"
        from("timer:clock?period=5000")
                .log("numbers")
                .setBody().header(Exchange.TIMER_COUNTER)
                .to("reactive-streams:numbers");

        // Generating strings every 4.9 seconds and forwarding to the stream "strings"
        from("timer:clock2?period=1000&delay=2000")
                .log("String")
                .setBody().simple("Hello World ${header.CamelTimerCounter}!")
                .to("reactive-streams:strings");
    }
}
