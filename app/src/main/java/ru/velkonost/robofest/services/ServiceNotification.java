package ru.velkonost.robofest.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import ru.velkonost.robofest.MainActivity;
import ru.velkonost.robofest.R;
import ru.velkonost.robofest.database.DatabaseNotification;

public class ServiceNotification extends Service {

    private static final int INTERVAL_NOTIFICATION = 30000;

    private static final String QUERY_SELECT = "SELECT _id, event_name, event_theme " +
            "FROM table_event WHERE (event_time = '%s') " +
            "AND (event_date = '%s') AND (event_value = 1)";

    private static final String QUERY_UPDATE = "UPDATE table_event SET event_value = 0 WHERE _id = %s";

    private DatabaseNotification notificationDatabase;
    private NotificationManager notificationManager;
    private Intent intentMainActivity;
    private PendingIntent pendingIntent;
    private NotificationCompat.Builder builder;
    private Timer timer;

    @Override
    public void onCreate() {
        notificationDatabase = new DatabaseNotification(this);
        intentMainActivity = new Intent(ServiceNotification.this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(ServiceNotification.this, 0, intentMainActivity,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        timer = new Timer();
        initNotification();
        call();
    }

    private void initNotification(){
        builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentIntent(pendingIntent);
        builder.setTicker("Приближается мероприятие");
        builder.setSmallIcon(R.drawable.ic_notifications_none_white_24dp);
        builder.setAutoCancel(true);
        builder.setContentTitle("Через 15 мин. начнется мероприятие");
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
    }

    private void showNotification(int id, String name, String theme){
        builder.setShowWhen(true);
        builder.setWhen(System.currentTimeMillis());
        if (theme.equals(""))
            builder.setContentText(name);
        else
            builder.setContentText(name + " - " + theme);
        notificationManager.notify(id, builder.build());
    }

    private void process(){
        SQLiteDatabase database = notificationDatabase.getReadableDatabase();
        String s1 = getCurrentTime();
//        String s1 = "10:45";
        String s2 = getCurrentDate();
//        String s2 = "17.02.2017";
        String textQuery = String.format(QUERY_SELECT, s1, s2);
        Cursor cursor = database.rawQuery(textQuery, null);
        int id = 1;
        while (cursor.moveToNext()){
            int rowId = cursor.getInt(0);
            String name = cursor.getString(1);
            String theme = cursor.getString(2);
            showNotification(id, name, theme);
            id ++;
            updateRow(rowId);
        }
        cursor.close();
    }

    private void updateRow(int rowId){
        SQLiteDatabase database = notificationDatabase.getWritableDatabase();
        String textQuery = String.format(QUERY_UPDATE, rowId);
        database.execSQL(textQuery);
    }

    private void call(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                process();
            }
        }, 0, INTERVAL_NOTIFICATION);
    }

    //получение текущей даты устройства
    private String getCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return (day < 10 ? "0" + day : day)+"."+(month < 10 ? "0" + month : month)+"." + year;
    }

    //получение текущего времени устройства
    private String getCurrentTime() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour + ":" + (minute < 10 ? "0" + minute : minute);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
