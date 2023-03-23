package com.reactive.reactive.examples.ExposicionRest.beans;

import com.reactive.reactive.models.in.ApiConnect.RequestApiConnect;
import com.reactive.reactive.models.in.RequestPrincipal;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TransformationBean {


    public Publisher<RequestApiConnect> rqPrincipal2rqApiConnect(Publisher<RequestPrincipal> requestPrincipal) {

        return Flux.from(requestPrincipal).map(rq -> {
                    RequestApiConnect a = new RequestApiConnect();
                    a.setAccount(rq.getNumeroDeCuenta());
                    a.setAccountType("CORRIENTE");
                    a.setIdentification(rq.getNumCedula());
                    a.setTipoIdentificacion("C.C");
                    a.setOffice(rq.getOficina());
                    return a;
                }
        );

    }


}
