package com.lau.finalprojectmedical_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class addreportActivity extends AppCompatActivity{

    private EditText fullName;
    private EditText age;
    private EditText phone_num;
    private EditText address;
    private EditText dr_name;
    private EditText reason_for_ass;
    private EditText medicines_ref;
    private EditText family_mh;
    private EditText past_mh;
    private EditText examination_findings;
    private EditText signature;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreport);

        fullName = (EditText) findViewById(R.id.full_name);
        age = (EditText) findViewById(R.id.age);
        phone_num = (EditText) findViewById(R.id.phone_num);
        address = (EditText) findViewById(R.id.address);
        dr_name = (EditText) findViewById(R.id.dr_name);
        reason_for_ass = (EditText) findViewById(R.id.res_for_ass);
        medicines_ref = (EditText) findViewById(R.id.med_hst);
        family_mh = (EditText) findViewById(R.id.family_mh);
        past_mh = (EditText) findViewById(R.id.past_mh);
        examination_findings = (EditText) findViewById(R.id.exam_find);
        signature = (EditText) findViewById(R.id.signature);

        findViewById(R.id.Submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReport();
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(addreportActivity.this, SettingsActivity.class));
            }
        });
    }
    private void addNewReport() {
        final String Full_name = fullName.getText().toString().trim();
        final String Age = age.getText().toString().trim();
        final String Phone_num = phone_num.getText().toString().trim();
        final String Address = address.getText().toString().trim();
        final String doctor_name = dr_name.getText().toString().trim();
        final String reasons_for_assessment = reason_for_ass.getText().toString().trim();
        final String med_ref = medicines_ref.getText().toString().trim();
        final String family_medH = family_mh.getText().toString().trim();
        final String past_medH = past_mh.getText().toString().trim();
        final String ex_findings = examination_findings.getText().toString().trim();
        final String Signature = signature.getText().toString().trim();

        if (TextUtils.isEmpty(Full_name)) {
            fullName.setError("Please enter full name");
            fullName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Age)) {
            age.setError("Please enter your age");
            age.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Phone_num)) {
            phone_num.setError("Enter your phone number");
            phone_num.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Address)) {
            address.setError("Please enter your address");
            address.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(doctor_name)) {
            dr_name.setError("Please enter your doctor's name");
            dr_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(reasons_for_assessment)) {
            reason_for_ass.setError("Please enter the reasons for your assessment");
            reason_for_ass.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(med_ref)) {
            medicines_ref.setError("Please enter your reffered medicines");
            medicines_ref.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(family_medH)) {
            family_mh.setError("Please enter family medical history");
            family_mh.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(past_medH)) {
            past_mh.setError("Please enter your past medical history");
            past_mh.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ex_findings)) {
            examination_findings.setError("Please enter examination findings");
            examination_findings.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Signature)) {
            signature.setError("Please enter your signature");
            signature.requestFocus();
            return;
        }

        class NewReport extends AsyncTask<Void, Void, String> {

            //private ProgressBar pb;
            @Override
            protected String doInBackground(Void... voids) {

                reqHandler requestHandler = new reqHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("username", Full_name);
                params.put("age", Age);
                params.put("phone_number", Phone_num);
                params.put("address", Address);
                params.put("doctor_name", doctor_name);
                params.put("reasons_for_assessment", reasons_for_assessment);
                params.put("medicines_reffered", med_ref);
                params.put("family_medical_history", family_medH);
                params.put("past_medical_history", past_medH);
                params.put("examinations_findings", ex_findings);
                params.put("signature", Signature);
                params.put("date", "1/11/2020");
                return requestHandler.sendPostRequest("http://192.168.1.105/apis/add_report.php?apicall="+"addreport", params);

            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //pb = (ProgressBar) findViewById(R.id.progressBar);
                //pb.setVisibility(View.VISIBLE);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //pb.setVisibility(View.GONE);

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject userJson = obj.getJSONObject("user");
                        users user = new users(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("date")
                        );
                        Preferences.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        NewReport addReport = new NewReport();
        addReport.execute();
    }
}
