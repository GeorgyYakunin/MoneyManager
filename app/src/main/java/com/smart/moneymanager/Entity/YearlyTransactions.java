package com.smart.moneymanager.Entity;

import com.smart.moneymanager.DataController.DateDataController;

import java.util.ArrayList;
import java.util.List;

public class YearlyTransactions {
    public String year;
    public Double incomeTotal;
    public Double expenseTotal;
    public List<MonthTransactions> monthlyExpenses = new ArrayList<>();

    public YearlyTransactions(List<MonthTransactions> monthlyExpenses) {
        this.year = new DateDataController().DateToYear(monthlyExpenses.get(0).date);
        SetTotal(monthlyExpenses);
        this.monthlyExpenses.addAll(monthlyExpenses);
    }
    private void SetTotal(List<MonthTransactions> monthlyExpenses){
        double incomeSum = 0;
        double expenseSum = 0;
        for (MonthTransactions monex: monthlyExpenses) {
            incomeSum += monex.incomeTotal;
            expenseSum += monex.expenseTotal;
        }
        this.incomeTotal = incomeSum;
        this.expenseTotal = expenseSum;
    }
}
