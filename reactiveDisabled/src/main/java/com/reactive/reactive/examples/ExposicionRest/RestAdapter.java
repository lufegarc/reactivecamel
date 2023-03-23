package com.reactive.reactive.examples.ExposicionRest;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class RestAdapter extends RouteBuilder {

    @Override
    public void configure()  {

       restConfiguration()
               .component("netty-http")
               .port(8081);

       rest()
         .get("/no-reactive")
               .to("direct:pruebaDSL");


    }


}
