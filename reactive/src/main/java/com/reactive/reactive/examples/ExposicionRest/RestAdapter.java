package com.reactive.reactive.examples.ExposicionRest;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RestAdapter extends RouteBuilder {

    @Autowired
    private CamelReactiveStreamsService camel;

    private static final Logger LOG = LoggerFactory.getLogger(RestAdapter.class);


    @Override
    public void configure()  {

        restConfiguration().component("netty-http").port(8081);

       rest()
         .get("/restDSL")
         .to("direct:pruebaDSL");





    }


}
