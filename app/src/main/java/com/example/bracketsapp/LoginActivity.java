package com.example.bracketsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button botaoEnviarLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.id_EmailLogin);
        senha = findViewById(R.id.id_SenhaLogin);
        mAuth = FirebaseAuth.getInstance();

        botaoEnviarLogin = (Button) findViewById(R.id.bt_EnviarLogin);

        botaoEnviarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editEmail = email.getText().toString();
                String editSenha = senha.getText().toString();

                if (editEmail.isEmpty() || editSenha.isEmpty()){
                    Snackbar.make(v,"Preencha todos os campos!!", Snackbar.LENGTH_LONG).show();
                }
                else
                {

                    mAuth.signInWithEmailAndPassword(editEmail ,editSenha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(LoginActivity.this, "login feito com sucesso", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(LoginActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}