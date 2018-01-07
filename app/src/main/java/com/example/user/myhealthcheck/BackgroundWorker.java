package com.example.user.myhealthcheck;

/**
 * Created by user on 19/12/2017.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
   Context context;
    AlertDialog alertDialog;
    DataDownloadListener dataDownloadListener;
    boolean success=false;
    String result;
   BackgroundWorker (Context ctx) {
       context = ctx;
    }
  public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
      this.dataDownloadListener = dataDownloadListener;
  }
    // Alert Dialog Manager
  //  AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
   // SessionManager session=new SessionManager( Signin.this);
    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String login_url = "http://192.168.1.5/mypraxis/MyHealthCheck/login.php";
        String register_url = "http://192.168.1.5/mypraxis/MyHealthCheck/register.php";

        if(type.equals("login")) {

            try {
                String user_name = params[1];
                String password = params[2];


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register")) {
            try {
                String user_name = params[1];
                String password = params[2];
                String name_user = params[3];
                String surname_user = params[4];
                String amka_user = params[5];
                String email_user = params[6];
                String sosn= params[7];



                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("name_user","UTF-8")+"="+URLEncoder.encode(name_user,"UTF-8")+"&"
                        +URLEncoder.encode("surname_user","UTF-8")+"="+URLEncoder.encode(surname_user,"UTF-8")+"&"
                        +URLEncoder.encode("amka_user","UTF-8")+"="+URLEncoder.encode(amka_user,"UTF-8")+"&"
                        +URLEncoder.encode("email_user","UTF-8")+"="+URLEncoder.encode(email_user,"UTF-8")+"&"
                        +URLEncoder.encode("sosn","UTF-8")+"="+URLEncoder.encode(sosn,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
       alertDialog.show();
       // Toast.makeText(context, "Wrong Username or Password!"+result, Toast.LENGTH_SHORT).show();
        if(result.equals("login not success")) {
            set_loginsuccess(false);
         //   Toast.makeText(context, "Wrong Username or Password!"+result, Toast.LENGTH_SHORT).show();
            dataDownloadListener.dataDownloadFailed();
           //alert.showAlertDialog(getApplicationContext(), "Login failed..", "Username/Password is incorrect", false);


        }else if(result.contains("register")){
            context.startActivity(new Intent(context, Signin.class));
            set_loginsuccess(false);

        }
        else
        {
            set_amka(result);

            set_loginsuccess(true);
            dataDownloadListener.dataDownloadedSuccessfully(true);

            context.startActivity(new Intent(context, Signin.class));

        }




    }
 public String get_amka(){
        return result;
    }
    public void set_amka(String result){
        this.result=result;
    }
    public void set_loginsuccess(boolean success){
        this.success=success;
    }
    public boolean get_loginsuccess(){
        return success;
    }

    public static interface DataDownloadListener {
        void dataDownloadedSuccessfully(Object data);
        void dataDownloadFailed();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}