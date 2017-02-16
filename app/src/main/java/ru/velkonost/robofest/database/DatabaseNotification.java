package ru.velkonost.robofest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseNotification extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "notification_base.db";
    private final static int DATABASE_VERSION = 3;

    //создание таблицы notif_first
    private final static String CREATE_TABLE = "create table table_event (" +
            "_id integer primary key autoincrement, " +
            "event_name text, " +
            "event_theme text, " +
            "event_date text, " +
            "event_time text, " +
            "event_value integer);";

    //вставка конкурсов за первый день
    private final static String INSERT_DAY_FIRST = "insert into table_event (event_name, event_theme, " +
            "event_date, event_time, event_value) values " +
            "('Регистрация', '', '17.02.2017', '8:45', 1), " +
            "('Открытие', '', '17.02.2017', '9:45', 1), " +
            "('Hello, Robot! Lego', 'Тренировка', '17.02.2017', '10:45', 1), " +
            "('Hello, Robot! Arduino', 'Тренировка', '17.02.2017', '10:45', 1), " +
            "('Hello, Robot! Arduino', 'Соревнование', '17.02.2017', '13:45', 1), " +
            "('Hello, Robot! Arduino', 'Награждение', '17.02.2017', '16:45', 1), " +
            "('Jr. FLL', 'Соревнование', '17.02.2017', '10.45', 1), " +
            "('Jr. FLL', 'Награждение', '17.02.2017', '15:15', 1), " +
            "('FLL', 'Тренировка', '17.02.2017', '10:45', 1), " +
            "('FLL', 'Соревнование', '17.02.2017', '13:45', 1), " +
            "('FLL', 'Награждение', '17.02.2017', '16:45', 1), " +
            "('Робокарусель', 'Тренировка', '17.02.2017', '10:45', 1), " +
            "('Робокарусель', 'Соревнование', '17.02.2017', '13:45', 1), " +
            "('Робокарусель', 'Награждение', '17.02.2017', '16:45', 1);";

    //вставка конкурсов за второй день
    private final static String INSERT_DAY_SECOND = "insert into table_event (event_name, event_theme, " +
            "event_date, event_time, event_value) values " +
            "('Регистрация', '', '18.02.2017', '8:45', 1), " +
            "('Награждение', '', '18.02.2017', '17:15', 1), " +
            "('Hello, Robot! Lego', 'Тренировка', '18.02.2017', '9:45', 1), " +
            "('Hello, Robot! Lego', 'Соревнование', '18.02.2017', '12:45', 1), " +
            "('FLL', 'Тренировка', '18.02.2017', '10:45', 1), " +
            "('FLL', 'Соревнование', '18.02.2017', '13:45', 1), " +
            "('Робокарусель', 'Соревнование', '18.02.2017', '10:45', 1), " +
            "('Фристайл', 'Соревнование', '18.02.2017', '9:45', 1);";

    public DatabaseNotification(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(INSERT_DAY_FIRST);
        db.execSQL(INSERT_DAY_SECOND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE table_event");
        onCreate(db);
    }
}
