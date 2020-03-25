package proyectn.com.menzaobroci;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class RecieverPrijatno extends BroadcastReceiver {

    public final static String ACTION_ID = "actionId";//0 dugme, 1 da, 2 ne
    private UpravljanjeObrocima upravljanjeObrocima;

    @Override
    public void onReceive(Context context, Intent intent) {
        int action = intent.getIntExtra(ACTION_ID, 0);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(action == 0) {
            upravljanjeObrocima = new UpravljanjeObrocima(context, false);
            upravljanjeObrocima.skiniObrok(Calendar.getInstance(), context);
        }
        else if(action == 1){
            upravljanjeObrocima = new UpravljanjeObrocima(context, true);
            upravljanjeObrocima.skiniVeceru(context, Calendar.getInstance());
            manager.cancel(iVeceraService.NOTIFICATION_ID);
            Intent intent1 = new Intent(context, MainActivity.class);
            context.startActivity(intent1);
        }
    }
}
