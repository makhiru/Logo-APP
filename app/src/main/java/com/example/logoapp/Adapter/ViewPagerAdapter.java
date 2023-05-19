package com.example.logoapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.logoapp.Logo_Class;
import com.example.logoapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    Context context;
    ViewPager2 viewPager;
    boolean start;

    String logo_ans, status;
    int pos1, pos2, completedlogo, thislevellogo, currentLevel;
    ArrayList<Character> chars = new ArrayList<>();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ViewPagerAdapter(Context context, int pos1, int pos2, ViewPager2 viewPager, boolean start) {
        this.context = context;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.viewPager = viewPager;
        this.start = start;

        preferences = context.getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.game_layout, parent, false));
        playGame(holder, pos2);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                playGame(holder, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Logo_Class.LOGO_CLASS.get(pos1).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgchoose;
        Button btnremove, btnclear, btnleft, btnright, btnnext;
        TextView[] ans = new TextView[9];
        TextView[] btn = new TextView[14];
        LinearLayout llwin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnremove = itemView.findViewById(R.id.btnremove);
            btnclear = itemView.findViewById(R.id.btnclear);
            btnleft = itemView.findViewById(R.id.btnleft);
            btnright = itemView.findViewById(R.id.btnright);
            btnnext = itemView.findViewById(R.id.btnnext);
            imgchoose = itemView.findViewById(R.id.imgchhose);
            llwin = itemView.findViewById(R.id.llwin);

            for (int i = 0; i < 9; i++) {
                int id = context.getResources().getIdentifier("ans" + i, "id", context.getPackageName());
                ans[i] = itemView.findViewById(id);
            }

            for (int i = 0; i < 14; i++) {
                int id = context.getResources().getIdentifier("btn" + i, "id", context.getPackageName());
                btn[i] = itemView.findViewById(id);
            }
        }
    }

    public void playGame(ViewPagerAdapter.ViewHolder holder, int position) {

        pos2 = position;

        completedlogo = preferences.getInt("completedlogo", 0);
        thislevellogo = preferences.getInt("Level" + pos1 + "Tlogos", 0);
        status = preferences.getString("Level" + pos1 + "Logo" + pos2, "h");
        currentLevel = preferences.getInt("CurrentLevel", 0);

        holder.imgchoose.setImageResource(Logo_Class.LOGO_CLASS.get(pos1).get(pos2).getImg());

        logo_ans = Logo_Class.LOGO_CLASS.get(pos1).get(pos2).getName().toUpperCase();
        System.out.println(logo_ans);

        chars.clear();

        for (int i = 0; i < logo_ans.length(); i++) {
            chars.add(logo_ans.charAt(i));
            System.out.println(chars.get(i));
        }

        for (int count = logo_ans.length(); count < 14; count++) {
            char r = (char) (new Random().nextInt(91 - 65) + 65);
            chars.add(r);
        }

        Collections.shuffle(chars);

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            holder.ans[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 14; i++) {
                        holder.btn[i].setClickable(true);
                    }
                    if (!holder.ans[finalI].getText().toString().isEmpty()) {
                        for (int j = 0; j < 14; j++) {
                            if (holder.ans[finalI].getText().toString().equals(holder.btn[j].getText().toString())) {
                                if (holder.btn[j].getVisibility() == View.INVISIBLE) {
                                    holder.btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        holder.ans[finalI].setText("");
                    }
                }
            });
        }

        for (int i = 0; i < 9; i++) {
            if (i >= logo_ans.length()) {
                holder.ans[i].setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < 14; i++) {
            holder.btn[i].setText(chars.get(i).toString());
            int finali = i;

            holder.btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String ch = holder.btn[finali].getText().toString();

                    for (int j = 0; j < 9; j++) {

                        if (holder.ans[j].getText().toString().isEmpty()) {
                            holder.ans[j].setText(ch);

                            holder.btn[finali].setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                    win(holder);
                }
            });
        }

        if (status.equals("Win")) {
            winScreen(holder);
        } else {
            defaultScreen(holder);
        }

        holder.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setClickable(true);
                }

                for (int i = 8; i >= 0; i--) {
                    if (!holder.ans[i].getText().toString().isEmpty()) {

                        for (int j = 0; j < 14; j++) {
                            if (holder.ans[i].getText().toString().equals(holder.btn[j].getText().toString())) {
                                if (holder.btn[j].getVisibility() == View.INVISIBLE) {
                                    holder.btn[j].setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        holder.ans[i].setText("");
                        break;
                    }

                }
            }
        });

        holder.btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 9; i++) {
                    holder.ans[i].setText("");
                }
                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setVisibility(View.VISIBLE);
                    holder.btn[i].setClickable(true);
                }
            }
        });

        holder.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != (Logo_Class.LOGO_CLASS.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
                }
            }
        });

        holder.btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != (Logo_Class.LOGO_CLASS.get(pos1).size() - 1)) {
                    viewPager.setCurrentItem(position + 1, true);
                }
            }
        });

        holder.btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0)
                    viewPager.setCurrentItem(position - 1, true);
            }
        });
    }

    public void win(ViewPagerAdapter.ViewHolder holder) {
        int j = 0;
        int k = 0;

        for (int i = 0; i < logo_ans.length(); i++) {
            if (holder.ans[i].getText().toString().isEmpty()) {
                j++;
            }
        }

        if (j == 0) {
            for (int i = 0; i < logo_ans.length(); i++) {
                if (holder.ans[i].getText().toString().charAt(0) != logo_ans.charAt(i)) {
                    k++;
                }
            }
            if (k == 0) {
                completedlogo++;
                thislevellogo++;
                editor.putInt("CompletedLogos", completedlogo);
                editor.putInt("Level" + pos1 + "Tlogos", thislevellogo);
                editor.putString("Level" + pos1 + "Logo" + pos2, "Win");
                if (thislevellogo >= 3 && pos1 > currentLevel) {
                    editor.putInt("CurrentLevel", pos1);
                    editor.putString("Level" + pos1, "Done");
                }
                editor.commit();
                winScreen(holder);

            } else {

                for (int i = 0; i < 14; i++) {
                    holder.btn[i].setClickable(false);
                }

                Dialog dialog = new Dialog(context);
                dialog.setCancelable(false);
                dialog.create();
                dialog.setContentView(R.layout.win_layout);
                Button btnok = dialog.findViewById(R.id.btnok);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }
    }

    public void winScreen(ViewPagerAdapter.ViewHolder holder) {
        for (int i = 0; i < logo_ans.length(); i++) {
            holder.ans[i].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < 14; i++) {
            holder.btn[i].setVisibility(View.INVISIBLE);
        }
        holder.btnremove.setVisibility(View.INVISIBLE);
        holder.btnclear.setVisibility(View.INVISIBLE);
        holder.llwin.setVisibility(View.VISIBLE);

    }

    public void defaultScreen(ViewPagerAdapter.ViewHolder holder) {
        for (int i = 0; i < logo_ans.length(); i++) {
            holder.ans[i].setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < 14; i++) {
            holder.btn[i].setVisibility(View.VISIBLE);
        }
        holder.btnclear.setVisibility(View.VISIBLE);
        holder.btnremove.setVisibility(View.VISIBLE);
        holder.imgchoose.setImageResource(Logo_Class.LOGO_CLASS.get(pos1).get(pos2).getImg());

        holder.llwin.setVisibility(View.INVISIBLE);
    }
}
