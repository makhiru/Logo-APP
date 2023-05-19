package com.example.logoapp;

import java.util.ArrayList;
import java.util.List;

public class Logo_Class {

    public static final List<List<Logo>> LOGO_CLASS = new ArrayList<>();
    List<Logo> level1 = new ArrayList<>();
    List<Logo> level2 = new ArrayList<>();
    List<Logo> level3 = new ArrayList<>();
    List<Logo> level4 = new ArrayList<>();
    List<Logo> level5 = new ArrayList<>();
    List<Logo> level6 = new ArrayList<>();

    public Logo_Class() {
        level1.add(new Logo(R.drawable.adidas, "Adidas"));
        level1.add(new Logo(R.drawable.facebook, "Facebook"));
        level1.add(new Logo(R.drawable.instagram, "Instagram"));
        level1.add(new Logo(R.drawable.skype, "SkyPe"));
        level1.add(new Logo(R.drawable.snapchat, "Snapchat"));

        level2.add(new Logo(R.drawable.abc, "Abc"));
        level2.add(new Logo(R.drawable.ask, "Ask"));
        level2.add(new Logo(R.drawable.telegram, "telegram"));
        level2.add(new Logo(R.drawable.tnt, "tnt"));
        level2.add(new Logo(R.drawable.google, "google"));

        level3.add(new Logo(R.drawable.honda, "honda"));
        level3.add(new Logo(R.drawable.ing, "ing"));
        level3.add(new Logo(R.drawable.landrover, "landrover"));
        level3.add(new Logo(R.drawable.lexmark, "lexmark"));
        level3.add(new Logo(R.drawable.linkedin, "linkedin"));

        level4.add(new Logo(R.drawable.maestro, "maestro"));
        level4.add(new Logo(R.drawable.marlboro, "marlboro"));
        level4.add(new Logo(R.drawable.paypal, "paypal"));
        level4.add(new Logo(R.drawable.puma, "puma"));
        level4.add(new Logo(R.drawable.peugeot, "peugeot"));

        level5.add(new Logo(R.drawable.starbucks, "starbucks"));
        level5.add(new Logo(R.drawable.whatsapp, "whatsapp"));
        level5.add(new Logo(R.drawable.youtube, "youtube"));
        level5.add(new Logo(R.drawable.pinterest, "pinterest"));
        level5.add(new Logo(R.drawable.snapchat, "snapchat"));

        level6.add(new Logo(R.drawable.starbucks, "starbucks"));
        level6.add(new Logo(R.drawable.whatsapp, "whatsapp"));
        level6.add(new Logo(R.drawable.youtube, "youtube"));
        level6.add(new Logo(R.drawable.pinterest, "pinterest"));
        level6.add(new Logo(R.drawable.snapchat, "snapchat"));

        LOGO_CLASS.add(level1);
        LOGO_CLASS.add(level2);
        LOGO_CLASS.add(level3);
        LOGO_CLASS.add(level4);
        LOGO_CLASS.add(level5);
        LOGO_CLASS.add(level6);
    }
}
