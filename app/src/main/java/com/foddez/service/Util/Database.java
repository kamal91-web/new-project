package com.foddez.service.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.DatabaseMetaData;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="mycaer";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="order_details";

    private static final String COLUMN_ID="id";
    private static final String COLUMN_BUSINESS_ID="business_id";
    private static final String COLUMN_PRODUCT_ID="product_id";
    private static final String COLUMN_CATEGORY_ID="category_id";
    private static final String COLUMN_SUBCATEGORY_ID="subcategory_id";
    private static final String COLUMN_PRODUCT_NAME="product_name";
    private static final String COLUMN_PRODUCT_DESC="product_description";
    private static final String COLUMN_PRODUCT_UNIT="product_unit";
    private static final String COLUMN_PRODUCT_SGST="product_sgst";
    private static final String COLUMN_PRODUCT_CGST="product_cgst";
    private static final String COLUMN_PRODUCT_IMAGE="product_image";
    private static final String COLUMN_PRODUCT_UNIT_NAME="product_unit_name";
    private static final String COLUMN_PRODUCT_STOCK="product_stock";
    private static final String COLUMN_PRODUCT_PRICE="product_price";
    private static final String COLUMN_ORDER_QTY="order_qty";
    private static final String COLUMN_OFFER_ID="offer_id";
    private static final String COLUMN_OFFER_PRICE="offer_price";
    private static final String COLUMN_OFFER_PERCENTAGE="offer_percentage";
    private static final String COLUMN_BUSINESS_NAME="business_name";
    public Database(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlcreate="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (\n" +
                " "+COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                " "+COLUMN_BUSINESS_ID+" TEXT,\n" +
                " "+COLUMN_PRODUCT_ID+" TEXT,\n" +
                " "+COLUMN_CATEGORY_ID+" TEXT,\n" +
                " "+COLUMN_SUBCATEGORY_ID+" TEXT,\n" +
                " "+COLUMN_PRODUCT_NAME+" TEXT,\n" +
                " "+COLUMN_PRODUCT_DESC+" TEXT,\n" +
                " "+COLUMN_PRODUCT_UNIT+" TEXT,\n" +
                " "+COLUMN_PRODUCT_SGST+" TEXT,\n" +
                " "+COLUMN_PRODUCT_CGST+" TEXT,\n" +
                " "+COLUMN_PRODUCT_IMAGE+" TEXT,\n" +
                " "+COLUMN_PRODUCT_UNIT_NAME+" TEXT,\n" +
                " "+COLUMN_PRODUCT_STOCK+" TEXT,\n" +
                " "+COLUMN_PRODUCT_PRICE+" TEXT,\n" +
                " "+COLUMN_ORDER_QTY+" TEXT,\n" +
                " "+COLUMN_OFFER_ID+" TEXT,\n" +
                " "+COLUMN_OFFER_PRICE+" TEXT,\n" +
                " "+COLUMN_OFFER_PERCENTAGE+" TEXT,\n" +
                " "+COLUMN_BUSINESS_NAME+" TEXT\n" +
                ");";
        db.execSQL(sqlcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Add Record in Cart Table
    public boolean addProductToCart(String businessId, String productId, String categoryId, String subcategoryId, String productName, String productDesc, String productUnit, String productSgst, String productCgst, String productImage, String productUnitName, String productStock, String productPrice, String orderQty, String offerId, String offerPrice, String offerPercentage, String businessName){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_BUSINESS_ID, businessId);
        cv.put(COLUMN_PRODUCT_ID, productId);
        cv.put(COLUMN_CATEGORY_ID, categoryId);
        cv.put(COLUMN_SUBCATEGORY_ID, subcategoryId);
        cv.put(COLUMN_PRODUCT_NAME, productName);
        cv.put(COLUMN_PRODUCT_DESC, productDesc);
        cv.put(COLUMN_PRODUCT_UNIT, productUnit);
        cv.put(COLUMN_PRODUCT_SGST, productSgst);
        cv.put(COLUMN_PRODUCT_CGST, productCgst);
        cv.put(COLUMN_PRODUCT_IMAGE, productImage);
        cv.put(COLUMN_PRODUCT_UNIT_NAME, productUnitName);
        cv.put(COLUMN_PRODUCT_STOCK, productStock);
        cv.put(COLUMN_PRODUCT_PRICE, productPrice);
        cv.put(COLUMN_ORDER_QTY, orderQty);
        cv.put(COLUMN_OFFER_ID, offerId);
        cv.put(COLUMN_OFFER_PRICE, offerPrice);
        cv.put(COLUMN_OFFER_PERCENTAGE, offerPercentage);
        cv.put(COLUMN_BUSINESS_NAME, businessName);
        return db.insert(TABLE_NAME,null,cv) != -1;
    }
    // Select All record from Cart table
    public Cursor getAllCartProduct(){
        SQLiteDatabase db=getReadableDatabase();

        return db.rawQuery("Select * from "+TABLE_NAME,null);
    }

    // Select Particular product in Cart table
    public Cursor getThisCartProduct(String productId){
        SQLiteDatabase db=getReadableDatabase();
        //ContentValues cv=new ContentValues();
        //cv.put(COLUMN_PRODUCT_ID, productId);
        return db.rawQuery("Select * from "+TABLE_NAME+" Where "+COLUMN_PRODUCT_ID+" =?", new String[]{productId});
    }

    // Update Product in the Cart
    public boolean updateProduct(String productId, String productQty){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ORDER_QTY, productQty);
        return db.update(TABLE_NAME,cv,COLUMN_PRODUCT_ID+"=?",new String[]{productId})>0;
    }
    public boolean deleteProduct(String productId){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,COLUMN_PRODUCT_ID+"=?",new String[]{productId})>0;
    }
    public boolean deleteAllProduct(){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,null,null)>0;
    }
    public int cartProductCount(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME,null);
        int count=cursor.getCount();
        cursor.close();
        return count;
    }
    public int checkBusinessItem(String businessId){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME+" Where "+COLUMN_BUSINESS_ID+" =? GROUP BY "+COLUMN_BUSINESS_ID,new String[]{businessId});
        int count=cursor.getCount();
        cursor.close();
        return count;
    }

    public Cursor getBusinessName(){
        SQLiteDatabase db=getReadableDatabase();
        //ContentValues cv=new ContentValues();
        //cv.put(COLUMN_PRODUCT_ID, productId);
        return db.rawQuery("Select * from "+TABLE_NAME+" GROUP BY "+COLUMN_BUSINESS_ID, null);
    }

    public int cartProductQty(){
        int totalPQty=0;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("Select SUM("+COLUMN_ORDER_QTY+") as totalaty from "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            totalPQty=cursor.getInt(cursor.getColumnIndex("totalaty"));
        }else{
            totalPQty=0;
        }
        cursor.close();
        return totalPQty;
    }
    public float cartProductTotalAmt(){
        float totalCartAmt= (float) 0.0;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            do {
                float totalPQty=Integer.parseInt(cursor.getString(14)) * Float.parseFloat(cursor.getString(13));
                totalCartAmt +=totalPQty;
                //Log.d("PRICEINFO_GETSTR", " Processing Row " + cursor.getString(14));
            }while (cursor.moveToNext());
        }else{
            totalCartAmt= (float) 0.0;
        }
        cursor.close();
        return totalCartAmt;
    }
}
