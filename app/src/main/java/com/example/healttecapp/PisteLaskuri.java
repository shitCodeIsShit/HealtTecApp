package com.example.healttecapp;

import android.widget.ImageView;

public class PisteLaskuri {

    /**
     * @author Valtteri Mäntymäki
     */

    private int pisteidenYhteisumma;

    public PisteLaskuri() {
        this.pisteidenYhteisumma = mitkaPisteet();
    }

    /** Hakee kannasta vimmeisimmän tämän päivän totaali pisteet
     * @return palauttaa viimmeisimmän totaali pisteet
     */
    public int mitkaPisteet() {

        int pisteet = ActivityDatabaseAdapter.getTotallPoints();

        return pisteet;

    }

    /**
     * palauttaa maskotin drawle tiedoston nimen mikä tulee näyttää etusivulla. Palautettava
     * kuva riippuu kannassa olevasta vimmeisimmästä totaali pisteistä.
     * @param ukko ottaa kuvan sisään ja asettaa sille uuden ImageResource osoitteen
     * @return palauttaa parametrinä saamansa kuvan uudella resurssi arvolla
     */
    public ImageView mikaMaskotti(ImageView ukko) {

        if (this.pisteidenYhteisumma < 10) {
            ukko.setImageResource(R.drawable.ukko1);
        } else if (this.pisteidenYhteisumma >= 10 && this.pisteidenYhteisumma < 20) {
            ukko.setImageResource(R.drawable.ukko2);
        } else if (this.pisteidenYhteisumma >= 20 && this.pisteidenYhteisumma < 30) {
            ukko.setImageResource(R.drawable.ukko3);
        } else if (this.pisteidenYhteisumma >= 30 && this.pisteidenYhteisumma < 40) {
            ukko.setImageResource(R.drawable.ukko4);
        } else if (this.pisteidenYhteisumma > 40) {
            ukko.setImageResource(R.drawable.ukko5);
        }

        return ukko;

    }


}
