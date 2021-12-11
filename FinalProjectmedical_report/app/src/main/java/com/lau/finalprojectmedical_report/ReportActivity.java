package com.lau.finalprojectmedical_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportActivity extends AppCompatActivity {
    private ListView listView;
    private RequestQueue requestQueue;
    ArrayList<String> report = new ArrayList<String>();
    String USER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle bundle = getIntent().getExtras();
        USER = bundle.getString("username");
        Log.i("USER LOGGED IN: ", USER);
        listView = (ListView) findViewById(R.id.list_view);

        findViewById(R.id.add_new_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ReportActivity.this, addreportActivity.class));
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ReportActivity.this, SettingsActivity.class));
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, report);
        listView.setAdapter(adapter);

        findViewById(R.id.report_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReport();
            }
        });
    }
    private void getReport() {

        class report extends AsyncTask<Void, Void, String> {
            //ProgressBar progressBar;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressBar = (ProgressBar) findViewById(R.id.progressBar);
               //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(View.GONE);

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject reportJson = obj.getJSONObject("user");
                        int ID = reportJson.getInt("id");
                        String NAME = reportJson.getString("username");
                        String AGE = reportJson.getString("age");
                        String NUM = reportJson.getString("phone_num");
                        String ADDRESS = reportJson.getString("address");
                        String DR_NAME = reportJson.getString("dr_name");
                        String REASON = reportJson.getString("reasons_ass");
                        String MED_REF = reportJson.getString("med_reff");
                        String FAMILY_MH = reportJson.getString("family_mh");
                        String PAST_MH = reportJson.getString("past_mh");
                        String EXAM_FIND = reportJson.getString("examinations_find");
                        String SIG = reportJson.getString("signature");
                        String DATE = reportJson.getString("date");

                        report.add("ID: " + Integer.toString(ID));
                        report.add("Full name of patient: " + NAME);
                        report.add("Age of patient: " + AGE);
                        report.add("Phone number of patient: " + NUM);
                        report.add("Address of patient: " + ADDRESS);
                        report.add("patient's doctor name: " + DR_NAME);
                        report.add("Reasons for assessment: " + REASON);
                        report.add("Medicines being taken:" + MED_REF);
                        report.add("Family medical history: " + FAMILY_MH);
                        report.add("Patient past medical history: " + PAST_MH);
                        report.add("Examination findings: " + EXAM_FIND);
                        report.add("patient signature: " + SIG);
                        report.add("Date of assessment: " + DATE);

                        listView = (ListView)findViewById(R.id.list_view);

                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, report);
                        listView.setAdapter(adapter);

                        //startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                        //Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                        //intent.putExtra("username", reportJson.getString("username"));
                        //startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "user not found please add a report", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                reqHandler requestHandler = new reqHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("username", USER);
                //params.put("password", password);
                return requestHandler.sendPostRequest("http://192.168.1.105/apis/get_medical_report.php?apicall="+"medicalReport", params);

            }
        }

        report rep = new report();
        rep.execute();
    }

}