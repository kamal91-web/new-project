package com.foddez.service.Model;

public class CategoryModel {
    private int cid;
    private String category_name;
    private String image;

    public CategoryModel(int cid, String category_name, String category_image) {
        this.cid = cid;
        this.category_name = category_name;
        this.image = category_image;
    }

    public int getCid() {
        return cid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getImage() {
        return image;
    }
}
