package com.example.bracketsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class ActivityCriar extends AppCompatActivity {

    private EditText nome, email, telefone, senha;
    private TextView resultadoNome, resultadoEmail, resultadoTelefone, resultadoSenha;
    private Button botaoEnviarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);

        nome = findViewById(R.id.id_NomeCadastro);
        email = findViewById(R.id.id_EmailCadastro);
        telefone = findViewById(R.id.id_Telefone);
        senha = findViewById(R.id.id_SenhaCadastro);
        resultadoNome = findViewById(R.id.id_resultadoNome);
        resultadoEmail = findViewById(R.id.id_resultadoEmail);
        resultadoTelefone = findViewById(R.id.id_resultadoTelefone);
        resultadoSenha = findViewById(R.id.id_resultadoSenha);

        botaoEnviarCadastro = (Button) findViewById(R.id.bt_EnviarCadastro);

        botaoEnviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editNome = nome.getText().toString();
                String editEmail = email.getText().toString();
                String editTelefone = telefone.getText().toString();
                String editSenha = senha.getText().toString();

                if (editNome.isEmpty() || editEmail.isEmpty() || editTelefone.isEmpty() || editSenha.isEmpty()){
                    Snackbar.make(v,"Preencha todos os campos!!", Snackbar.LENGTH_SHORT).show();
                }else{
                    resultadoNome.setText(editNome);
                    resultadoEmail.setText(editEmail);
                    resultadoTelefone.setText(editTelefone);
                    resultadoSenha.setText(editSenha);
                    Snackbar.make(v,"Cadastro feito com sucesso!!", Snackbar.LENGTH_SHORT).show();
                }

            }
        });


    }
}