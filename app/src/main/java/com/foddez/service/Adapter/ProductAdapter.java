package com.foddez.service.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.foddez.service.Model.ProductModel;
import com.foddez.service.ProductList;
import com.foddez.service.R;
import com.foddez.service.Util.Database;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private ArrayList<ProductModel> productList;
    private OnItemClickListener pListener;
    int previousPosition=0;
    Database mDataBase;
    private String businessName;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        pListener=listener;
    }

    public ProductAdapter(Context mCtx, ArrayList<ProductModel> productList, Database mDataBase, String businessName) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.mDataBase=mDataBase;
        this.businessName=businessName;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.product_layout,viewGroup,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ProductViewHolder productViewHolder, int i) {
        final ProductModel products =productList.get(i);
        if(productViewHolder.productImage.equals("")){
            Glide.with(mCtx).load(products.getProduct_image()).into(productViewHolder.productImage);
        }else {
            Glide.with(mCtx).load(products.getProduct_image()).into(productViewHolder.productImage);
        }
        productViewHolder.txtProductName.setText(products.getProduct_name());
        productViewHolder.txtProductDesc.setText(products.getProduct_desc());
        productViewHolder.txtProductPrice.setText("\u20B9"+String.valueOf(products.getProduct_price())+"/-");
        productViewHolder.txtProductUnit.setText(products.getProduct_unit_name());

        Cursor curchk = mDataBase.getThisCartProduct(products.getPid());
        if (curchk.moveToFirst()) {
            productViewHolder.addToCartButton.setVisibility(View.GONE);
            productViewHolder.llElegantNumber.setVisibility(View.VISIBLE);
            productViewHolder.numberButton.setNumber(curchk.getString(14));
        }else{
            productViewHolder.addToCartButton.setVisibility(View.VISIBLE);
            productViewHolder.llElegantNumber.setVisibility(View.GONE);
            productViewHolder.numberButton.setNumber("0");
        }
        curchk.close();
        productViewHolder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataBase.cartProductCount()>0) {
                    if (mDataBase.checkBusinessItem(products.getBusiness_id()) == 1) {
                        productViewHolder.addToCartButton.setVisibility(View.GONE);
                        productViewHolder.llElegantNumber.setVisibility(View.VISIBLE);
                        //productViewHolder.numberButton.setRange(1,Integer.valueOf(products.getItem_stock()));
                        productViewHolder.numberButton.setNumber("1");
                        String pid=products.getPid();
                        String cat_id=products.getCat_id();
                        String sub_cat_id=products.getSub_cat_id();
                        String business_id=products.getBusiness_id();
                        String product_name=products.getProduct_name();
                        String product_desc=products.getProduct_desc();
                        String product_unit=products.getProduct_unit();
                        String item_sgst=products.getItem_sgst();
                        String item_cgst=products.getItem_cgst();
                        String product_image=products.getProduct_image();
                        String product_unit_name=products.getProduct_unit_name();
                        String item_stock=products.getItem_stock();
                        String item_price=String.valueOf(products.getProduct_price());

                        Cursor curchk = mDataBase.getThisCartProduct(pid);
                        mDataBase.addProductToCart(business_id,pid, cat_id, sub_cat_id, product_name, product_desc, product_unit, item_sgst,item_cgst,product_image,product_unit_name,item_stock, item_price,"1","","","",businessName);
                        curchk.close();
                        if (mCtx instanceof ProductList) {
                            ((ProductList)mCtx).displayBottomCart();
                        }
                    }else{
                        Cursor cursor = mDataBase.getBusinessName();
                        String prevBusinessName="";
                        if (cursor.moveToFirst()) {
                            prevBusinessName = cursor.getString(18);
                        }
                        if (mCtx instanceof ProductList) {
                            ((ProductList)mCtx). emptyCartPopup(prevBusinessName);
                        }
                    }
                }else{
                    productViewHolder.addToCartButton.setVisibility(View.GONE);
                    productViewHolder.llElegantNumber.setVisibility(View.VISIBLE);
                    //productViewHolder.numberButton.setRange(1,Integer.valueOf(products.getItem_stock()));
                    productViewHolder.numberButton.setNumber("1");
                    String pid=products.getPid();
                    String cat_id=products.getCat_id();
                    String sub_cat_id=products.getSub_cat_id();
                    String business_id=products.getBusiness_id();
                    String product_name=products.getProduct_name();
                    String product_desc=products.getProduct_desc();
                    String product_unit=products.getProduct_unit();
                    String item_sgst=products.getItem_sgst();
                    String item_cgst=products.getItem_cgst();
                    String product_image=products.getProduct_image();
                    String product_unit_name=products.getProduct_unit_name();
                    String item_stock=products.getItem_stock();
                    String item_price=String.valueOf(products.getProduct_price());

                    Cursor curchk = mDataBase.getThisCartProduct(pid);
                    mDataBase.addProductToCart(business_id,pid, cat_id, sub_cat_id, product_name, product_desc, product_unit, item_sgst,item_cgst,product_image,product_unit_name,item_stock, item_price,"1","","","",businessName);
                    curchk.close();
                    if (mCtx instanceof ProductList) {
                        ((ProductList)mCtx).displayBottomCart();
                    }
                }

            }
        });
        productViewHolder.numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String pid=products.getPid();
                String cat_id=products.getCat_id();
                String sub_cat_id=products.getSub_cat_id();
                String business_id=products.getBusiness_id();
                String product_name=products.getProduct_name();
                String product_desc=products.getProduct_desc();
                String product_unit=products.getProduct_unit();
                String item_sgst=products.getItem_sgst();
                String item_cgst=products.getItem_cgst();
                String product_image=products.getProduct_image();
                String product_unit_name=products.getProduct_unit_name();
                String item_stock=products.getItem_stock();
                String item_price=String.valueOf(products.getProduct_price());

                String productAddQty = productViewHolder.numberButton.getNumber();
                Cursor curchk = mDataBase.getThisCartProduct(pid);
                if(newValue==0){
                    productViewHolder.addToCartButton.setVisibility(View.VISIBLE);
                    productViewHolder.llElegantNumber.setVisibility(View.GONE);
                    mDataBase.deleteProduct(pid);
                    //mDataBase.deleteAllProduct();
                    curchk.close();
                    if (mCtx instanceof ProductList) {
                        ((ProductList)mCtx).displayBottomCart();
                    }
                }else{
                    productViewHolder.addToCartButton.setVisibility(View.GONE);
                    productViewHolder.llElegantNumber.setVisibility(View.VISIBLE);
                    if (curchk.moveToFirst()) {
                        if (mDataBase.updateProduct(pid, String.valueOf(newValue))) {
                            //Log.d("PRICEINFO_GETSTR", " Processing Row " + String.valueOf(newValue));
                            curchk.close();
                        } else {
                            curchk.close();
                        }
                    }else{
                        if (mDataBase.addProductToCart(business_id,pid, cat_id, sub_cat_id, product_name, product_desc, product_unit, item_sgst,item_cgst,product_image,product_unit_name,item_stock,item_price,String.valueOf(newValue),"","","",businessName)) {

                            curchk.close();
                        } else {
                            curchk.close();
                        }
                    }
                }
                if (mCtx instanceof ProductList) {
                    ((ProductList)mCtx).displayBottomCart();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView txtProductName, txtProductDesc, txtProductPrice, txtProductUnit;
        Button addToCartButton;
        LinearLayout llElegantNumber;
        ElegantNumberButton numberButton;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            txtProductName=itemView.findViewById(R.id.txt_product_name);
            txtProductDesc=itemView.findViewById(R.id.txt_product_desc);
            txtProductPrice=itemView.findViewById(R.id.txt_product_price);
            txtProductUnit=itemView.findViewById(R.id.txt_product_unit);
            addToCartButton=itemView.findViewById(R.id.btn_add_to_cart);
            llElegantNumber=itemView.findViewById(R.id.ll_elegant_number_button);
            numberButton=itemView.findViewById(R.id.elegant_number_button);
        }
    }
}
