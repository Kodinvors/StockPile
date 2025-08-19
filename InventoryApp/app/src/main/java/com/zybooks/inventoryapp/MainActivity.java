package com.zybooks.inventoryapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zybooks.inventoryapp.inventory.InventoryActivity;
import com.zybooks.inventoryapp.login.CreateAccountActivity;
import com.zybooks.inventoryapp.login.UserCredentialsDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mSignIn;
    private String username;
    private String password;
    UserCredentialsDatabase DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.Username);
        mPassword = findViewById(R.id.Password);
        mSignIn = findViewById(R.id.SignIn);
        DB = new UserCredentialsDatabase(this);

        // EditText listener to enable and disable @+id/SignIn
        mUsername.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {

                        username = mUsername.getText().toString();
                        password = mPassword.getText().toString();

                        // enables and disables @+id/SignIn
                        mSignIn.setEnabled(!username.isEmpty() && !password.isEmpty());
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                    }
                }
        );

        mPassword.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {

                        username = mUsername.getText().toString().trim();
                        password = mPassword.getText().toString().trim();

                        // enables and disables @+id/SignIn
                        mSignIn.setEnabled(!username.isEmpty() && !password.isEmpty());
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                    }
                }
        );
    }

    private void sendSMS() {

        String phoneNumber = "2088743000"; // Replace with the recipient's phone number

        String message = "Hello, this is a test SMS!"; // Replace with your desired message



        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(phoneNumber, null, message, null, null);

    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        // Check if permission is already granted

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) ==
                PackageManager.PERMISSION_GRANTED) {

            // Permission is granted, proceed with sending SMS messages

            sendSMS();

        } else {

            // Permission is not granted, request it from the user

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, 1);

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Permission granted, proceed with sending SMS messages

                sendSMS();

            } else {

                // Permission denied, continue without SMS messaging notification feature

                // Handle this case as per your application's requirements
                Toast.makeText(this, "Permission denied, cannot send SMS!",
                        Toast.LENGTH_SHORT).show();

            }

        }

    }

    public void onSignInClick(View view) {
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUsername.getText().toString().trim();
                password = mPassword.getText().toString().trim();

                Boolean checkUserPass = DB.checkUsernamePassword(username, password);
                if (checkUserPass) {
                    Toast.makeText(MainActivity.this, "Sign-in Successful!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(MainActivity.this,
                            "Login Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onCreateAccountClick(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}