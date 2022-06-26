package com.example.g1c2movil.retrofit.service;

import com.example.g1c2movil.retrofit.model.Anexo;
import com.example.g1c2movil.retrofit.model.Carrera;
import com.example.g1c2movil.retrofit.model.Convocatoria;
import com.example.g1c2movil.retrofit.model.LoginBody;
import com.example.g1c2movil.retrofit.model.RespuestaAuth;
import com.example.g1c2movil.retrofit.model.RespuestaGenerica;
import com.example.g1c2movil.retrofit.model.SolicitudAlumno;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface UsuarioService {

    @POST("GestionConvocatoria/CrearConvocatoria")
    Call<RespuestaGenerica<Convocatoria>> crearConvocatoria();

    @GET("GestionConvocatoria/ListaConvocatoria")
    Call<RespuestaGenerica<Convocatoria>> getConvocatorias();

    @GET("GestionCarrera/ListarCarreras")
    Call<RespuestaGenerica<Carrera>> getCarreras();

    @GET("GestionAnexo9/ListaAnexo9")
    Call<RespuestaGenerica<Anexo>> getAnexos();

    @GET("GestionSolicitudAlumno/ListaSolicitudAlumno")
    Call<RespuestaGenerica<SolicitudAlumno>> getSolicitudesAlumnos();

    @Streaming
    @GET("files/{filename}")
    Call<ResponseBody> getFile(@Path("filename") String filename);

    // autentificacion de usuario
    @POST("auth/login")
    Call<RespuestaAuth> login(@Body LoginBody loginBody);

}
