package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

public class adultItems extends AppCompatActivity {
    // _____________________________________instance  variables_____________________________________
    private RecyclerView recyclerView;
    private FloatingActionButton home;
    private FloatingActionButton delete;
    private Button childrenButton;
    private Button bulkButton;
    private Button checkoutButton;

    String[] names = {"Adult Fish", "Adult Fish++", "Adult Shrimp", "Adult Shrimp++", "Adult Combo", "Adult Combo++"};
    int[] prices = {11, 13, 11, 13, 11, 16};
    int[] quantities = {0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // making the activity fullscreen
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }

        setContentView(R.layout.activity_adult_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // obtaining the quantities of each item in the cart
        String[] cartNames = Items.getItemNames();
        int[] cartQtys = Items.getItemQty();
        for (int i = 0; i < cartNames.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if (cartNames[i].equals(names[j])) {
                    quantities[j] = cartQtys[i];
                }
            }
        }

        // setting up the back/home button
        home = findViewById(R.id.fab);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
                finish();
            }
        });

        // setting up the clear cart button
        delete = findViewById(R.id.deleteButton);
        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Are you sure you want to delete all items?");
        dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Items.cartItems.clear();
                finish();
                startActivity(getIntent());
            }
        });
        dlgAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(getIntent());
            }
        });
        dlgAlert.setCancelable(true);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgAlert.create().show();
            }
        });

        // setting up the buttons directing user to another item category
        childrenButton = findViewById(R.id.childrenButton);
        childrenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), childItems.class));
                finish();
            }
        });
        bulkButton = findViewById(R.id.bulkButton);
        bulkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), bulkItems.class));
                finish();
            }
        });
        checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), checkout.class));
            }
        });

        // putting all the items into the recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        myAdaptorMenu adaptor = new myAdaptorMenu(this, names, prices, quantities);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
