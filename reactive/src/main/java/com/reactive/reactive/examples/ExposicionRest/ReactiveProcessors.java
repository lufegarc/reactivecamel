package com.reactive.reactive.examples.ExposicionRest;

import com.reactive.reactive.models.in.ApiConnect.RequestApiConnect;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreams;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class ReactiveProcessors {

    @Autowired
    private CamelContext context;

    @Autowired
    private CamelReactiveStreamsService camel;

    @PostConstruct
    public void setup() {

        camel.process("rest:get:orders", exchange ->
                Flux.from(exchange)
                        .map(ex -> ex.getIn().getBody(String.class))
                        .flatMap(camel.toStream("transformarBody", RequestApiConnect.class))
                        .flatMap(camel.toStream("llamarApiconnect", String.class))
                        .map(Object.class::cast)
                        .switchIfEmpty(
                                Flux.from(exchange)
                                        .doOnNext(ex -> ex.getIn().setBody("Not found"))
                                        .doOnNext(ex -> ex.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 404))
                        ));


        camel.process("direct:pruebaDSL", exchange ->
                Flux.from(exchange)
                        .map(ex -> ex.getIn().getBody(String.class))
                        .flatMap(camel.toStream("transformarBody", RequestApiConnect.class))
                        .flatMap(camel.toStream("llamarApiconnect", String.class))
                        .map(Object.class::cast)
                        .switchIfEmpty(
                                Flux.from(exchange)
                                        .doOnNext(ex -> ex.getIn().setBody("Not found"))
                                        .doOnNext(ex -> ex.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 404))
                        ));


    }

}
