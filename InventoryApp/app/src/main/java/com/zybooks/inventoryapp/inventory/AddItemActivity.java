package com.zybooks.inventoryapp.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.inventoryapp.R;

public class AddItemActivity extends AppCompatActivity {

    private EditText mAddItem;
    private Button mSubmitButton;
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mAddItem = findViewById(R.id.itemToAdd);
        mSubmitButton = findViewById(R.id.submitItem);

        // EditText listener to enable and disable @+id/createAccountButton
        mAddItem.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {

                        item = mAddItem.getText().toString();

                        // enables and disables @+id/createAccountButton
                        mSubmitButton.setEnabled(!item.isEmpty());
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

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InventoryDatabase inventoryDB = new InventoryDatabase(AddItemActivity.this);
                inventoryDB.addItem(mAddItem.getText().toString());
            }
        });
    }
}
