package com.zybooks.inventoryapp.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.inventoryapp.MainActivity;
import com.zybooks.inventoryapp.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mCreateAccount;
    private String newUsername;
    private String newPassword;
    UserCredentialsDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mUsername = findViewById(R.id.newUsernameEditText);
        mPassword = findViewById(R.id.newPasswordEditText);
        mCreateAccount = findViewById(R.id.createAccountButton);
        mDB = new UserCredentialsDatabase(this);

        // EditText listener to enable and disable @+id/createAccountButton
        mUsername.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {

                        newUsername = mUsername.getText().toString();
                        newPassword = mPassword.getText().toString();

                        // enables and disables @+id/createAccountButton
                        mCreateAccount.setEnabled(!newUsername.isEmpty() && !newPassword.isEmpty());
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

                        newUsername = mUsername.getText().toString();
                        newPassword = mPassword.getText().toString();

                        // enables and disables @+id/createAccountButton
                        mCreateAccount.setEnabled(!newUsername.isEmpty() && !newPassword.isEmpty());
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

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUsername = mUsername.getText().toString().trim();
                newPassword = mPassword.getText().toString().trim();

                Boolean checkUser = mDB.checkUsername(newUsername);
                if (!checkUser) {
                    Boolean insert = mDB.addCredentials(newUsername, newPassword);
                    if(insert) {
                        Toast.makeText(CreateAccountActivity.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(CreateAccountActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(CreateAccountActivity.this, "User Already Exists!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
