package com.itptit.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itptit.myapplication.EditItemActivity;
import com.itptit.myapplication.R;
import com.itptit.myapplication.data.DBHelper;
import com.itptit.myapplication.model.MyModel;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<MyModel> dataset;

    final Context mContext;

    final DBHelper dbHelper;

    public ListViewAdapter(List<MyModel> dataset, Context mContext, DBHelper dbHelper) {
        this.dataset = dataset;
        this.mContext = mContext;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView content;
        public Button buttonEdit;
        public Button buttonDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            content = itemView.findViewById(R.id.item_content);
            buttonEdit = itemView.findViewById(R.id.btn_edit);
            buttonDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyModel item = dataset.get(position);

        holder.name.setText(item.getJobName());
        holder.content.setText(item.getJobDetail());

        holder.buttonEdit.setOnClickListener(view -> {
            Intent myIntent = new Intent(mContext, EditItemActivity.class);
            myIntent.putExtra("item_data", item);
            mContext.startActivity(myIntent);
        });

        holder.buttonDelete.setOnClickListener(view -> {
            dbHelper.deleteData(item);
            dataset = dbHelper.getAllData();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
