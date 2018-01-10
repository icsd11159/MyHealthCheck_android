package com.example.user.myhealthcheck;
import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ViewPdf extends Activity {
     Context context;
    // Progress dialog
    private ProgressDialog pDialog;
    ViewPdf (Context ctx) {
       context = ctx;
    }



    public void downloadPDF(String namepdf)
    {

            Toast.makeText(context,"context:" +namepdf, Toast.LENGTH_SHORT).show();
            new DownloadFile().execute("http://8d2a7219.ngrok.io/mypraxis/MyHealthCheck/src/uploads/" + namepdf, namepdf);
        Toast.makeText(context, "con path:" + Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG).show();

    }

    public void viewPDF(View view,String namepdf)
    {


            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + namepdf);  // -> filename = maven.pdf
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }


    }

    private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
            String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
          //  hidepDialog();
            Toast.makeText(context, "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }


   // private void showpDialog() {
    //    if (!pDialog.isShowing())
      //      pDialog.show();
   // }

  //  private void hidepDialog() {
     //   if (pDialog.isShowing())
      //      pDialog.dismiss();
  //  }

}