package com.reactive.reactive.examples.RestExample;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.util.UnwrapStreamProcessor;
import org.springframework.stereotype.Component;

//@Component
public class RestExampleRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // The full path should be eg.: http://localhost:8080/camel/sum/23/31
        rest().get("/sum/{num1}/{num2}")
                .produces("text/plain")
                .to("direct:sum");

        from("direct:sum")
                
                //.setHeader("num1").simple("headerAs(num1,Long)")
                //.setHeader("num2").simple("headerAs(num2,Long)")
                .setHeader("num1",header("num1").convertTo(Long.class))
                .setHeader("num2",header("num2").convertTo(Long.class))
                .log("Llega hasta aca ${header.num1} ${header.num2}")
                .bean("calculator", "sum")
                .process(new UnwrapStreamProcessor())
                .setBody().simple("The result is: ${body}");
    }

}
