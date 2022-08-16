package com.example.oase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListTaskAdapter extends ArrayAdapter<Task> {

    Context context;
    private ArrayList<Task> arrayList;
    public ListTaskAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.list_task, null);
        }

        if (arrayList.size()>0){
            Task t = arrayList.get(position);
            TextView matkulTxt = convertView.findViewById(R.id.txtMatkulTask);
            TextView typeTxt = convertView.findViewById(R.id.txtTypeTask);
            TextView deadlineTxt = convertView.findViewById(R.id.txtDeadlineTask);

            matkulTxt.setText(t.matkulTask);
            typeTxt.setText(t.typeTask);
            deadlineTxt.setText(t.deadlineTask);
        }

        return convertView;
    }
}
