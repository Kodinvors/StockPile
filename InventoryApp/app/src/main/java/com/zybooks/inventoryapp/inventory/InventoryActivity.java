package com.zybooks.inventoryapp.inventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zybooks.inventoryapp.R;

import java.util.ArrayList;


public class InventoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    InventoryDatabase myDB;
    ArrayList<String> item_id, item_name, item_quantity;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        myDB = new InventoryDatabase(InventoryActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_quantity = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(InventoryActivity.this, this, item_id, item_name, item_quantity);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_quantity.add(cursor.getString(2));
            }
        }
    }
}
