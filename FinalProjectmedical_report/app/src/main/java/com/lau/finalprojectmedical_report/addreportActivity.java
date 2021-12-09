package com.lau.finalprojectmedical_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


public class addreportActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreport);
    }
    /*private void UserSignup() {
        final String username = Username.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Username.setError("Please enter username");
            Username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Email.setError("Please enter your email");
            Email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Enter a valid email");
            Email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Enter a password");
            Password.requestFocus();
            return;
        }

        class UserRegister extends AsyncTask<Void, Void, String> {

            private ProgressBar pb;
            @Override
            protected String doInBackground(Void... voids) {

                reqHandler requestHandler = new reqHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return requestHandler.sendPostRequest("http://192.168.1.105/apis/signup.php?apicall="+"signup", params);

            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pb = (ProgressBar) findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pb.setVisibility(View.GONE);

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject userJson = obj.getJSONObject("user");
                        users user = new users(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("email")
                        );
                        Preferences.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        UserRegister userReg = new UserRegister();
        userReg.execute();
    }*/
}
