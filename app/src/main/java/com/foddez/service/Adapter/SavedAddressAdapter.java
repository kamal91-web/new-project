package com.foddez.service.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foddez.service.EditSavedAddress;
import com.foddez.service.Model.SavedAddressModel;
import com.foddez.service.R;
import com.foddez.service.SavedAddress;

import java.util.List;

public class SavedAddressAdapter extends RecyclerView.Adapter<SavedAddressAdapter.SavedAddressViewHolder>{

    private Context mCtx;
    private List<SavedAddressModel> savedAddressModelList;

    public SavedAddressAdapter(Context mCtx, List<SavedAddressModel> savedAddressModelList) {
        this.mCtx = mCtx;
        this.savedAddressModelList = savedAddressModelList;
    }

    @NonNull
    @Override
    public SavedAddressAdapter.SavedAddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.saved_address_layout,viewGroup,false);
        return new SavedAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAddressAdapter.SavedAddressViewHolder savedAddressViewHolder, int i) {
        final SavedAddressModel savedAddressModel = savedAddressModelList.get(i);

        if(savedAddressModel.getAddress_type().equals("HOME")){
            savedAddressViewHolder.llLeft.setBackgroundColor(Color.parseColor("#0000EE"));
            savedAddressViewHolder.txtAddressType.setTextColor(Color.parseColor("#0000EE"));
            savedAddressViewHolder.imgAddressType.setBackgroundResource(R.drawable.home);
        }else if(savedAddressModel.getAddress_type().equals("OFFICE")){
            savedAddressViewHolder.llLeft.setBackgroundColor(Color.parseColor("#E9133C"));
            savedAddressViewHolder.txtAddressType.setTextColor(Color.parseColor("#E9133C"));
            savedAddressViewHolder.imgAddressType.setBackgroundResource(R.drawable.office);
        }else{
            savedAddressViewHolder.llLeft.setBackgroundColor(Color.parseColor("#11D4FD"));
            savedAddressViewHolder.txtAddressType.setTextColor(Color.parseColor("#11D4FD"));
            savedAddressViewHolder.imgAddressType.setBackgroundResource(R.drawable.other);
        }
        savedAddressViewHolder.txtAddressType.setText(savedAddressModel.getAddress_type());
        savedAddressViewHolder.txtFullAddress.setText(savedAddressModel.getHouse_number() + ", " + savedAddressModel.getArea() + ", " + savedAddressModel.getCity() + ", " + savedAddressModel.getState() + ", " + savedAddressModel.getCountry());
        savedAddressViewHolder.imgEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin = new Intent(mCtx, EditSavedAddress.class);
                gotologin.putExtra("address_id",savedAddressModel.getSaid());
                gotologin.putExtra("address_type",savedAddressModel.getAddress_type());
                gotologin.putExtra("house_number",savedAddressModel.getHouse_number());
                gotologin.putExtra("area",savedAddressModel.getArea());
                gotologin.putExtra("land_mark",savedAddressModel.getLand_mark());
                gotologin.putExtra("state",savedAddressModel.getState());
                gotologin.putExtra("city",savedAddressModel.getCity());
                gotologin.putExtra("country",savedAddressModel.getCountry());
                mCtx.startActivity(gotologin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedAddressModelList.size();
    }

    public class SavedAddressViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llLeft;
        ImageView imgAddressType, imgEditAddress;
        TextView txtAddressType, txtFullAddress;
        public SavedAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            llLeft=itemView.findViewById(R.id.ll_left_address);
            imgAddressType=itemView.findViewById(R.id.img_address_type);
            imgEditAddress=itemView.findViewById(R.id.img_edit_address);
            txtAddressType=itemView.findViewById(R.id.txt_address_type);
            txtFullAddress=itemView.findViewById(R.id.txt_full_address);
        }
    }
}
