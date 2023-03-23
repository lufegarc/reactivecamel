package com.reactive.reactive.examples.ExposicionRest;

import com.reactive.reactive.examples.ExposicionRest.beans.TransformationBean;
import com.reactive.reactive.models.in.RequestPrincipal;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(RestAdapter.class);

    @Override
    public void configure()  {

        from("direct:pruebaDSL").routeId("RutaPrincipal")
          .to("direct:transformarBody")
          .to("direct:llamarApiconnect");

        from("direct:transformarBody").routeId("transformBody")
                .unmarshal().json(RequestPrincipal.class)
                .bean(TransformationBean.class, "rqPrincipal2rqApiConnect");

        from("direct:llamarApiconnect").routeId("httpCall")
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, simple("POST"));
                //.to("netty-http:http://api-benchmark-capa-transaccional-lab.apps.ocpdes.ambientesbc.lab/prueba/servicio/prueba/reactiva");


    }
}
