package proyectn.com.menzaobroci;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpravljanjeObrocima {
    private int dorucak;
    private int rucak;
    private int vecera;

    private String log;

    private SharedPreferences sharedPreferences;

    private Calendar dorucakStart;
    private Calendar dorucakEnd;
    private Calendar rucakStart;
    private Calendar rucakEnd;
    private Calendar veceraStart;
    private Calendar veceraEnd;

    public UpravljanjeObrocima(Context context, boolean skiniVeceru){
        sharedPreferences = context.getSharedPreferences(Edit.MAIN_PREFS, 0);
        preuzmiIzShareda();
        log = sharedPreferences.getString(Istorija.ISTORIJA_PREF, "");
        if(!skiniVeceru) {
            radnoVreme(Calendar.getInstance());
        }
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
        if(danNedelji == Calendar.SATURDAY || danNedelji == Calendar.SUNDAY){
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
                editor.putString(Istorija.ISTORIJA_PREF, dodajLog(1, cal));
                Toast.makeText(context, "Broj preostalih doručaka: " + Integer.toString(dorucak), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Nemate više doručaka", Toast.LENGTH_SHORT).show();
            }
        }
        else if(cal.getTimeInMillis() >= rucakStart.getTimeInMillis() && cal.getTimeInMillis() <= rucakEnd.getTimeInMillis()){
            if(rucak != 0) {
                rucak--;
                editor.putInt(Edit.RUCAK_PREF, rucak);
                editor.putString(Istorija.ISTORIJA_PREF, dodajLog(2, cal));
                Toast.makeText(context, "Broj preostalih ručkova: " + Integer.toString(rucak), Toast.LENGTH_SHORT).show();
                if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    iVecera(context);
                }
            }
            else{
                Toast.makeText(context, "Nemate vise ručkova", Toast.LENGTH_SHORT).show();
            }
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                cal.getTimeInMillis() >= veceraStart.getTimeInMillis() && cal.getTimeInMillis() <= veceraEnd.getTimeInMillis()) {
            if (vecera != 0) {
                vecera--;
                editor.putInt(Edit.VECERA_PREF, vecera);
                editor.putString(Istorija.ISTORIJA_PREF, dodajLog(3, cal));
                Toast.makeText(context, "Broj preostalih večera: " + Integer.toString(vecera), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Nemate vise večera", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context, "Ne radi menza rođače!", Toast.LENGTH_SHORT).show();
        }
        //iVecera(context);
        editor.apply();

        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                Log.v("Dan", "ponedeljak");
                break;
            case Calendar.TUESDAY:
                Log.v("Dan", "utorak");
                break;
            case Calendar.WEDNESDAY:
                Log.v("Dan", "sreda");
                break;
            case Calendar.THURSDAY:
                Log.v("Dan", "cetvrtak");
                break;
            case Calendar.FRIDAY:
                Log.v("Dan", "petak");
                break;
            case Calendar.SATURDAY:
                Log.v("Dan", "subota");
                break;
            case Calendar.SUNDAY:
                Log.v("Dan", "nedelja");
                break;
            default:
                break;
        }
    }

    public void skiniVeceru(Context context, Calendar cal){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(vecera != 0) {
            vecera--;
            editor.putInt(Edit.VECERA_PREF, vecera);
            editor.putString(Istorija.ISTORIJA_PREF, dodajLog(3, cal));
            Toast.makeText(context, "Broj preostalih večera: " + Integer.toString(vecera), Toast.LENGTH_SHORT).show();
            editor.apply();
        }
        else{
            Toast.makeText(context, "Nemate vise večera", Toast.LENGTH_SHORT).show();
        }
    }

    private String dodajLog(int obrok, Calendar cal){//1 dorucak, 2 rucak, 3 vecera
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
        String ispis = simpleDateFormat.format(cal.getTime());
        if(obrok == 1){
            ispis += "\tDorčak: -1";
        }
        else if(obrok == 2){
            ispis += "\tRučak: -1";
        }
        else{
            ispis += "\tVečera: -1";
        }
        ispis += "\n" + "\n";
        return ispis + log;
    }

    private void iVecera(Context context){
        Intent intent = new Intent(context, iVeceraService.class);
        context.startService(intent);
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
