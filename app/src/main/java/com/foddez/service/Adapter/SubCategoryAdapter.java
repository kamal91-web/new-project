package com.foddez.service.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foddez.service.Model.SubCategoryModel;
import com.foddez.service.ProductList;
import com.foddez.service.R;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<SubCategoryModel> mySubCategoryList;
    private callback listener;
    private View selectView;
    private int selectedIndex=-1;
    private int firstIndex=1;

    public SubCategoryAdapter(Context mCtx, ArrayList<SubCategoryModel> mySubCategoryList) {
        this.mCtx = mCtx;
        this.mySubCategoryList = mySubCategoryList;
    }
    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.layout_sub_category,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder viewHolder, final int i) {
        final SubCategoryModel subCategoryModel=mySubCategoryList.get(i);
        viewHolder.subcatName.setText(subCategoryModel.getSubCategoryName());
        viewHolder.clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIndex = i;
                if (mCtx instanceof ProductList) {
                    ((ProductList)mCtx).getProducts(subCategoryModel.getBusinessId(),subCategoryModel.getCategoryId(),subCategoryModel.getScid());
                    /*Log.d("BUSINESS==",subCategoryModel.getBusinessId());
                    Log.d("BUSINESS==",subCategoryModel.getCategoryId());
                    Log.d("BUSINESS==",subCategoryModel.getScid());*/
                    notifyDataSetChanged();
                }
            }
        });
        if(selectedIndex==i){
            viewHolder.bottomIndicator.setBackgroundColor(mCtx.getResources().getColor(R.color.color_lightGreen));
            viewHolder.bottomIndicator.setVisibility(View.VISIBLE);
            viewHolder.subcatName.setTextColor(Color.parseColor("#5AD237"));

            if (mCtx instanceof ProductList) {
                ((ProductList)mCtx).getProducts(subCategoryModel.getBusinessId(),subCategoryModel.getCategoryId(),subCategoryModel.getScid());

                //notifyDataSetChanged();
            }
        }else{
            if(firstIndex==1 && i==0){
                viewHolder.bottomIndicator.setBackgroundColor(mCtx.getResources().getColor(R.color.color_lightGreen));
                viewHolder.bottomIndicator.setVisibility(View.VISIBLE);
                viewHolder.subcatName.setTextColor(Color.parseColor("#5AD237"));
                if (mCtx instanceof ProductList) {
                    ((ProductList)mCtx).getProducts(subCategoryModel.getBusinessId(),subCategoryModel.getCategoryId(),subCategoryModel.getScid());
                    //notifyDataSetChanged();
                }
                firstIndex=2;
            }else{
                viewHolder.bottomIndicator.setBackgroundColor(Color.TRANSPARENT);
                viewHolder.bottomIndicator.setVisibility(View.GONE);
                viewHolder.subcatName.setTextColor(Color.parseColor("#7f333639"));
            }
        }
    }
    public interface callback{
        void onTitleClicked(int position);
    }
    @Override
    public int getItemCount() {
        return mySubCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subcatName;
        CardView clicker;
        View bottomIndicator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subcatName=itemView.findViewById(R.id.txt_sub_cat_name);
            clicker = itemView.findViewById(R.id.clickr);
            bottomIndicator = itemView.findViewById(R.id.bottom_indicator);
        }
    }
}
