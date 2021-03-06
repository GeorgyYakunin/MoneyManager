package com.smart.moneymanager.ListAdaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.moneymanager.Dialog.SelectCategoryListDialog;
import com.smart.moneymanager.R;

import java.util.List;

import RoomDb.Category;

public class SelectCategoryListAdaptor extends RecyclerView.Adapter<SelectCategoryListAdaptor.ViewHolder> {

    private Context context;
    private SelectCategoryListDialog selectCategoryListDialog;
    private final List<Category> allCategories;
    private final List<Category> selectedCategories;

    public SelectCategoryListAdaptor(Context context, SelectCategoryListDialog selectCategoryListDialog, List<Category> allCategories, List<Category> selectedCategories) {
        this.context = context;
        this.selectCategoryListDialog = selectCategoryListDialog;
        this.allCategories = allCategories;
        this.selectedCategories = selectedCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_category_list_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(allCategories.get(position).getName());
        if (allCategories.get(position).getIsIncome()) {
            holder.tvCategoryType.setText(context.getResources().getString(R.string.income));
        } else {
            holder.tvCategoryType.setText(context.getResources().getString(R.string.expense));
        }
        if(selectedCategories.contains(allCategories.get(position))){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.medium_gray));
            //holder.tvCategoryName.setTextColor(Color.YELLOW);
        }else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCategoryListDialog.ReloadList(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView tvCategoryName;
        public TextView tvCategoryType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryType = itemView.findViewById(R.id.tvCategoryType);
        }
    }
}
