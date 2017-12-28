package com.example.user.myhealthcheck;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    Button button;
    Button button2,connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();


    }




    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        connect = (Button) findViewById(R.id.connect);
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
