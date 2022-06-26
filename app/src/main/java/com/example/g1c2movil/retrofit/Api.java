package com.example.g1c2movil.retrofit;

import com.example.g1c2movil.retrofit.service.UsuarioService;

public class Api {

    public static final String URL = "http://192.168.219.37:8080";

    public static UsuarioService getUsuarioService() {
        return RetrofitCliente.getCliente(URL).create(UsuarioService.class);
    }

}
