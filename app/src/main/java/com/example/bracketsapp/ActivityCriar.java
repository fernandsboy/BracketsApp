package com.example.bracketsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActivityCriar extends AppCompatActivity {

    private EditText nome, email, telefone, senha;
    private TextView resultadoNome, resultadoEmail, resultadoTelefone, resultadoSenha;
    private Button botaoEnviarCadastro;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String userID;

   private String editNome ;
  private   String editEmail ;
  private   String editTelefone ;
   private String editSenha ;

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
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        botaoEnviarCadastro = (Button) findViewById(R.id.bt_EnviarCadastro);

        botaoEnviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               editNome = nome.getText().toString();
               editEmail = email.getText().toString();
                editTelefone = telefone.getText().toString();
                editSenha = senha.getText().toString();

                if (editNome.isEmpty() || editEmail.isEmpty() || editTelefone.isEmpty() || editSenha.isEmpty()){
                    Snackbar.make(v,"Preencha todos os campos!!", Snackbar.LENGTH_LONG).show();
                }else{


                    mAuth.createUserWithEmailAndPassword(editEmail , editSenha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful())
                                    {
                                        userID = mAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fStore.collection("users")
                                                .document(userID);

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("name" , editNome);
                                        user.put("email", editEmail);
                                        user.put("number", editTelefone);
                                        user.put("password", editSenha);
                                        documentReference.set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(ActivityCriar.this, "Banco de dados salvo", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(ActivityCriar.this, ""+e, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(ActivityCriar.this, "Conta criada", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ActivityCriar.this, ""+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });


    }
}