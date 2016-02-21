package com.example.ali.nativx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etFirstName, etLastName, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean errorFree = true;

        switch(v.getId()){
            case R.id.bRegister:
                if((etFirstName.getText().toString()).equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your First Name", Toast.LENGTH_SHORT).show();
                    etFirstName.requestFocus();
                    errorFree = false;
                }
                if((etLastName.getText().toString()).equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Last Name", Toast.LENGTH_SHORT).show();
                    etLastName.requestFocus();
                    errorFree = false;
                }

                if(!validateEmail(etEmail.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    errorFree = false;
                }

                if((etPassword.getText().toString()).equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    errorFree = false;
                } else if((etPassword.getText().toString()).length()<6){
                    Toast.makeText(getApplicationContext(), "Password length must be at least 6 characters long", Toast.LENGTH_LONG).show();
                    etPassword.requestFocus();
                    errorFree = false;
                }

                if(errorFree) {
                    Toast.makeText(getApplicationContext(), "User Successfully Registered", Toast.LENGTH_SHORT).show();

                    String first_name = etFirstName.getText().toString();
                    String last_name = etLastName.getText().toString();
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();

                    DatabaseManager db = new DatabaseManager(this);
                    db.addUser(first_name, last_name, email, password);
                    //db.close();
                    startActivity(new Intent(this, Login.class));
                }

                break;
        }
    }
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
