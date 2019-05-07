package com.example.healttecapp;

import android.widget.ImageView;

public class PisteLaskuri {

    private int pisteidenYhteisumma;

    public PisteLaskuri(){
        this.pisteidenYhteisumma = mitkaPisteet();
    }

    public int mitkaPisteet(){

//        ArrayList l;
//        l = ActivityDatabaseAdapter.getAllTotalPoints();
//
//        // antaa edellisen päivän kokonais pisteet
//        int pisteet = (int) l.get(l.size() -1);

        int pisteet = ActivityDatabaseAdapter.getTotallPoints();

        return pisteet;

    }

    // palauttaa maskotin drawle tiedoston nimen mikä tulee näyttää etusivulla
    public ImageView mikaMaskotti(ImageView ukko){

        if (this.pisteidenYhteisumma < 10){
            ukko.setImageResource(R.drawable.ukko1);
        }else if (this.pisteidenYhteisumma >= 10 && this.pisteidenYhteisumma < 20){
            ukko.setImageResource(R.drawable.ukko2);
        }else if (this.pisteidenYhteisumma >= 20 && this.pisteidenYhteisumma < 30){
            ukko.setImageResource(R.drawable.ukko3);
        }else if (this.pisteidenYhteisumma >= 30 && this.pisteidenYhteisumma < 40){
            ukko.setImageResource(R.drawable.ukko4);
        }else if (this.pisteidenYhteisumma > 40){
            ukko.setImageResource(R.drawable.ukko5);
        }

        return ukko;

    }


}
