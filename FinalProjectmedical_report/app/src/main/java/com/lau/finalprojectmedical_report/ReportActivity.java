package com.lau.finalprojectmedical_report;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ReportActivity extends AppCompatActivity {
    private ListView listView;
    private RequestQueue requestQueue;
    ArrayList<String> report = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

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


            }
        });
    }
    private void jsonParse() {
        String url = "http://192.168.1.105/apis/get_medical_report.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("reports");
                            for (int i=0; i <jsonArray.length(); i++){

                                JSONObject medical_report = jsonArray.getJSONObject(i);
                                String full_name= medical_report.getString("full_name");
                                int age = medical_report.getInt("age");
                                String phone_number = medical_report.getString("phone_number");
                                String address = medical_report.getString("address");
                                String doctor_name = medical_report.getString("doctor_name");
                                String reasons_for_assessment = medical_report.getString("reasons_for_assessment");
                                String medicines_reffered = medical_report.getString("medicines_reffered");
                                String family_medical_history = medical_report.getString("family_medical_history");
                                String past_medical_history = medical_report.getString("past_medical_history");
                                String examinations_findings = medical_report.getString("examinations_findings");
                                String signature = medical_report.getString("signature");
                                String date = medical_report.getString("date");

                                report.add(full_name);
                                report.add(Integer.toString(age));
                                report.add(phone_number);
                                report.add(address);
                                report.add(doctor_name);
                                report.add(reasons_for_assessment);
                                report.add(medicines_reffered);
                                report.add(family_medical_history);
                                report.add(past_medical_history);
                                report.add(examinations_findings);
                                report.add(signature);
                                report.add(date);


                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}