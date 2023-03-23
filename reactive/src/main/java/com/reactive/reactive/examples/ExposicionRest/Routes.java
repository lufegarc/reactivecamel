package com.reactive.reactive.examples.ExposicionRest;

import com.reactive.reactive.examples.ExposicionRest.beans.TransformationBean;
import com.reactive.reactive.models.in.RequestPrincipal;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.util.UnwrapStreamProcessor;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure()  {

        from("reactive-streams:transformarBody?maxInflightExchanges=200").routeId("TrasmformRoute")
                .unmarshal().json(RequestPrincipal.class)
                .bean(TransformationBean.class, "rqPrincipal2rqApiConnect")
                .process(new UnwrapStreamProcessor())
        ;

        from("reactive-streams:llamarApiconnect?maxInflightExchanges=200").routeId("httpCall")
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                //.to("netty-http:http://api-benchmark-capa-transaccional-lab.apps.ocpdes.ambientesbc.lab/prueba/servicio/prueba/reactiva")
        ;

    }
}
