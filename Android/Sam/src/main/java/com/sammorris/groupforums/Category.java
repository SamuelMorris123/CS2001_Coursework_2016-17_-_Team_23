package com.sammorris.groupforums;

/**
 * Created by Sam Morris on 3/14/2017.
 */

public class Category {

    private String  Title;
    private String Desc;
    private String image;

    public Category(){

    }

    public Category(String title, String image, String desc) {
        this.Title = title;
        this.image = image;
        this.Desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


}
