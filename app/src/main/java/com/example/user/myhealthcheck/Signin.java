package com.example.user.myhealthcheck;

/**
 * Created by user on 19/12/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Signin extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;
    Context context;
    // Alert Dialog Manager
    // AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;
    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Session Manager
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_connect_user);
        UsernameEt = (EditText) findViewById(R.id.input_email);
        PasswordEt = (EditText) findViewById(R.id.input_password);
        // Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        backgroundWorker.setDataDownloadListener(new BackgroundWorker.DataDownloadListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                String amka = backgroundWorker.get_amka();
                session.createLoginSession(amka);
                Toast.makeText(getApplicationContext(), "User Login Status from signin: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
                HashMap<String, String> user = session.getUserDetails();

                // name
                String amka_user = user.get(SessionManager.KEY_NAME);
                Toast.makeText(getApplicationContext(), "your AMKA from signin: " + amka_user, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainActivity.class));
            }
            @Override
            public void dataDownloadFailed() {
                // handler failure (e.g network not available etc.)
                Toast.makeText(getApplicationContext(), "You are not signin ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";

        backgroundWorker.execute(type, username, password);


    }


    public void OnRegister(View view) {
        Intent myIntent = new Intent(Signin.this, Register.class);
        startActivity(myIntent);
        // context.startActivity(new Intent(context, Register.class));
    }

}
