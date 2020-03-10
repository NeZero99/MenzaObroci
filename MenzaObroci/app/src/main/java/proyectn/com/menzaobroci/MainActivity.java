package proyectn.com.menzaobroci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int test = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void prijatnoOnClick(View view) {
        test++;
        TextView t = (TextView) findViewById(R.id.tvDorucak);
        t.setText("Dorucak: " + test);
    }
}
