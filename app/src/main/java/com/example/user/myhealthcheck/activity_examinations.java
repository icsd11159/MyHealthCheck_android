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
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activity_examinations extends AppCompatActivity implements Serializable {
    SessionManager session;
    // TextView listView_amka,listView_idd,listView_type,listView_nameexam,listView_result,listView_date,listView_comments;
    ArrayList<Exam> examList = new ArrayList<Exam>();
   // ArrayList<pdfname> pdfList = new ArrayList<pdfname>();

    private int i;
   // private Boolean isnew = true;
    private ArrayList<String> input1 = new ArrayList<String>();
    private ArrayList<String> input2 = new ArrayList<String>();
    private TableRow row7,row2;
    private TableLayout inflate;
    private int newid=0;
    private TextView txtcol1;
    public boolean isnew=true;
    public void setnewid(int newid)
    {
        this.newid=newid;
    }
    public int getnewid()
    {
        return newid;
    }
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
        SessionManager s = new SessionManager(activity_examinations.this);

        getJSON("http://192.168.1.4/mypraxis/MyHealthCheck/Api.php");
        //  String pdfname_url ="http://192.168.1.2/mypraxis/MyHealthCheck/pdfname.php";
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
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
                    String post_data = URLEncoder.encode("amka_user", "UTF-8") + "=" + URLEncoder.encode(amka_user, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "iso-8859-1"));

                    //A simple string to read values from each line
                    String json;
                    // String jsonpdf;
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
      //  GetJSON getJSONpdf = new GetJSON();
        //getJSONpdf.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
       // JSONArray jsonArraypdf = new JSONArray(json);
        // String[] product = new String[jsonArray.length()];

        for ( i = 0; i < jsonArray.length(); i++) {

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

            ));


            //  Button btn = new Button(this);
            //   btn.setId(i);
            // final int id_ = btn.getId();

            // btn = ((Button) findViewById(id_));

           if (newid!=examList.get(i).getId_e()){


                row7 = new TableRow(this);
               row2 = new TableRow(this);
                Button btn = new Button(this);
                btn.setId(i);
                final int id_ = btn.getId();
                btn.setText("Exam: " + examList.get(i).getId_e()+" , "+ examList.get(id_).getType()+",Date: "+ examList.get(i).getDate());
                btn.setBackgroundColor(Color.rgb(70, 80, 90));
               final int newidd=examList.get(i).getId_e();
                //linear.addView(btn, params);
               txtcol1 = new TextView(this);
               txtcol1.setText("");
               this.row2.addView(txtcol1);
               inflate.addView(row2);
                this.row7.addView(btn);
                inflate.addView(row7);
               //inflate.addView(row2);
                btn = ((Button) findViewById(id_));
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(activity_examinations.this, viewactivity.class);
                        // ArrayList<String> myList = new ArrayList<String>();
                        Bundle args = new Bundle();
                        args.putSerializable("examlist",(Serializable)examList);
                        intent.putExtra("BUNDLE",args);
                        intent.putExtra("id_e",newidd);
                        startActivity(intent);

                    }
                });
               newid=examList.get(i).getId_e();//gia na mhn tupwnei parametro ths idias eksetash
            }
        }
    }

}
