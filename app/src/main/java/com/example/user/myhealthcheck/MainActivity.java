package com.example.user.myhealthcheck;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
public class MainActivity extends AppCompatActivity{
    ImageButton button,button2;
    Button connect;
    // Alert Dialog Manager
   // AlertDialogManager alert = new AlertDialogManager();
    TextView lblName,lblName1;
    // Session Manager Class
    SessionManager session;

    // Button Logout
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Session class instance
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_main);
        lblName = (TextView) findViewById(R.id.lblName);
        lblName1 = (TextView) findViewById(R.id.lblName1);
        btnLogout=(Button) findViewById(R.id.btnLogout);
        connect = (Button) findViewById(R.id.connect);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_SHORT).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
       // session.checkLogin();


        if (!session.isLoggedIn()){

            btnLogout.setVisibility(View.GONE);
            connect.setVisibility(View.VISIBLE);
        }
        else{
            HashMap<String, String> user = session.getUserDetails();

            // name
            String amka_user = user.get(SessionManager.KEY_NAME);

            lblName.setText("My AMKA:");
            lblName1.setText(amka_user);
            btnLogout.setVisibility(View.VISIBLE);
            connect.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "User AMKA: " + amka_user, Toast.LENGTH_SHORT).show();
        }

        addListenerOnButton();


    }




    public void addListenerOnButton() {

        final Context context = this;

        button = (ImageButton) findViewById(R.id.button);
        button2 = (ImageButton) findViewById(R.id.button2);



       // btn_login = (Button) findViewById(R.id.btn_login);
       // link_signup = (Button) findViewById(R.id.link_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, menu.class);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);

            }
        });
        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Signin.class);
                startActivity(intent);

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                session.logoutUser();


            }
        });
       // link_signup.setOnClickListener(new View.OnClickListener() {

          //  @Override
           // public void onClick(View arg0) {

             //   Intent intent = new Intent(context, Register.class);
             //   startActivity(intent);

         //   }
      //  });
       // btn_login.setOnClickListener(new View.OnClickListener() {

           // @Override
           // public void onClick(View arg0) {

             //   Intent intent = new Intent(context, menu.class);
              //  startActivity(intent);

         //   }
       // });
    }}
