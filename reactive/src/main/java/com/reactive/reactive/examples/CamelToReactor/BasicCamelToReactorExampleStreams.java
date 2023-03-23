package com.reactive.reactive.examples.CamelToReactor;

import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;


//@Component
public class BasicCamelToReactorExampleStreams {

    @Autowired
    private CamelReactiveStreamsService camel;

    private static final Logger LOG = LoggerFactory.getLogger(BasicCamelToReactorExampleStreams.class);

    @PostConstruct
    public void setupStreams() {
        // Use two streams from Camel
        Publisher<Integer> numbers = camel.fromStream("numbers", Integer.class);
        Publisher<String> strings = camel.fromStream("strings", String.class);

        Flux.from(numbers)
                .zipWith(strings) // emit items in pairs
                .map(tuple -> "BasicCamelToReactor - " + tuple.getT1() + " -> " + tuple.getT2())
                .doOnNext(LOG::info)
                .subscribe();
    }
}
