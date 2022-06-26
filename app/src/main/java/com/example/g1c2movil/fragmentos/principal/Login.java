package com.example.g1c2movil.fragmentos.principal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.g1c2movil.R;
import com.example.g1c2movil.activity.Menu;
import com.example.g1c2movil.databinding.FragmentLoginBinding;
import com.example.g1c2movil.fragmentos.base.BaseFragment;
import com.example.g1c2movil.retrofit.Api;
import com.example.g1c2movil.retrofit.model.LoginBody;
import com.example.g1c2movil.retrofit.model.RespuestaAuth;
import com.example.g1c2movil.retrofit.service.UsuarioService;
import com.example.g1c2movil.utils.SesionPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseFragment<FragmentLoginBinding> {

    private Button loginbtn;

    private EditText usuario, contrasena;

    private ProgressBar pb;

    private UsuarioService servicioApi;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //redireccion si ya hay sesion activa
        // Redirección al Login
        if (SesionPrefs.get(requireActivity()).isLogged()) {
            abrirMenu();
        }

        //inicializamos los elementos
        loginbtn = getBinding().loginbtn;
        usuario = getBinding().username;
        contrasena = getBinding().password;
        pb = getBinding().pbLogin;

        //Hacemos la conexion a la api
        servicioApi = Api.getUsuarioService();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString().trim();
                String pass = contrasena.getText().toString().trim();

                if (esUsuarioValido(user) && esContraValida(pass)) {
                    pb.setVisibility(View.VISIBLE);
                    loginbtn.setVisibility(View.GONE);
                    iniciarSesion(user, pass);
                }
            }
        });

    }

    private void abrirMenu() {
        Intent mainIntent = new Intent(requireActivity(), Menu.class);
        startActivity(mainIntent);
        requireActivity().finish();
    }


    // Realiza la solicitud para iniciar sesion
    private void iniciarSesion(String user, String pass) {

        Call<RespuestaAuth> loginCall = servicioApi.login(new LoginBody(user, pass));
        loginCall.enqueue(new Callback<RespuestaAuth>() {
            @Override
            public void onResponse(Call<RespuestaAuth> call, Response<RespuestaAuth> response) {

                if (!response.isSuccessful()) { //poner el simbolo ! antes de response

                    pb.setVisibility(View.GONE);
                    loginbtn.setVisibility(View.VISIBLE);

                    String error;
                    if (response.code() == 401) {
                        error = "Unauthorized";
                    } else {
                        error = "Uknown";
                    }
                    toast("Error: " + error);

                    return;
                }


                Log.d("Login", "" + response.body());
                SesionPrefs.get(requireActivity()).saveAuth(response.body());

                toast("Has iniciado sesion exitosamente.");
                abrirMenu();
            }

            @Override
            public void onFailure(Call<RespuestaAuth> call, Throwable t) {

                pb.setVisibility(View.GONE);
                loginbtn.setVisibility(View.VISIBLE);
                toast("Error: " + t.getMessage());
            }
        });

    }

    // Valida si el texto ingresado es un mail valido
    private boolean esUsuarioValido(String usuario) {

        if (usuario.isEmpty()) {
            toast("Por favor ingrese su usuario");
            return false;
        } else {
            return true;
        }
    }

    //Valida si el texto corresponde a una contraseña valida (mayor a 4 caracteres)
    private boolean esContraValida(String contrasena) {
        if (contrasena.isEmpty()) {
            toast("La contraseña no debe ir vacia.");
            return false;
        } else if (contrasena.length() < 4) {
            toast("Por favor verifique su contraseña");
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected FragmentLoginBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }
}