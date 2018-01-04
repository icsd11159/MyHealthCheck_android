package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;


public class menu extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new SessionManager(getApplicationContext());
        openMenu();

    }

    public void openMenu() {

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_SHORT).show();
        final Context context = this;

        ImageButton imgbtn = (ImageButton) findViewById(R.id.imageButton3);

        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, MapsActivity.class);
                startActivity(menu);

            }

        });
        ImageButton imgbtn_ex = (ImageButton) findViewById(R.id.imageButton);
        if (!session.isLoggedIn()){

            imgbtn_ex.setVisibility(View.GONE);
        }
        else{
            HashMap<String, String> user = session.getUserDetails();

            // name
            String amka_user = user.get(SessionManager.KEY_NAME);
            Toast.makeText(getApplicationContext(), "your AMKA: " + amka_user, Toast.LENGTH_SHORT).show();
            imgbtn_ex.setVisibility(View.VISIBLE);

        }

        imgbtn_ex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, activity_examinations.class);
                startActivity(menu);


            }

        });
       Button main = (Button) findViewById(R.id.buttonmain);
        main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, MainActivity.class);
                startActivity(menu);

            }

        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
