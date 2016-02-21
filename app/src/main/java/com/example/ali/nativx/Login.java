package com.example.ali.nativx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {
    Button bLogin, bSignUp;
    EditText etUserName, etPassword;
    public static UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        bSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                String email = etUserName.getText().toString();
                DatabaseManager db = new DatabaseManager(this);

                if(db.isUserExist(email)){
                    //userLocalStore.storeUserData(email);
                    //userLocalStore.setUserLoggedIn(true);
                    Toast.makeText(getApplicationContext(), "User Found", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, Explore.class));
                }
                else
                    Toast.makeText(getApplicationContext(), "User Not Found. Please enter valid user details", Toast.LENGTH_LONG).show();
                break;
            case R.id.bSignUp:
                startActivity(new Intent(this, Register.class));
                break;

        }
    }
}

