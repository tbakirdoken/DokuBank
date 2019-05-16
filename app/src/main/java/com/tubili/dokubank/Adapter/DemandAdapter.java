package com.tubili.dokubank.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

import java.util.ArrayList;

public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.ViewHolder> {

    Context context;
    private ArrayList<Demand> demandModelArrayList;
    private OnItemListener mOnItemListener;

    public DemandAdapter(Context context, ArrayList<Demand> demandModelArrayList) {
        this.context = context;
        this.demandModelArrayList = demandModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demands,parent,false);
        return new ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.inbox.setImageResource(demandModelArrayList.get(position).getInbox());
//        holder.arrow.setImageResource(demandModelArrayList.get(position).getArrow());
//        holder.txttrades.setText(demandModelArrayList.get(position).getTxttrades());
//        holder.txthistory.setText(demandModelArrayList.get(position).getTxthistory());
    }

    @Override
    public int getItemCount() {
        return demandModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView inbox,arrow;
        TextView txttrades,txthistory;
        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);

            inbox=itemView.findViewById(R.id.inbox);
            arrow=itemView.findViewById(R.id.arrow);
            txttrades=itemView.findViewById(R.id.txttrades);
            txthistory=itemView.findViewById(R.id.txthistory);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}





/*package com.tubili.dokubank.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

import java.util.ArrayList;
import java.util.List;


public class DemandsAdapter extends ArrayAdapter<Demand> implements View.OnClickListener{

    private ArrayList<Demand> demands;
    Context mContext;

// View lookup cache
private static class ViewHolder {
    TextView txtName;
    TextView txtType;
    TextView txtVersion;
    ImageView info;
}

    public DemandsAdapter(ArrayList<Demand> demands, Context context) {
        super(context, R.layout.item_demands, demands);
        this.demands = demands;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Demand dataModel=(Demand)object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Demand dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getType());
        viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}*/


