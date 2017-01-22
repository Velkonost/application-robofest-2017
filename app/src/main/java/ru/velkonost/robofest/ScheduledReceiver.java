package ru.velkonost.robofest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ScheduledReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Дзинь-дзинь! Пора кормить кота",
                Toast.LENGTH_LONG).show();

        buildNotification(context);
    }

    private void buildNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Напоминалка").setContentText("Внимание!")
                .setContentInfo("Накорми кота!").setTicker("Голодный котик")
                .setLights(0xFFFF0000, 500, 500)
                .setContentIntent(pendingIntent).setAutoCancel(true);

        Notification notification = builder.build();

        notificationManager.notify(2, notification);
    }

}