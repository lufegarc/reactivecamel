package com.reactive.reactive.examples.RestExample;


import org.apache.camel.Header;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

//@Component("calculator")
public class RestExampleStreams {


    public Publisher<Long> sum(@Header("num1") Publisher<Long> num1, @Header("num2") Publisher<Long> num2) {



        return Flux.from(num1).zipWith(num2)
                .map(t -> t.getT1() + t.getT2());
    }
}
