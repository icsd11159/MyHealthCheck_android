package com.example.user.myhealthcheck;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class activity_examinations extends AppCompatActivity {

    ListView listView_amka,listView_idd,listView_type,listView_nameexam,listView_result,listView_date,listView_comments;
    ArrayList<Exam> examList=new ArrayList <Exam>();
    private ArrayList<String> input1 = new ArrayList<String>();
    private TableRow row,row1,row2,row3,row4,row5,row6;
    private TableLayout inflate;
    private TextView txtcol1, txtcol3,txtcol4,txtcol5,txtcol6,txtcol7,txtcol8,id_d , txtcola3,txtcola4,txtcola5,txtcola6,txtcola7,txtcola8;
    private String t1="Id Dr:      ";
    private String t2="Εξέταση:    ";
     private String t3="Είδος:      ";
    private String t4="Αποτέλεσμα: ";
    private String t5="Ημερομηνία: ";
    private String t6="Σχόλια:     ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinations);
        inflate = (TableLayout) findViewById(R.id.mytable);
        id_d = (TextView) findViewById(R.id.textdd);
        getJSON("http://192.168.1.2/mypraxis/MyHealthCheck/Api.php");
    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


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

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
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
            obj.getInt("id_d"),
             obj.getString("type"),
            obj.getString("name_exam"),
            obj.getString("result"),
           obj.getString("date"),
            obj.getString("comments")
            ));



            Context context = getApplicationContext();
            CharSequence text = "Hello toast! "+examList.size();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            row = new TableRow(activity_examinations.this);
            row1 = new TableRow(activity_examinations.this);
            row2 = new TableRow(activity_examinations.this);
            row3 = new TableRow(activity_examinations.this);
            row4 = new TableRow(activity_examinations.this);
            row5 = new TableRow(activity_examinations.this);
            row6 = new TableRow(activity_examinations.this);
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
                    txtcol3.setText(examList.get(i).getId_d());
                    txtcol4.setText(examList.get(i).getName_exam());
                    txtcol5.setText(examList.get(i).getType());
                    txtcol6.setText(examList.get(i).getResult());
                    txtcol7.setText(examList.get(i).getDate());
                    txtcol8.setText(examList.get(i).getComments());
                }
             else {
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
            this.row2.addView(txtcola4);
            this.row3.addView(txtcol4);
            inflate.addView(row3);
            this.row2.addView(txtcola5);
            this.row4.addView(txtcol5);
            inflate.addView(row4);
            this.row2.addView(txtcola6);
            this.row5.addView(txtcol6);
            inflate.addView(row5);
            this.row2.addView(txtcola8);
            this.row6.addView(txtcol8);
            inflate.addView(row6);

        }
    }
}