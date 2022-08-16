package com.example.oase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListDashboardAdapter extends RecyclerView.Adapter<ListDashboardAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Dashboard> dashboards;

    public ListDashboardAdapter(Context ctx, List<Dashboard> dashboards){
        this.inflater = LayoutInflater.from(ctx);
        this.dashboards = dashboards;
    }

    @NonNull
    @Override
    public ListDashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDashboardAdapter.ViewHolder holder, int position) {
        holder.matkulDashboard.setText(dashboards.get(position).getMatkul());
    }

    @Override
    public int getItemCount() {
        return dashboards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matkulDashboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matkulDashboard = itemView.findViewById(R.id.txtMatkulDasboard);
        }
    }
}
