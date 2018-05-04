package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
        ImageButton imgbtn6 = (ImageButton) findViewById(R.id.imageButton6);
        Button sosn = (Button) findViewById(R.id.sosn);
        TextView notcon = (TextView) findViewById(R.id.notcon);

        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);

                builder.setMessage("message:");
                builder.setTitle("SOS message");

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to send sos message to your contact ?");
                //This will not allow to close dialogbox until user selects an option
                builder.setCancelable(false);
                builder.setPositiveButton("Yes, i'm on emergency !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(menu.this, "positive button", Toast.LENGTH_SHORT).show();
                        //builder.finish();
                        Intent menu = new Intent(context, send_sos_message.class);
                        startActivity(menu);
                    }
                });
                builder.setNegativeButton("No, i am ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        Toast.makeText(menu.this, "negative button", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert.show();


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
      //  ImageButton imgbtn6 = (ImageButton) findViewById(R.id.imageButton6);
        imgbtn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), pdf_open.class);
                //String url= String.valueOf(examList.get(id_).getId_e());
                i.putExtra("key", session.ipaddress()+"/mypraxis/MyHealthCheck/app/build/generated/assets/049_Πρώτες Βοήθειες.pdf");
                startActivity(i);
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
