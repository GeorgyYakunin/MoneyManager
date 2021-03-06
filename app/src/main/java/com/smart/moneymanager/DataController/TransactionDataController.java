package com.smart.moneymanager.DataController;

import android.content.Context;

import com.smart.moneymanager.Activity.ActivityStart;
import com.smart.moneymanager.Entity.DayTransactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import RoomDb.Category;
import RoomDb.Transaction;

public class TransactionDataController {
    public static List<Transaction> FilterTransactionsByCatgory(List<Transaction> transactions, List<Category> categories){
        if(categories.size()==0){
            return transactions;
        }
        List<Transaction> filteredTransactions = new ArrayList<>();
        for(Transaction transaction:transactions){
            if(DoesCategoryExists(transaction.getCategoryId(), categories)){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
    public static Boolean DoesCategoryExists(Long categoryid, List<Category> categories){
        for(Category category:categories){
            if(category.getId() == categoryid){
                return true;
            }
        }
        return false;
    }
    public static DayTransactions GetTodaysTransactions(Context context){
        Date date = new DateDataController().CropTimeFromDate(Calendar.getInstance());
        List<Transaction>  todayTransactions = ActivityStart.getDBInstance(context).mmDao().GetTransactionsOfaDate(date);
        List<DayTransactions> dayTransactions = new ExpenseDataController(todayTransactions).getDailyExpenses();
        if(dayTransactions.size()>0)
            return dayTransactions.get(0);
        return null;
    }
}
