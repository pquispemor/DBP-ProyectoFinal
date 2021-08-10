package com.example.snackdelivery.Ayudante;

import android.content.Context;
import android.widget.Toast;

import com.example.snackdelivery.Dominio.SnackDominio;
import com.example.snackdelivery.Interfaz.ChangeNumberItemListener;

import java.util.ArrayList;

public class ManagementCard {  //Tarjeta de Gestion
    private Context context;
    private TinyDB tinyDB;

    public ManagementCard(Context context){
        this.context = context;
        this.tinyDB=new TinyDB(context);

    }
    public void insertarSnack(SnackDominio item){
        ArrayList<SnackDominio> listSnack=getListCard();
        boolean existAlready=false;
        int n=0;
        for (int i=0; i < listSnack.size(); i++){
            if (listSnack.get(i).getTitulo().equals(item.getTitulo())){
                existAlready=true;
                n=i;
                break;
            }
        }

        if (existAlready){
            listSnack.get(n).setNumeroTarjeta(item.getNumeroTarjeta());
        }else {
            listSnack.add(item);
        }

        tinyDB.putListObject("CardList", listSnack);
        Toast.makeText(context,"Añadido a tu carrito", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<SnackDominio> getListCard(){
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberSnack(ArrayList<SnackDominio> listsnack, int position, ChangeNumberItemListener changeNumberItemListener){
        listsnack.get(position).setNumeroTarjeta(listsnack.get(position).getNumeroTarjeta()+1);
        tinyDB.putListObject("CardList", listsnack);
        changeNumberItemListener.changed();
    }

    public void minusNumberSnack(ArrayList<SnackDominio> listsnack, int position, ChangeNumberItemListener changeNumberItemListener){
        if (listsnack.get(position).getNumeroTarjeta()==1){
            listsnack.remove(position);
        }else {
            listsnack.get(position).setNumeroTarjeta(listsnack.get(position).getNumeroTarjeta()-1);
        }
        tinyDB.putListObject("CardList", listsnack);
        changeNumberItemListener.changed();
    }

    public Double getTotalPrecio(){
        ArrayList<SnackDominio> listsnack2=getListCard();
        double precio=0;
        for (int i = 0; i < listsnack2.size(); i++){
            precio = precio + (listsnack2.get(i).getTarifa()*listsnack2.get(i).getNumeroTarjeta());
        }

        return precio;
    }
}
