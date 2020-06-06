package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class myAdaptorMenu extends RecyclerView.Adapter<myAdaptorMenu.MyViewHolder> {

    String[] data1; // array of item names
    int[] data2; // array of item prices
    int[] data3; // array of item quantities
    Context context;

    private Button add;
    private Button minus;

    public myAdaptorMenu(Context getContext, String[] s1, int[] s2, int[] s3) {
        context = getContext;
        data1 = s1;
        data2 = s2;
        data3 = s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_menu, parent, false);

        add = view.findViewById(R.id.button2);
        minus = view.findViewById(R.id.button);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position] + "");
        holder.myText3.setText(data3[position] + "");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items.addNewItem(data1[position], data2[position], 1);
                data3[position]++;
                holder.myText3.setText(data3[position] + "");
            }
        }
        );
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items.removeItem(data1[position]);
                if (data3[position] > 0) {
                    data3[position]--;
                }
                holder.myText3.setText(data3[position] + "");
            }
        }
        );
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
