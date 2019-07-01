package com.tubili.dokubank.Adapter;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tubili.dokubank.Common;
import com.tubili.dokubank.DemandDetailActivity;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

import java.util.ArrayList;

public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.ViewHolder> {

    Context context;
    private ArrayList<Demand> demandModelArrayList;

    public DemandAdapter(Context context, ArrayList<Demand> demandModelArrayList) {
        this.context = context;
        this.demandModelArrayList = demandModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demands,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Demand selectedProduct = demandModelArrayList.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return demandModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName;
        ImageView arrow,imagePerson;
        TextView txtType;
        Demand _demand;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtname);
            arrow=itemView.findViewById(R.id.imageArrow);
            txtType=itemView.findViewById(R.id.txttype);
            imagePerson=itemView.findViewById(R.id.imagePerson);
            itemView.setOnClickListener(this);
        }

        public void setData(Demand selectedDemand, int position) {

            this.txtName.setText(selectedDemand.getName());
            this.txtType.setText(selectedDemand.getTissueType());
            //this.productImage.setImageResource(selectedDemand.getImageID());
            _demand = selectedDemand;
        }

        @Override
        public void onClick(View view) {

            Common.selectedDemand = _demand;

            Intent intent = new Intent(view.getContext(),DemandDetailActivity.class);
            view.getContext().startActivity(intent);

        }
    }
}