package proyectn.com.menzaobroci;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

public class UpravljanjeObrocima {
    private int dorucak;
    private int rucak;
    private int vecera;

    private SharedPreferences sharedPreferences;

    private Calendar dorucakStart;
    private Calendar dorucakEnd;
    private Calendar rucakStart;
    private Calendar rucakEnd;
    private Calendar veceraStart;
    private Calendar veceraEnd;

    public UpravljanjeObrocima(Context context){
        sharedPreferences = context.getSharedPreferences(Edit.MAIN_PREFS, 0);
        preuzmiIzShareda();
        radnoVreme(Calendar.getInstance());
    }

    public void preuzmiIzShareda(){
        dorucak = sharedPreferences.getInt(Edit.DORUCAK_PREF, 0);
        rucak = sharedPreferences.getInt(Edit.RUCAK_PREF, 0);
        vecera = sharedPreferences.getInt(Edit.VECERA_PREF, 0);
    }

    private void radnoVreme(Calendar cal){
        int danNedelji = cal.get(Calendar.DAY_OF_WEEK);
        dorucakStart = Calendar.getInstance();
        dorucakEnd = Calendar.getInstance();
        rucakStart = Calendar.getInstance();
        rucakEnd = Calendar.getInstance();
        veceraStart = Calendar.getInstance();
        veceraEnd = Calendar.getInstance();

        rucakStart.set(Calendar.HOUR_OF_DAY, 10);
        rucakStart.set(Calendar.MINUTE, 45);

        dorucakStart.set(Calendar.HOUR_OF_DAY, 7);
        dorucakStart.set(Calendar.MINUTE, 0);
        dorucakEnd.set(Calendar.HOUR_OF_DAY, 9);
        dorucakEnd.set(Calendar.MINUTE, 10);
        if(danNedelji == 6 || danNedelji == 7){
            rucakEnd.set(Calendar.HOUR_OF_DAY, 14);
            rucakEnd.set(Calendar.MINUTE, 30);
        }
        else{
            veceraStart.set(Calendar.HOUR_OF_DAY, 16);
            veceraStart.set(Calendar.MINUTE, 45);
            veceraEnd.set(Calendar.HOUR_OF_DAY, 20);
            veceraEnd.set(Calendar.MINUTE, 10);

            rucakEnd.set(Calendar.HOUR_OF_DAY, 15);
            rucakEnd.set(Calendar.MINUTE, 10);
        }
    }

    public void skiniObrok(Calendar cal, Context context){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(cal.getTimeInMillis() >= dorucakStart.getTimeInMillis() && cal.getTimeInMillis() <= dorucakEnd.getTimeInMillis()){
            if(dorucak != 0) {
                dorucak--;
                editor.putInt(Edit.DORUCAK_PREF, dorucak);
                //postaviView();
                Toast.makeText(context, "Preostalo vam je jos " + Integer.toString(dorucak) + " dorucka.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Nemate vise dorucka", Toast.LENGTH_SHORT).show();
            }
        }
        else if(cal.getTimeInMillis() >= rucakStart.getTimeInMillis() && cal.getTimeInMillis() <= rucakEnd.getTimeInMillis()){
            if(rucak != 0) {
                rucak--;
                editor.putInt(Edit.RUCAK_PREF, rucak);
                //postaviView();
                Toast.makeText(context, "Preostalo vam je jos " + Integer.toString(rucak) + " rucka.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Nemate vise ruckova", Toast.LENGTH_SHORT).show();
            }
        }
        else if(cal.getTimeInMillis() >= veceraStart.getTimeInMillis() && cal.getTimeInMillis() <= veceraEnd.getTimeInMillis()){
            if(vecera != 0) {
                vecera--;
                editor.putInt(Edit.VECERA_PREF, vecera);
                //postaviView();
                Toast.makeText(context, "Preostalo vam je jos " + Integer.toString(vecera) + " vecera.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Nemate vise vecera", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context, "Ne radi menza rodjace!", Toast.LENGTH_SHORT).show();
        }

        editor.apply();
    }

    public int getVecera() {
        return vecera;
    }

    public int getRucak() {
        return rucak;
    }

    public int getDorucak() {
        return dorucak;
    }
}
