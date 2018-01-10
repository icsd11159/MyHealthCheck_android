package com.example.user.myhealthcheck;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class sos_number extends AppCompatActivity {
    SessionManager session;
    EditText edit_phone,edit_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_number);
        session = new SessionManager(getApplicationContext());
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_history = (EditText) findViewById(R.id.edit_history);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_SHORT).show();
      //  final Context context = this;

        Button add = (Button) findViewById(R.id.button4);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getJSON("http://8d2a7219.ngrok.io/mypraxis/MyHealthCheck/addsos.php");
            }

        });


    }

    private void getJSON(final String urlWebService) {
        /*
        * As fetching the json string is a network operation
        * And we cannot perform a network operation in main thread
        * so we need an AsyncTask
        * The constrains defined here are
        * Void -> We are not passing anything
        * Void -> Nothing at progress update as well
        * String -> After completion it should return a string and it will be the json string
        * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                // Toast.makeText(context, "Wrong Username or Password!"+result, Toast.LENGTH_SHORT).show();
                if(result.equals("Not adding the values")) {

                    Toast.makeText(getApplicationContext(), "STATUS :"+result, Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "STATUS :"+result, Toast.LENGTH_LONG).show();
                    Intent menu = new Intent(getApplicationContext(), menu.class);
                    startActivity(menu);

                }




            }
            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    String number_sos= edit_phone.getText().toString();
                    String history= edit_history.getText().toString();
                   // Toast.makeText(getApplicationContext(), "HISTORY :"+history, Toast.LENGTH_LONG).show();
                    session = new SessionManager(getApplicationContext());
                    HashMap<String, String> user = session.getUserDetails();

                    // name
                    String amka_user = user.get(SessionManager.KEY_NAME);
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    // con.setDoInput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("amka_user","UTF-8")+"="+URLEncoder.encode(amka_user,"UTF-8")+"&"
                            +URLEncoder.encode("number_sos","UTF-8")+"="+URLEncoder.encode(number_sos,"UTF-8")+"&"
                            +URLEncoder.encode("history","UTF-8")+"="+URLEncoder.encode(history,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(),"iso-8859-1"));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {

                    return null;
                }

            }



        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

}
