package ru.velkonost.robofest.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Initializations {

    public static void changeActivityCompat(final Activity a, Intent nextIntent) {
        final Intent currentIntent = a.getIntent();
        nextIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


        if (currentIntent.filterEquals(nextIntent)) {
            a.finish();
        }
        a.overridePendingTransition(0, 0);

        a.startActivity(nextIntent);
        a.overridePendingTransition(0, 0);
    }

    /**
     * Проверка интернет-соединения.
     **/
    public static boolean hasConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
