package com.reactive.reactive.examples.ExposicionRest.beans;

import com.reactive.reactive.models.in.ApiConnect.RequestApiConnect;
import com.reactive.reactive.models.in.RequestPrincipal;
import org.springframework.stereotype.Component;


@Component
public class TransformationBean {


    public RequestApiConnect rqPrincipal2rqApiConnect(RequestPrincipal requestPrincipal) {

        RequestApiConnect a = new RequestApiConnect();
        a.setAccount(requestPrincipal.getNumeroDeCuenta());
        a.setAccountType("CORRIENTE");
        a.setIdentification(requestPrincipal.getNumCedula());
        a.setTipoIdentificacion("C.C");
        a.setOffice(requestPrincipal.getOficina());
        return a;


    }


}
