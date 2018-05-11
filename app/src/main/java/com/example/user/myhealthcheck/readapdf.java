package com.example.user.myhealthcheck;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
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

public class readapdf extends AppCompatActivity {
    SessionManager session;
    ArrayList<pdfname> pdfList=new ArrayList <pdfname>();
    // private int j = 0;
    //  private Boolean isnew = true;
private int value;
private int id;
private int open=0;
    private int id_name;
    String name_pdf;

private void setV(int value){
    this.value=value;
}
    private int getV(){
        return this.value;
    }
    // private ArrayList<String> input1 = new ArrayList<String>();
    private ArrayList<String> input2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SessionManager s = new SessionManager(readapdf.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readapdf);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        int value = bundle.getInt("value");
        setV(value);

    //    Toast.makeText(getApplicationContext(), "is value : "+ value,Toast.LENGTH_SHORT).show();

        getJSON("http://192.168.1.2/mypraxis/MyHealthCheck/pdfname.php");
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
             //   Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
                    session = new SessionManager(readapdf.this);
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
       // Toast.makeText(getApplicationContext(), "is j : "+ jsonArray.length(),Toast.LENGTH_SHORT).show();
        for ( int j = 0; j < jsonArray.length(); j++ ) {

            JSONObject objpdf = jsonArray.getJSONObject(j);
            //adding the product to product list
            input2.add(String.valueOf(j));
            pdfList.add(new pdfname(
                    objpdf.getString("name"),
                    objpdf.getInt("id_exam")
            ));

            if(pdfList.get(j).getId_e()==getV()) {




                name_pdf=pdfList.get(j).getname();

                id_name= pdfList.get(j).getId_e();
                id=getV();
                open (id_name,name_pdf);
               // open=1;
              //  final TextView  myClickableUrl = (TextView)findViewById(R.id.TextView1);
            //  myClickableUrl.setText("http://192.168.1.2/mypraxis/MyHealthCheck/src/" + id_name + "/"+name_pdf);

              // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.2/mypraxis/MyHealthCheck/src/" + id_name + "/"+name_pdf));
              //  startActivity(browserIntent);
               // Intent intent = new Intent(Intent.ACTION_VIEW);

           //     intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + "http://192.168.1.2/mypraxis/MyHealthCheck/src/" + id_name + "/"+name_pdf), "text/html");

              //  startActivity(intent);
                //String html = "My link is <a href=\"http://192.168.1.2/mypraxis/MyHealthCheck/src/\" + id_name + \"/\"+name_pdf>here</a>";
               // myClickableUrl.setClickable(true);
              //  myClickableUrl .setText(Html.fromHtml(html));
              //  Linkify.addLinks("http://192.168.1.2/mypraxis/MyHealthCheck/src/" + id_name + "/"+name_pdf, Linkify.WEB_URLS);
             //   Toast.makeText(getApplicationContext(), "is new: "+ getV(), Toast.LENGTH_SHORT).show();
              ////  WebView webView=(WebView)findViewById(R.id.webView1);
               // webView.getSettings().setJavaScriptEnabled(true);
              ////  webView.setWebViewClient(new Callback());
              //  String url="http://192.168.1.2/mypraxis/MyHealthCheck/src/" + id_name + "/"+name_pdf;
              //  webView.loadUrl("https://docs.google.com/viewer?url=" + url);
            }

        }

         //   Toast.makeText(getApplicationContext(), "is new: "+ getV(), Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(), "j: " + pdfList.get(j).getId_e(), Toast.LENGTH_SHORT).show();


        }

    public void open (int id_name,String name_pdf){
        open=0;
        Intent ip = new Intent(getApplicationContext(), pdf_open.class);
        String url = String.valueOf(id_name);
        Toast.makeText(getApplicationContext(), "is value : "+name_pdf+" url: "+url,Toast.LENGTH_SHORT).show();
        //  Toast.makeText(view.getContext(), "Button clicked index = " + session.ipaddress(), Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse("http://192.168.1.2/mypraxis/MyHealthCheck/app/src/asset/"+name_pdf); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        ip.putExtra("key", "http://192.168.1.2/mypraxis/MyHealthCheck/app/src/"+name_pdf);
        startActivity(ip);

    }

}



