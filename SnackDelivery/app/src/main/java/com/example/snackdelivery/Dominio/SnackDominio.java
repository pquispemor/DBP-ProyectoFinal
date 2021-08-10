package com.example.snackdelivery.Dominio;

import java.io.Serializable;

public class SnackDominio implements Serializable {
    private String titulo;
    private String foto;
    private String descripcion;
    private Double tarifa;
    private int numeroTarjeta;

    public SnackDominio(String titulo, String foto, String descripcion, Double tarifa) {
        this.titulo = titulo;
        this.foto = foto;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
    }

    public SnackDominio(String titulo, String foto, String descripcion, Double tarifa, int numeroTarjeta) {
        this.titulo = titulo;
        this.foto = foto;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}
