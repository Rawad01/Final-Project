package com.lau.finalprojectmedical_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Email;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Preferences.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, SettingsActivity.class));
            return;
        }

        Username = (EditText) findViewById(R.id.Username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSignup();
            }
        });

        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, SigninActivity.class));
            }
        });

    }

    private void UserSignup() {
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
                        //startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                        intent.putExtra("username", userJson.getString("username"));
                        startActivity(intent);

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
    }

}