package com.program.myocabona.Utils;

public class ItemObjects {
    private int img;
    private String title;
    private String desc;

    public ItemObjects(int img, String title, String desc) {
        this.img = img;
        this.title = title;
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
