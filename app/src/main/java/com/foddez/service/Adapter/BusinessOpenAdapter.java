package com.foddez.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foddez.service.Model.BusinessModel;
import com.foddez.service.R;

import java.util.ArrayList;
import java.util.List;

public class BusinessOpenAdapter extends RecyclerView.Adapter<BusinessOpenAdapter.BusinessViewHolder> {

    private Context mCtx;
    private ArrayList<BusinessModel> businessModelList;
    private OnItemClickListener cListner;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        cListner=onItemClickListener;
    }

    public BusinessOpenAdapter(Context mCtx, ArrayList<BusinessModel> businessModelList) {
        this.mCtx = mCtx;
        this.businessModelList = businessModelList;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.business_list_layout,viewGroup,false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder viewHolder, int i) {
        final BusinessModel businessModel=businessModelList.get(i);
        viewHolder.businessName.setText(businessModel.getBusiness_name());
        Glide.with(mCtx).load(businessModel.getBusiness_image()).into(viewHolder.businessLogo);
    }

    @Override
    public int getItemCount() {
        return businessModelList.size();
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder {
        ImageView businessLogo;
        TextView businessName, businessDesc, businessDist, businessDeliveryTime;
        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);
            businessLogo=itemView.findViewById(R.id.business_icon);
            businessName=itemView.findViewById(R.id.txt_business_name);
            businessDesc=itemView.findViewById(R.id.txt_business_desc);
            businessDist=itemView.findViewById(R.id.txt_business_distance);
            businessDeliveryTime=itemView.findViewById(R.id.txt_business_delivery_time);
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
