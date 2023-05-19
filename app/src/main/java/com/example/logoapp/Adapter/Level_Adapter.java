package com.example.logoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logoapp.Activity.Base_Level_Activity;
import com.example.logoapp.Logo;
import com.example.logoapp.Logo_Class;
import com.example.logoapp.R;

import java.util.List;

public class Level_Adapter extends RecyclerView.Adapter<Level_Adapter.ViewHolder> {

    Context context;
    List<List<Logo>> logolist;
    SharedPreferences preferences;

    public Level_Adapter(Context context, List<List<Logo>> logolist) {
        this.context = context;
        this.logolist = logolist;
        preferences = context.getSharedPreferences("mypref", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Level_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.level_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Level_Adapter.ViewHolder holder, int position) {

        int cLevel = preferences.getInt("Level" + position + "Tlogos", 0);
        int lastlevel = preferences.getInt("CurrentLevel", 0);
        String status = preferences.getString("Level" + position, "h");

        holder.txtlevel.setText("Level " + (position + 1));

        if (position == lastlevel || position == (lastlevel + 1) || status.equals("Done") || position == 0) {
            holder.imglock.setBackgroundResource(R.drawable.red_btn);
            holder.imglock.setText(cLevel + "/" + logolist.get(position).size());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Base_Level_Activity.class);
                    intent.putExtra("i", holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        } else {
            holder.imglock.setBackgroundResource(R.drawable.lockk);
        }

    }

    @Override
    public int getItemCount() {
        return logolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView imglock;
        TextView txtlevel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imglock = itemView.findViewById(R.id.txtlock);
            txtlevel = itemView.findViewById(R.id.txtlevel);
        }
    }
}
