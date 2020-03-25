package proyectn.com.menzaobroci;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView dor;
    private TextView ruc;
    private TextView vec;

    //private SharedPreferences sharedPreferences;

    private UpravljanjeObrocima upravljanjeObrocima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upravljanjeObrocima = new UpravljanjeObrocima(this, false);

        dor = (TextView) findViewById(R.id.tvDorucak);
        ruc = (TextView) findViewById(R.id.tvRucak);
        vec = (TextView) findViewById(R.id.tvVecera);

        postaviView();

        Calendar temp = Calendar.getInstance();
        Log.v("Vreme", Integer.toString(temp.get(Calendar.DAY_OF_WEEK)));
    }

    private void postaviView(){
        dor.setText(getString(R.string.dorucak) + " " + Integer.toString(upravljanjeObrocima.getDorucak()));
        ruc.setText(getString(R.string.rucak) + " " + Integer.toString(upravljanjeObrocima.getRucak()));
        vec.setText(getString(R.string.vecera) + " " + Integer.toString(upravljanjeObrocima.getVecera()));
    }

    public void prijatnoOnClick(View view) {
        Calendar cal = Calendar.getInstance();
        upravljanjeObrocima.skiniObrok(Calendar.getInstance(), this);
        postaviView();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, Edit.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.action_istorija){
            Intent intent = new Intent(this, Istorija.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.action_info){
            Intent intent = new Intent(this, Info.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        upravljanjeObrocima.preuzmiIzShareda();
        postaviView();
    }
}
