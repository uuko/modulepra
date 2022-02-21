package com.example.modulepra.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainTable.class}, version = 1, exportSchema = false)
public abstract class MainDBDataBase extends RoomDatabase {
    private static final String DB_NAME = "alertDatabase.db";
    private static volatile MainDBDataBase instance;

    public static MainDBDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }


    private static MainDBDataBase create(final Context context) {
        return Room.databaseBuilder(context, MainDBDataBase.class, DB_NAME)
                .build();
//          .addMigrations(MIGRATION_1_2)
    }

//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE EventDBTable " + "ADD COLUMN Note TEXT");
//        }
//    };

    public abstract MainDbDao mainDbDao();
}
