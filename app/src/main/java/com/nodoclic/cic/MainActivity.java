package com.nodoclic.cic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.nodoclic.cic.clases.RsLogin;
import com.nodoclic.cic.servicios.Servicio;
import com.nodoclic.cic.util.CONST;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValidation();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                //Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                //startActivity(intent);
            }
        });
    }




    public void setValidation() {
        // Check for a valid email address.

        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        /*
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }*/

        // Check for a valid password.

        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 2) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isEmailValid && isPasswordValid) {
            ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                    "Espere...", true);
            try {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        RsLogin rsLogin = Servicio._login(email.getText().toString(), password.getText().toString(), context);
                        Log.d("resp", rsLogin.toString());
                        dialog.cancel();
                        if(rsLogin.getType().equals(CONST.OK_RESPONSE)){
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            } catch (Exception e) {
                System.err.println("tasks interrupted");
                Toast.makeText(getApplicationContext(), "1:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
            }
        }

    }

}