package com.example.g1c2movil.fragmentos.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g1c2movil.adaptador.InstructivoAdaptador;
import com.example.g1c2movil.databinding.FragmentInstructivoBinding;
import com.example.g1c2movil.fragmentos.base.BaseFragment;
import com.example.g1c2movil.retrofit.Api;
import com.example.g1c2movil.retrofit.service.UsuarioService;

public class InstructivoFragment extends BaseFragment<FragmentInstructivoBinding> {

    private UsuarioService servicioApi;

    private InstructivoAdaptador adapter;
    private RecyclerView rvInstructivo;
    private ProgressBar pbInstructivo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pbInstructivo = getBinding().pbInstructivo;
        rvInstructivo = getBinding().rvInstructivo;
        //rvInstructivo.setHasFixedSize(true);

        //Hacemos la conexion a la api
        servicioApi = Api.getUsuarioService();

        pbInstructivo.setVisibility(View.VISIBLE);

        /*
        Call<RespuestaGenerica<Convocatoria>> convocatoriaCall = servicioApi.getConvocatorias();
        convocatoriaCall.enqueue(new Callback<RespuestaGenerica<Convocatoria>>() {

            @Override
            public void onResponse(Call<RespuestaGenerica<Convocatoria>> call, Response<RespuestaGenerica<Convocatoria>> response) {

                RespuestaGenerica<Convocatoria> responseFromAPI = response.body();

                if (!responseFromAPI.getData().isEmpty()) {

                    adapter = new InstructivoAdaptador(responseFromAPI.getData());
                    rvInstructivo.setAdapter(adapter);
                }
                pbInstructivo.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<Convocatoria>> call, Throwable t) {
                toast("Error: " + t.getMessage());
                pbInstructivo.setVisibility(View.GONE);
            }
        });*/
    }

    @Override
    protected FragmentInstructivoBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentInstructivoBinding.inflate(inflater, container, false);
    }

}