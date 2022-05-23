package com.example.testbankapperickbernal.Models;

public class AccountModel
{
    private int id;
    private String cuenta;
    private String nombre;
    private String ultimasesion;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getNombre() {  return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUltimasesion() { return ultimasesion; }
    public void setUltimasesion(String ultimasesion) { this.ultimasesion = ultimasesion; }

    public AccountModel() {  }

    public AccountModel(int id, String cuenta, String nombre, String ultimasesion) {
        this.id = id;
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.ultimasesion = ultimasesion;
    }
}
