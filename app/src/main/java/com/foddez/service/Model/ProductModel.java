package com.foddez.service.Model;

public class ProductModel {
    private String pid,cat_id,sub_cat_id,business_id,product_name, product_desc,product_unit, item_sgst,item_cgst, product_image,product_unit_name,item_stock;
    private double product_price, product_offer_price;

    public ProductModel(String pid, String cat_id, String sub_cat_id, String business_id, String product_name, String product_desc, String product_unit, String item_sgst, String item_cgst, String product_image, String product_unit_name, String item_stock,  double product_price, double product_offer_price) {
        this.pid = pid;
        this.cat_id = cat_id;
        this.sub_cat_id = sub_cat_id;
        this.business_id = business_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_unit = product_unit;
        this.item_sgst = item_sgst;
        this.item_cgst = item_cgst;
        this.product_image = product_image;
        this.product_unit_name = product_unit_name;
        this.item_stock = item_stock;
        this.product_price = product_price;
        this.product_offer_price = product_offer_price;
    }

    public String getPid() {
        return pid;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public String getItem_sgst() {
        return item_sgst;
    }

    public String getItem_cgst() {
        return item_cgst;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_unit_name() {
        return product_unit_name;
    }

    public String getItem_stock() {
        return item_stock;
    }

    public double getProduct_price() {
        return product_price;
    }

    public double getProduct_offer_price() {
        return product_offer_price;
    }

}

