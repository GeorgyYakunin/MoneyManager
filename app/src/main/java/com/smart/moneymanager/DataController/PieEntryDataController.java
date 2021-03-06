package com.smart.moneymanager.DataController;

import android.content.Context;

import com.github.mikephil.charting.data.PieEntry;
import com.smart.moneymanager.Activity.ActivityStart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import RoomDb.Transaction;

public class PieEntryDataController {
    private  double Total=0;
    public ArrayList<PieEntry> GetList(Context context, Date date){
         ArrayList<PieEntry> pieEntries = new ArrayList<>();
         List<Transaction> transactions = ActivityStart.getDBInstance(context).mmDao().GetTransactionsOfaDate(date);
         ActivityStart.destroyDBInstance();
        for (Transaction transaction : transactions) {
            if(!transaction.getIsIncome()){
                Total += transaction.getAmount();
                pieEntries.add(new PieEntry(((int) transaction.getAmount()), transaction.getName()));
            }

        }

//         if(pieEntries.size()==0){
//             pieEntries.add(new PieEntry(1 , "No entry"));
//         }
         return pieEntries;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getTotal() {
        return Total;
    }
}

