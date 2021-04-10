package com.example.bracketsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button botaoEnviarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.id_EmailLogin);
        senha = findViewById(R.id.id_SenhaLogin);

        botaoEnviarLogin = (Button) findViewById(R.id.bt_EnviarLogin);

        botaoEnviarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editEmail = email.getText().toString();
                String editSenha = senha.getText().toString();

                if (editEmail.isEmpty() || editSenha.isEmpty()){
                    Snackbar.make(v,"Preencha todos os campos!!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}