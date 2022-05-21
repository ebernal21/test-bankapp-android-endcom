package com.example.testbankapperickbernal.Models;

public class BalanceModel
{
    private int id;
    private String account;
    private int generalBalance;
    private int revenue;
    private int expenses;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public int getGeneralBalance() { return generalBalance; }
    public void setGeneralBalance(int generalBalance) { this.generalBalance = generalBalance; }

    public int getRevenue() { return revenue; }
    public void setRevenue(int revenue) { this.revenue = revenue; }

    public int getExpenses() { return expenses; }
    public void setExpenses(int expenses) { this.expenses = expenses; }

    public BalanceModel() { }

    public BalanceModel(int id, String account, int generalBalance, int revenue, int expenses)
    {
        this.id = id;
        this.account = account;
        this.generalBalance = generalBalance;
        this.revenue = revenue;
        this.expenses = expenses;
    }
}
