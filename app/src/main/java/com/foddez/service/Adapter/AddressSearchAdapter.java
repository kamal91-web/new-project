package com.foddez.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.foddez.service.Model.AddressSearchModel;
import com.foddez.service.R;
import com.foddez.service.SearchAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressSearchAdapter extends RecyclerView.Adapter<AddressSearchAdapter.ViewHolder>  implements Filterable {

    private Context mCtx;
    private List<AddressSearchModel> mAddressList;
    private OnItemClickListener sListener;
    int previousPosition=0;

    /*public void setOnItemClickListener(SearchAddress searchAddress) {
        this.sListener=searchAddress;
    }*/

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public AddressSearchAdapter(Context mCtx, List<AddressSearchModel> mAddressList) {
        this.mCtx = mCtx;
        this.mAddressList = mAddressList;
    }

    @NonNull
    @Override
    public AddressSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_address_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressSearchAdapter.ViewHolder viewHolder, int i) {
        AddressSearchModel addressSearchModel=mAddressList.get(i);

        viewHolder.txtCityName.setText(addressSearchModel.getCity_name()+", "+addressSearchModel.getState_name()+", "+addressSearchModel.getCountry_name());
    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString=constraint.toString();
                if(charString.isEmpty()){

                }else{
                    ArrayList<AddressSearchModel> filteredList=new ArrayList<>();
                    for(AddressSearchModel addressSearchModel:mAddressList){
                        if(charString.toLowerCase().contains(charString)){
                            filteredList.add(addressSearchModel);
                        }
                    }
                    mAddressList=filteredList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mAddressList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mAddressList=(ArrayList<AddressSearchModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCityName=itemView.findViewById(R.id.txt_city_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(sListener!=null){
                    int position=getAdapterPosition();
                    //Snackbar.make(v,"Click on "+position, Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    if(position!=RecyclerView.NO_POSITION){
                        sListener.onItemClick(position);
                    }
                }
                }
            });
        }
    }
}
