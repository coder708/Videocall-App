package com.example.videocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.videocall.databinding.ActivityNewAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NewAccount extends AppCompatActivity {

    ActivityNewAccountBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNewAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setTitle("Signin");
        dialog.setMessage("Your Account is created");

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(NewAccount.this,LoginActivity.class));

            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String mail,user,password;
                mail=binding.emailbox.getText().toString();
                user=binding.namebox.getText().toString();
                password=binding.passwordbox.getText().toString();

                auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful()){


                            Users users=new Users();
                            users.setMail1(mail);
                            users.setPassword1(password);
                            users.setUser1(user);
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(users);
                            startActivity(new Intent(NewAccount.this,MainActivity.class));
                            finish();
                        }

                        else{
                            Toast.makeText(NewAccount.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}