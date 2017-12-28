package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        openMenu();
    }

    public void openMenu() {

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

        imgbtn_ex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menu = new Intent(context, activity_examinations.class);
                startActivity(menu);

            }

        });
    }


}
