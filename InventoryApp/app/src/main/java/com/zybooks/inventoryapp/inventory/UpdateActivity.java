package com.zybooks.inventoryapp.inventory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.inventoryapp.R;

public class UpdateActivity extends AppCompatActivity{

    EditText item_input, quantity_input;
    Button update_button, delete_button;
    String id, item, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_input = findViewById(R.id.item_update);
        quantity_input = findViewById(R.id.quantity_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Call
        getAndSetIntentData();

        //Set Actionbar title after getAndSetData()
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle(item);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //then call
                InventoryDatabase myDB = new InventoryDatabase(UpdateActivity.this);
                myDB.updateQuantity(id, item, quantity);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("id")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            item = getIntent().getStringExtra("title");
            quantity = getIntent().getStringExtra("pages");

            //Setting Intent Data
            item_input.setText(item);
            quantity_input.setText(quantity);
        }
        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete " + item + " ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                InventoryDatabase myDB = new InventoryDatabase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}
