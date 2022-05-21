package com.example.testbankapperickbernal.Models;

public class CardsModel
{
    private int id;
    private String cardNumber;
    private String name;
    private int balance;
    private String estado;
    private String tipo;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;  }

    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public CardsModel() {  }

    public CardsModel(int id, String cardNumber, String name, int balance, String estado, String tipo)
    {
        this.id = id;
        this.cardNumber = cardNumber;
        this.name = name;
        this.balance = balance;
        this.estado = estado;
        this.tipo = tipo;
    }
}
