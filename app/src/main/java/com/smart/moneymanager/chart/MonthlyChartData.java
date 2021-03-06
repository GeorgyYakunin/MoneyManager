package com.smart.moneymanager.chart;

import android.content.Context;

import com.smart.moneymanager.Activity.ActivityStart;
import com.smart.moneymanager.DataController.DateDataController;
import com.smart.moneymanager.DataController.ExpenseDataController;
import com.smart.moneymanager.Entity.MonthTransactions;

import java.util.Date;
import java.util.List;

import RoomDb.Transaction;

public class MonthlyChartData {
    public List<MonthTransactions> GetMonthlyExpenseList(Context context){
        List<Transaction> transactions = ActivityStart.getDBInstance(context).mmDao().GetTransaction();
        ActivityStart.destroyDBInstance();
        ExpenseDataController expenseDataController = new ExpenseDataController(transactions);
        return expenseDataController.GetMonthlyExpenses(expenseDataController.getDailyExpenses());
    }

    public Double GetMaximumMonthlyIncome(List<MonthTransactions> monthTransactions){
        Double maximum = 0.0;
        for(MonthTransactions monthTransactions1 : monthTransactions){
            if(monthTransactions1.incomeTotal>maximum){
                maximum = monthTransactions1.incomeTotal;
            }
        }
        return  maximum;
    }

    public Double GetMaximumMonthlyExpense(List<MonthTransactions> monthTransactions){
        Double maximum = 0.0;
        for(MonthTransactions monthTransactions1 : monthTransactions){
            if(monthTransactions1.expenseTotal>maximum){
                maximum = monthTransactions1.expenseTotal;
            }
        }
        return  maximum;
    }
    public MonthTransactions GetThisMonthTransactions(List<MonthTransactions> monthTransactionsList){
        Date date = new Date();
        String thisMonth = new DateDataController().DateToMonthYear(date);
        for(MonthTransactions monthTransactions : monthTransactionsList){
            if(monthTransactions.month.equals(thisMonth)){
                return  monthTransactions;
            }
        }
        return null;
    }
}
