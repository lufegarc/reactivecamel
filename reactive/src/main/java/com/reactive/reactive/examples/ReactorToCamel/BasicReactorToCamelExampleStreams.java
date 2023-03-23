package com.reactive.reactive.examples.ReactorToCamel;

import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;

//@Component
public class BasicReactorToCamelExampleStreams  {
    @Autowired
    private CamelReactiveStreamsService camel;


    @PostConstruct
    public void setupStreams() {

        // Get a subscriber from camel
        Subscriber<String> elements = camel.streamSubscriber("elements", String.class);

        // Emit a string every 7 seconds and push it to the Camel "elements" stream
        Flux.interval(Duration.ofSeconds(7))
                .map(item -> "element " + item)
                .subscribe(elements);

    }
}
