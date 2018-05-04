package com.example.user.myhealthcheck;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activity_examinations extends AppCompatActivity {
    SessionManager session;
    // TextView listView_amka,listView_idd,listView_type,listView_nameexam,listView_result,listView_date,listView_comments;
    ArrayList<Exam> examList=new ArrayList <Exam>();
    private ArrayList<String> input1 = new ArrayList<String>();
    private TableRow row,row1,row2,row3,row4,row5,row6,row7;
    private TableLayout inflate;
    private TextView txtcol1, txtcol3,txtcol4,txtcol5,txtcol6,txtcol7,txtcol8,textd,txtcola3,txtcola4,txtcola5,txtcola6,txtcola7,txtcola8;
    private String t1="Id Dr:"+"      ";
    private String t2="Εξέταση:"+"    ";
    private String t3="Είδος:"+"      ";
    private String t4="Αποτέλεσμα:"+" ";
    private String t5="Ημερομηνία:"+" ";
    private String t6="Σχόλια:"+"     ";
  //  Button[] myButton ;
  //  Button pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinations);
        inflate = (TableLayout) findViewById(R.id.mytable);
    //    inflate2 = (TableLayout) findViewById(R.id.mytable);
        // pdf = (Button) findViewById(R.id.pdf);
      //  pdf.setVisibility(View.GONE);
        SessionManager s=new SessionManager(activity_examinations.this);

        getJSON("http://192.168.1.2/mypraxis/MyHealthCheck/Api.php");
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

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
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
                    session = new SessionManager(activity_examinations.this);
                    HashMap<String, String> user = session.getUserDetails();

                    // name
                    String amka_user = user.get(SessionManager.KEY_NAME);
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                   // con.setDoInput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("amka_user","UTF-8")+"="+URLEncoder.encode(amka_user,"UTF-8");
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

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
       // String[] product = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            //adding the product to product list
            input1.add(String.valueOf(i));
            examList.add(new Exam(
                    obj.getInt("amka"),
                    obj.getInt("id_doctor"),
                    obj.getInt("id_exam"),
                    obj.getString("type_ex"),
                    obj.getString("text"),
                    obj.getString("date_e"),
                    obj.getString("comments")
                    //  obj.getString("name")
            ));


            row = new TableRow(activity_examinations.this);
            row1 = new TableRow(activity_examinations.this);
            row2 = new TableRow(activity_examinations.this);
            row3 = new TableRow(activity_examinations.this);
            row4 = new TableRow(activity_examinations.this);
            row5 = new TableRow(activity_examinations.this);
            row6 = new TableRow(activity_examinations.this);
            row7 = new TableRow(activity_examinations.this);
            txtcol1 = new TextView(activity_examinations.this);


            if ((examList.get(i) != null)) {

                txtcol1.setText(input1.get(i));


            } else {
                txtcol1.setText("");
            }
            row.addView(txtcol1);
            inflate.addView(row);
            txtcola3 = new TextView(activity_examinations.this);
            txtcola4 = new TextView(activity_examinations.this);
            txtcola5 = new TextView(activity_examinations.this);
            txtcola6 = new TextView(activity_examinations.this);
            txtcola7 = new TextView(activity_examinations.this);
            txtcola8 = new TextView(activity_examinations.this);
            txtcol3 = new TextView(activity_examinations.this);
            txtcol4 = new TextView(activity_examinations.this);
            txtcol5 = new TextView(activity_examinations.this);
            txtcol6 = new TextView(activity_examinations.this);
            txtcol7 = new TextView(activity_examinations.this);
            txtcol8 = new TextView(activity_examinations.this);


            if (examList.get(i) != null) {

                //  txtcol2.setText(examList.get(i).getAmka());


                txtcola3.setText(t1);
                txtcola4.setText(t2);
                txtcola5.setText(t3);
                txtcola6.setText(t4);
                txtcola7.setText(t5);
                txtcola8.setText(t6);
                txtcol3.setText(String.valueOf(examList.get(i).getid_doctor()));
                txtcol4.setText(String.valueOf(examList.get(i).getName_exam()));
                txtcol5.setText(examList.get(i).getType());
                txtcol7.setText(examList.get(i).getDate());
                txtcol8.setText(examList.get(i).getComments());
                // txtcol9.setText(examList.get(i).getComments());
            } else {
                txtcol1.setText("");
                txtcol3.setText("");
                txtcol4.setText("");
                txtcol5.setText("");
                txtcol6.setText("");
                txtcol7.setText("");
                txtcol8.setText("");

            }
            this.row1.addView(txtcola7);
            this.row1.addView(txtcol7);
            inflate.addView(row1);
            this.row2.addView(txtcola3);
            this.row2.addView(txtcol3);
            inflate.addView(row2);
            this.row3.addView(txtcola4);
            this.row3.addView(txtcol4);
            inflate.addView(row3);
            this.row4.addView(txtcola5);
            this.row4.addView(txtcol5);
            inflate.addView(row4);
            this.row5.addView(txtcola6);
            this.row5.addView(txtcol6);
            inflate.addView(row5);
            this.row6.addView(txtcola8);
            this.row6.addView(txtcol8);
            inflate.addView(row6);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("PDF for Exam: " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            //linear.addView(btn, params);
            this.row7.addView(btn);
            inflate.addView(row7);
            btn = ((Button) findViewById(id_));
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Button clicked index = " + examList.get(id_).getId_e(), Toast.LENGTH_SHORT).show();

                    // ViewPdf pdf = new ViewPdf();
                    // pdf.downloadPDF(examList.get(id_).getfile());
                    //Run the code in a different thread than the UI thread
                    //  Thread thread = new Thread() {
                    //   @Override
                    //public void run() {
                    Intent i = new Intent(getApplicationContext(), pdf_open.class);
                   String url= String.valueOf(examList.get(id_).getId_e());
                    Toast.makeText(view.getContext(), "Button clicked index = " + session.ipaddress(), Toast.LENGTH_SHORT).show();
                    i.putExtra("key", "http://192.168.1.2/mypraxis/MyHealthCheck/src/"+url+"/");
                    startActivity(i);
                }
            });
        }}}


