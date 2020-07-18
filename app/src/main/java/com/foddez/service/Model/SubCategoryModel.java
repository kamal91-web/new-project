package com.foddez.service.Model;

import java.util.ArrayList;

public class SubCategoryModel {
    private String scid;
    private String categoryId;
    private String subCategoryName;
    private String subCatImage;
    private String businessId;
    private ArrayList<ProductModel> productList;

    public SubCategoryModel() {
    }

    public SubCategoryModel(String scid, String categoryId, String subCategoryName, String subCatImage, String businessId) {
        this.scid = scid;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
        this.subCatImage = subCatImage;
        this.businessId = businessId;
    }

    public String getScid() {
        return scid;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getSubCatImage() {
        return subCatImage;
    }

    public String getBusinessId() {
        return businessId;
    }

    public ArrayList<ProductModel> getProductList() {
        return productList;
    }
}
