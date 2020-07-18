package com.foddez.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.foddez.service.ConfirmOrder;
import com.foddez.service.Model.SavedAddressModel;
import com.foddez.service.R;

import java.util.List;

public class SavedAddressRadioAdapter extends RecyclerView.Adapter<SavedAddressRadioAdapter.SavedAddressViewHolder> {
    private Context mCtx;
    private List<SavedAddressModel> savedAddressModelList;
    private int lastSelectedPosition = -1;
    public SavedAddressRadioAdapter(Context mCtx, List<SavedAddressModel> savedAddressModelList) {
        this.mCtx = mCtx;
        this.savedAddressModelList = savedAddressModelList;
    }
    @NonNull
    @Override
    public SavedAddressRadioAdapter.SavedAddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.layout_radiobutton_address_list,viewGroup,false);
        return new SavedAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SavedAddressRadioAdapter.SavedAddressViewHolder savedAddressViewHolder, int i) {
        final SavedAddressModel savedAddressModel = savedAddressModelList.get(i);
        savedAddressViewHolder.txtAddressType.setText(savedAddressModel.getAddress_type());
        savedAddressViewHolder.txtAddress.setText(savedAddressModel.getHouse_number() + ", " + savedAddressModel.getArea() + ", " + savedAddressModel.getCity() + ", " + savedAddressModel.getState() + ", " + savedAddressModel.getCountry());
        final String said=savedAddressModel.getSaid();
        final String address_type=savedAddressModel.getAddress_type();
        final String full_address=savedAddressModel.getHouse_number() + ", " + savedAddressModel.getArea() + ", " + savedAddressModel.getCity() + ", " + savedAddressModel.getState() + ", " + savedAddressModel.getCountry();
        savedAddressViewHolder.rbtnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition=savedAddressViewHolder.getAdapterPosition();
                notifyDataSetChanged();
                if(mCtx instanceof ConfirmOrder){
                    ((ConfirmOrder)mCtx).addressSelected(said,address_type,full_address);
                }
            }
        });
        savedAddressViewHolder.rbtnAddress.setChecked(lastSelectedPosition==i);
    }

    @Override
    public int getItemCount() {
        return savedAddressModelList.size();
    }

    public class SavedAddressViewHolder extends RecyclerView.ViewHolder {
        TextView txtAddressType, txtAddress;
        RadioButton rbtnAddress;
        public SavedAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddressType=itemView.findViewById(R.id.txt_address_type);
            txtAddress=itemView.findViewById(R.id.txt_saved_address);
            rbtnAddress=itemView.findViewById(R.id.rbtn_saved_address);
            /*rbtnAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition=getAdapterPosition();
                    notifyDataSetChanged();

                }
            });*/
        }
    }
}
