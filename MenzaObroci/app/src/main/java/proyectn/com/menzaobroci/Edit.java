package proyectn.com.menzaobroci;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Edit extends AppCompatActivity implements View.OnClickListener {

    public static final String MAIN_PREFS = "mainPrefs";
    public static final String DORUCAK_PREF = "dorucak";
    public static final String RUCAK_PREF = "rucak";
    public static final String VECERA_PREF = "vecera";

    private int dorucak;
    private int rucak;
    private int vecera;

    private TextView dor;
    private TextView ruc;
    private TextView vec;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Izmeni obroke");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //textview
        dor = (TextView) findViewById(R.id.tvDorucakCnt);
        ruc = (TextView) findViewById(R.id.tvRucakCnt);
        vec = (TextView) findViewById(R.id.tvVeceraCnt);
        //sharedPref
        sharedPreferences = this.getSharedPreferences(MAIN_PREFS, 0);
        dorucak = sharedPreferences.getInt(DORUCAK_PREF, 0);
        rucak = sharedPreferences.getInt(RUCAK_PREF, 0);
        vecera = sharedPreferences.getInt(VECERA_PREF, 0);
        postaviView();
        //listeneri za Dugmice;
        Button m1 = (Button) findViewById(R.id.btnDorucakMinus);
        Button m2 = (Button) findViewById(R.id.btnRucakMinus);
        Button m3 = (Button) findViewById(R.id.btnVeceraMinus);
        Button p1 = (Button) findViewById(R.id.btnDorucakPlus);
        Button p2 = (Button) findViewById(R.id.btnRucakPlus);
        Button p3 = (Button) findViewById(R.id.btnVeceraPlus);
        Button save = (Button) findViewById(R.id.btnSacuvaj);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        m3.setOnClickListener(this);
        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void postaviView(){
        dor.setText(Integer.toString(dorucak));
        ruc.setText(Integer.toString(rucak));
        vec.setText(Integer.toString(vecera));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDorucakMinus:
                if(dorucak != 0){
                    dorucak--;
                    postaviView();
                }
                break;
            case R.id.btnDorucakPlus:
                dorucak++;
                postaviView();
                break;
            case R.id.btnRucakMinus:
                if(rucak != 0) {
                    rucak--;
                    postaviView();
                }
                break;
            case R.id.btnRucakPlus:
                rucak++;
                postaviView();
                break;
            case R.id.btnVeceraMinus:
                if(vecera != 0) {
                    vecera--;
                    postaviView();
                }
                break;
            case R.id.btnVeceraPlus:
                vecera++;
                postaviView();
                break;
            case R.id.btnSacuvaj:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(DORUCAK_PREF, dorucak);
                editor.putInt(RUCAK_PREF, rucak);
                editor.putInt(VECERA_PREF, vecera);
                editor.apply();

                Toast.makeText(this, "Obroci su sacuvani", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
