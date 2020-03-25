package proyectn.com.menzaobroci;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Istorija extends AppCompatActivity {

    public static final String ISTORIJA_PREF = "istorija";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istorija);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Istorija");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //shared
        SharedPreferences sharedPreferences = this.getSharedPreferences(Edit.MAIN_PREFS, 0);
        String log = sharedPreferences.getString(ISTORIJA_PREF, "");
        //textView
        TextView textView = (TextView) findViewById(R.id.istorijaText);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(log);
    }
}
