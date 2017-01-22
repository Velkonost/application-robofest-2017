package services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmService extends Service {
    @Override
    public void onCreate() {
        Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG)
                .show();
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "MyAlarmService.onStartCommand()", Toast.LENGTH_LONG)
                .show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG)
                .show();
        return super.onUnbind(intent);
    }
}