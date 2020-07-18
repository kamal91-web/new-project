package com.foddez.service.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.foddez.service.ConfirmOrder;
import com.foddez.service.Model.CartItemModel;
import com.foddez.service.R;
import com.foddez.service.Util.Database;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    private Context mCtx;
    private ArrayList<CartItemModel> cartItemList;
    private OnItemClickListener pListener;
    int previousPosition=0;
    Database mDataBase;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        pListener=listener;
    }

    public CartItemAdapter(Context mCtx, ArrayList<CartItemModel> cartItemList, Database mDataBase) {
        this.mCtx = mCtx;
        this.cartItemList = cartItemList;
        this.mDataBase = mDataBase;
    }

    @NonNull
    @Override
    public CartItemAdapter.CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_card_item,viewGroup,false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.CartItemViewHolder cartItemViewHolder, int i) {
        final CartItemModel cartItemModel=cartItemList.get(i);
        cartItemViewHolder.txtProductName.setText(cartItemModel.getProduct_name());
        cartItemViewHolder.txtUnitName.setText(cartItemModel.getProduct_unit_name());
        float totalPPrice=Integer.valueOf(cartItemModel.getOrder_qty())*Float.valueOf(cartItemModel.getProduct_price());
        cartItemViewHolder.txtProductTotalPrice.setText("\u20B9 "+totalPPrice);
        cartItemViewHolder.numberButton.setNumber(cartItemModel.getOrder_qty());

        cartItemViewHolder.numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String pid=cartItemModel.getProduct_id();
                String business_id=cartItemModel.getBusiness_id();
                Cursor curchk = mDataBase.getThisCartProduct(pid);
                if(newValue==0){
                    mDataBase.deleteProduct(pid);
                    curchk.close();
                    if (mCtx instanceof ConfirmOrder) {
                        ((ConfirmOrder)mCtx).loadCartItems(business_id);
                    }
                }else{
                    if (curchk.moveToFirst()) {
                        if (mDataBase.updateProduct(pid, String.valueOf(newValue))) {
                            //Log.d("PRICEINFO_GETSTR", " Processing Row " + String.valueOf(newValue));
                            curchk.close();
                        } else {
                            curchk.close();
                        }
                        if (mCtx instanceof ConfirmOrder) {
                            ((ConfirmOrder)mCtx).loadCartItems(business_id);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtUnitName, txtProductTotalPrice;
        ElegantNumberButton numberButton;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName=itemView.findViewById(R.id.txt_product_name);
            txtUnitName=itemView.findViewById(R.id.txt_unit_name);
            txtProductTotalPrice=itemView.findViewById(R.id.txt_item_total_price);
            numberButton=itemView.findViewById(R.id.elegant_number_button);
        }
    }
}
