package com.example.testbankapperickbernal.Models;

public class AddCardModel
{
    private String tarjeta;
    private String cuenta;
    private String issure;
    private String nombre;
    private String marca;
    private String estatus;
    private Double saldo;
    private String tipo;

    public String getTarjeta() { return tarjeta; }
    public void setTarjeta(String tarjeta) { this.tarjeta = tarjeta; }

    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getIssure() { return issure; }
    public void setIssure(String issure) { this.issure = issure; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public AddCardModel() {
    }

    public AddCardModel(String tarjeta, String cuenta, String issure, String nombre, String marca, String estatus, Double saldo, String tipo) {
        this.tarjeta = tarjeta;
        this.cuenta = cuenta;
        this.issure = issure;
        this.nombre = nombre;
        this.marca = marca;
        this.estatus = estatus;
        this.saldo = saldo;
        this.tipo = tipo;
    }
}
