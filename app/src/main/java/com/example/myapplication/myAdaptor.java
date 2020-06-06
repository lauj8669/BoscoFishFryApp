package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class myAdaptor extends RecyclerView.Adapter<myAdaptor.MyViewHolder> {

    String[] data1;
    int[] data2;
    int[] data3;
    Context context;

    public myAdaptor(Context getContext, String[] s1, int[] s2, int[] s3) {
        context = getContext;
        data1 = s1;
        data2 = s2;
        data3 = s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position] + "");
        holder.myText3.setText("Qty: " + data3[position] + "");

        /*
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items.cartItems.add(new Items.EachItem(data1[position], data2[position], data3[position]));
                Snackbar.make(v, data1[position] + " added!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        if (data2 == null) {
            return 0;
        }
        return data2.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2, myText3;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myText3 = itemView.findViewById(R.id.myText3);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
