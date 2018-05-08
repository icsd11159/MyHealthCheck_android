package com.example.user.myhealthcheck;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class viewactivity extends AppCompatActivity implements Serializable {
    private TableRow row, row1, row2, row3, row4, row5, row6, row7;
    private TableLayout inflate;
    private ArrayList<Integer> input1 = new ArrayList<Integer>();
    private TextView txtcol1, txtcol3, txtcol4, txtcol5, txtcol6, txtcol7, txtcol8, textd, txtcola3, txtcola4, txtcola5, txtcola6, txtcola7, txtcola8;
    private String t1 = "Id Dr:" + "      ";
    private String t2 = "Εξέταση:" + "    ";
    private String t3 = "Είδος:" + "      ";
    private String t4 = "Αποτέλεσμα:" + " ";
    private String t5 = "Ημερομηνία:" + " ";
    private String t6 = "Σχόλια:" + "     ";
    private int id_e;
     ArrayList<Exam> examList;
 //   Bundle bundle;
    public void seti(int j){
        this.id_e=j;
    }
    public int geti(){
        return this.id_e;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewactivity);
        inflate = (TableLayout) findViewById(R.id.mytable);

        // bundle = getIntent().getExtras();
        Intent intent = getIntent();
    //   examList =  (ArrayList<Exam>)getIntent().getSerializableExtra("examlist");
        Bundle args = intent.getBundleExtra("BUNDLE");
        examList = (ArrayList<Exam>) args.getSerializable("examlist");
        id_e = intent.getIntExtra("id_e", 0);
        seti(id_e);
        input1.add(id_e);


        view();
      // j = getIntent().getStringExtra("id");
//Extract the data…


    }
    public void view(){


        for (int i = 0; i < examList.size(); i++) {

            if (examList.get(i).getId_e() == geti()) {
                seti(examList.get(i).getId_e());
                //  row = new TableRow(activity_examinations.this);
              //  row = new TableRow(this);
                row1 = new TableRow(this);
                row2 = new TableRow(this);
                row3 = new TableRow(this);
                row4 = new TableRow(this);
                row5 = new TableRow(this);
                row6 = new TableRow(this);
                row7 = new TableRow(this);
                txtcol1 = new TextView(this);


              //  txtcol1.setText(i);

                //row.addView(txtcol1);
               // inflate.addView(row);
                txtcola3 = new TextView(this);
                txtcola4 = new TextView(this);
                txtcola5 = new TextView(this);
                txtcola6 = new TextView(this);
                txtcola7 = new TextView(this);
                txtcola8 = new TextView(this);
                txtcol3 = new TextView(this);
                txtcol4 = new TextView(this);
                txtcol5 = new TextView(this);
                txtcol6 = new TextView(this);
                txtcol7 = new TextView(this);
                txtcol8 = new TextView(this);




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

            }}
            // String[] product = new String[jsonArray.length()];
            Button btn = new Button(viewactivity.this);
            btn.setId(geti());
            final int id_ = btn.getId();
            btn.setText("PDF for Exam: " + geti());
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            //linear.addView(btn, params);
        row7 = new TableRow(this);
            this.row7.addView(btn);
            inflate.addView(row7);
            btn = ((Button) findViewById(id_));
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {


                    // ViewPdf pdf = new ViewPdf();
                    // pdf.downloadPDF(examList.get(id_).getfile());
                    //Run the code in a different thread than the UI thread
                    //  Thread thread = new Thread() {
                    //   @Override
                    //public void run() {
                    Intent i = new Intent(getApplicationContext(), readapdf.class);
                    //    AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
                    //  String getrec=textView.getText().toString();

//Create the bundle
                    Bundle bundle = new Bundle();

//Add your data to bundle
                    bundle.putInt("value", geti());

//Add the bundle to the intent
                    i.putExtras(bundle);

//Fire that second activity
                    startActivity(i);
                    //  Intent v = new Intent(getApplicationContext(), readpdf.class);
                    // Integer url = examList.get(id_).getId_e();


                    // v.putExtra("no",  url );
                    // startActivity(v);
                }

            });
        }

    }




