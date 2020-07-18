package com.foddez.service.Model;

public class CartItemModel {
    private String business_id;
    private String product_id;
    private String category_id;
    private String subcategory_id;
    private String product_name;
    private String product_description;
    private String product_unit;
    private String product_sgst;
    private String product_cgst;
    private String product_image;
    private String product_unit_name;
    private String product_stock;
    private String product_price;
    private String order_qty;
    private String offer_id;
    private String offer_price;
    private String offer_percentage;
    private String business_name;

    public CartItemModel(String business_id, String product_id, String category_id, String subcategory_id, String product_name, String product_description, String product_unit, String product_sgst, String product_cgst, String product_image, String product_unit_name, String product_stock, String product_price, String order_qty, String offer_id, String offer_price, String offer_percentage, String business_name) {
        this.business_id = business_id;
        this.product_id = product_id;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_unit = product_unit;
        this.product_sgst = product_sgst;
        this.product_cgst = product_cgst;
        this.product_image = product_image;
        this.product_unit_name = product_unit_name;
        this.product_stock = product_stock;
        this.product_price = product_price;
        this.order_qty = order_qty;
        this.offer_id = offer_id;
        this.offer_price = offer_price;
        this.offer_percentage = offer_percentage;
        this.business_name = business_name;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public String getProduct_sgst() {
        return product_sgst;
    }

    public String getProduct_cgst() {
        return product_cgst;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_unit_name() {
        return product_unit_name;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public String getOffer_percentage() {
        return offer_percentage;
    }

    public String getBusiness_name() {
        return business_name;
    }
}
