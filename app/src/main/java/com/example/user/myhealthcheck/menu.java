package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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
        Button sosn = (Button) findViewById(R.id.sosn);
        TextView notcon = (TextView) findViewById(R.id.notcon);

        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, MapsActivity.class);
                startActivity(menu);

            }

        });
        ImageButton imgbtn_ex = (ImageButton) findViewById(R.id.imageButton);


        imgbtn_ex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!session.isLoggedIn()){

                    Toast.makeText(getApplicationContext(), "You have to login first: " , Toast.LENGTH_LONG).show();
                }
                else{
                    HashMap<String, String> user = session.getUserDetails();

                    // name
                    String amka_user = user.get(SessionManager.KEY_NAME);
                    Toast.makeText(getApplicationContext(), "your AMKA: " + amka_user, Toast.LENGTH_SHORT).show();
                    Intent menu = new Intent(context, activity_examinations.class);
                    startActivity(menu);

                }




            }

        });
       ImageButton main = (ImageButton) findViewById(R.id.buttonmain);
        main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, MainActivity.class);
                startActivity(menu);

            }

        });

        sosn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!session.isLoggedIn()){

                    Toast.makeText(getApplicationContext(), "You have to login first: " , Toast.LENGTH_LONG).show();
                }
                else{
                    HashMap<String, String> user = session.getUserDetails();

                    // name
                    String amka_user = user.get(SessionManager.KEY_NAME);
                    Toast.makeText(getApplicationContext(), "your AMKA: " + amka_user, Toast.LENGTH_SHORT).show();
                    Intent menu = new Intent(context, sos_number.class);
                    startActivity(menu);

                }



            }

        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
