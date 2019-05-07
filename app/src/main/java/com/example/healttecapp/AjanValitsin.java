package com.example.healttecapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;

/**
 * @author Jimi Hjelt
 */

public class AjanValitsin extends DialogFragment {
    /** Luodaan kellodialogi, josta voidaan valita tunnit ja minuutit.
     *
     * @param savedInstanceState Palauttaa aiemman tilan kellonajasta.
     * @return Palauttaa kellonajan valitsimen, jonka aika ensimmäisellä avauksella on sama kuin puhelimen kellon ja tuntijärjestelmä määräytyy puhelimen asetusten mukaan (24h vai 12h)
     */
    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int tunti = c.get(Calendar.HOUR_OF_DAY);
        int minuutti = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), tunti, minuutti, android.text.format.DateFormat.is24HourFormat(getActivity()) );
    }
}
