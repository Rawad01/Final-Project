package com.lau.finalprojectmedical_report;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class SigninActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Username = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

    private void Login() {

        final String username = Username.getText().toString();
        final String password = Password.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Username.setError("Please enter your username");
            Username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Please enter your password");
            Password.requestFocus();
            return;
        }

        class userLogin extends AsyncTask<Void, Void, String> {
            ProgressBar progressBar;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);

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
                        //startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                        intent.putExtra("username", userJson.getString("username"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                reqHandler requestHandler = new reqHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return requestHandler.sendPostRequest("http://192.168.1.105/apis/login.php?apicall="+"signin", params);

            }
        }

        userLogin ul = new userLogin();
        ul.execute();
    }
}