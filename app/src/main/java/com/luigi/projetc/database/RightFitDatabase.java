package com.luigi.projetc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.luigi.projetc.database.dao.AlimentoDao;
import com.luigi.projetc.database.entities.AlimentoEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlimentoEntity.class}, version = 1)
public abstract class RightFitDatabase extends RoomDatabase {
    public abstract AlimentoDao alimentoDao();

    private static volatile RightFitDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RightFitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RightFitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RightFitDatabase.class, "right_fit_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build()

                    ;
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AlimentoDao dao = INSTANCE.alimentoDao();


                dao.insertAlimento();

            });
        }
    };
}
