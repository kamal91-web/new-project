package com.foddez.service.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foddez.service.R;
import com.foddez.service.Model.CategoryModel;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>  {

    private Context mCtx;
    private List<CategoryModel> categoriesList;
    private OnItemClickListener cListner;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        cListner=onItemClickListener;
    }

    public HomeCategoryAdapter(Context mCtx, List<CategoryModel> categoriesList) {
        this.mCtx = mCtx;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public HomeCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.home_category_layout,viewGroup,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.CategoryViewHolder categoryViewHolder, int i) {
        final CategoryModel categorie=categoriesList.get(i);
        categoryViewHolder.catName.setText(categorie.getCategory_name());
        Glide.with(mCtx).load(categorie.getImage()).into(categoryViewHolder.catImage);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView catImage;
        TextView catName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            catName=itemView.findViewById(R.id.cat_nm_top);
            catImage=itemView.findViewById(R.id.cat_img_top);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cListner!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            cListner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
