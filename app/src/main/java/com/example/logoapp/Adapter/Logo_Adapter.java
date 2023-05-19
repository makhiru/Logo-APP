package com.example.logoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logoapp.Activity.Game_Activity;
import com.example.logoapp.Logo;
import com.example.logoapp.R;

import java.util.List;

public class Logo_Adapter extends RecyclerView.Adapter<Logo_Adapter.ViewHolder> {

    Context context;
    List<Logo> logolist;
    int i;
    SharedPreferences preferences;

    public Logo_Adapter(Context context, List<Logo> logolist, int i) {
        this.context = context;
        this.logolist = logolist;
        this.i = i;
        preferences = context.getSharedPreferences("mypref", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Logo_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.logo_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Logo_Adapter.ViewHolder holder, int position) {

        String status = preferences.getString("Level" + i + "Logo" + position, "h");

        if (status.equals("Win")) {
            holder.tick.setImageResource(R.drawable.tick);
            holder.imglogo.setAlpha(0.2f);
        }
        holder.imglogo.setImageResource(logolist.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, Game_Activity.class);

                in.putExtra("pos1", i);
                in.putExtra("pos2", holder.getAdapterPosition());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return logolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imglogo, tick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imglogo = itemView.findViewById(R.id.imglogo);
            tick = itemView.findViewById(R.id.tick);
        }
    }
}
