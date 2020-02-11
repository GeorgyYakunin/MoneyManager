package com.smart.moneymanager.ListAdaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smart.moneymanager.Activity.ActivityAddTrans;
import com.smart.moneymanager.R;

import java.util.List;

import RoomDb.Transaction;

public class DayTransactionsRecycleViewAdaptor extends RecyclerView.Adapter<DayTransactionsRecycleViewAdaptor.ViewHolder> {
    private List<Transaction> transactions;
    private Context context;
    public DayTransactionsRecycleViewAdaptor(List<Transaction> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_daily_transactions_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(transactions.get(position).getCategory().getName());
        if(transactions.get(position).getName().equals("") || transactions.get(position).getName().equals(null)){
            holder.tvName.setText(transactions.get(position).getCategory().getName());
        }else {
            holder.tvName.setText(transactions.get(position).getName());
        }
        holder.tvValue.setText(String.valueOf(String.format("%.2f", transactions.get(position).getAmount())));
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, ActivityAddTrans.class);
                intent.putExtra("transactionId", transactions.get(position).getId());
                context.startActivity(intent);
                return false;
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityAddTrans.class);
                intent.putExtra("transactionId", transactions.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvCategoryName;
        public final TextView tvName;
        public final TextView tvValue;
        public final FloatingActionButton btnEdit;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCategoryName = (TextView) view.findViewById(R.id.tvCategoyName);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvValue = (TextView) view.findViewById(R.id.tvValue);
            btnEdit = (FloatingActionButton) view.findViewById(R.id.btnEdit);

        }

    }
}
