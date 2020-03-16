package proyectn.com.menzaobroci;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class RecieverPrijatno extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Uspesno!", Toast.LENGTH_SHORT).show();

        UpravljanjeObrocima upravljanjeObrocima = new UpravljanjeObrocima(context);
        upravljanjeObrocima.skiniObrok(Calendar.getInstance(), context);
    }
}
