package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 5/5/2018.
 */

public class readpdf {
     private int value;
    Context context;

    SessionManager session;

    // =new SessionManager(context);

//    SessionManager  session = new SessionManager(getApplicationContext());
    // TextView listView_amka,listView_idd,listView_type,listView_nameexam,listView_result,listView_date,listView_comments;
    //ArrayList<Exam> examList=new ArrayList <Exam>();
    ArrayList<pdfname> pdfList=new ArrayList <pdfname>();
    // private int j = 0;
  //  private Boolean isnew = true;

   // private ArrayList<String> input1 = new ArrayList<String>();
    private ArrayList<String> input2 = new ArrayList<String>();
   // private TableRow row,row1,row2,row3,row4,row5,row6,row7;
    //private TableLayout inflate;

    public void readjson()
    {
        getJSON("http://192.168.1.2/mypraxis/MyHealthCheck/pdfname.php");
    }
   public void setValue(int value)
   {
       this.value=value;
   }
    public Integer getValue() //returns a value
    {
        return this.value;
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
             Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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
                    //session = new SessionManager(readpdf.this);
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
       // GetJSON getJSONpdf = new GetJSON();
       // getJSONpdf.execute();
    }
    private void loadIntoListView(String json) throws JSONException {
       JSONArray jsonArray = new JSONArray(json);
       // JSONArray jsonArraypdf = new JSONArray(json);
        // String[] product = new String[jsonArray.length()];
     for ( int j = 0; j < jsonArray.length(); ) {
        Toast.makeText(context, "j: " + j, Toast.LENGTH_SHORT).show();
        JSONObject objpdf = jsonArray.getJSONObject(j);
        //adding the product to product list
        input2.add(String.valueOf(j));
        pdfList.add(new pdfname(
                objpdf.getString("name"),
                objpdf.getInt("id_exam")
        ));
        if(pdfList.get(j).getId_e()==getValue()){
    String name_pdf=pdfList.get(j).getname();

         //   Toast.makeText(getApplicationContext(), "is new: " + isnew, Toast.LENGTH_SHORT).show();




                  //  Toast.makeText(view.getContext(), "Button clicked index = " + pdfList.get(id_).getname(), Toast.LENGTH_SHORT).show();

                    // ViewPdf pdf = new ViewPdf();
                    // pdf.downloadPDF(examList.get(id_).getfile());
                    //Run the code in a different thread than the UI thread
                    //  Thread thread = new Thread() {
                    //   @Override
                    //public void run() {
                    Intent i = new Intent(context, pdf_open.class);
                    String url = String.valueOf(name_pdf);
                  //  Toast.makeText(view.getContext(), "Button clicked index = " + session.ipaddress(), Toast.LENGTH_SHORT).show();
                    i.putExtra("key", "http://192.168.1.2/mypraxis/MyHealthCheck/src/" + url + "/");
                   // startActivity(i);
                }

        }}
    }

