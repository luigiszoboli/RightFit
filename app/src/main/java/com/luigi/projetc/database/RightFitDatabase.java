package com.luigi.projetc.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.luigi.projetc.database.dao.AlimentoDao;
import com.luigi.projetc.database.dao.DietaDao;
import com.luigi.projetc.database.dao.ImcDao;
import com.luigi.projetc.database.dao.MetaDao;
import com.luigi.projetc.database.entities.AlimentoEntity;
import com.luigi.projetc.database.entities.DietaEntity;
import com.luigi.projetc.database.entities.ImcEntity;
import com.luigi.projetc.database.entities.MetaEntity;
import com.luigi.projetc.database.entities.converters.DateConverter;
import com.luigi.projetc.model.IMCDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlimentoEntity.class, DietaEntity.class, ImcEntity.class, MetaEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class RightFitDatabase extends RoomDatabase {
    public abstract AlimentoDao alimentoDao();
    public abstract DietaDao dietaDao();
    public abstract ImcDao imcDao();
    public abstract MetaDao metaDao();

    private static volatile RightFitDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RightFitDatabase getDatabase(final Context context) {
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
                Log.e("RightFitDatabase", "Criando banco");
                AlimentoDao dao = INSTANCE.alimentoDao();
                DietaDao dieta = INSTANCE.dietaDao();
                ImcDao imc = INSTANCE.imcDao();
                MetaDao metaDao = INSTANCE.metaDao();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                dao.insertAlimento(new AlimentoEntity(0, "primeiro", 1,1,1,1,1,1,1));
                dao.insertAlimento(new AlimentoEntity(0, "alimento", 2,2,2,2,2,2,2));
                dao.insertAlimento(new AlimentoEntity(0, "nome", 1,1,1,1,1,1,1));

                dieta.insertDieta(new DietaEntity(0, "3", 2, 2,  0, simpleDateFormat.format(new Date())));
                dieta.insertDieta(new DietaEntity(0, "3", 1, 3, 0, simpleDateFormat.format(new Date())));
                dieta.insertDieta(new DietaEntity(0, "2", 1, 3, 0, simpleDateFormat.format(new Date())));

                metaDao.insertMeta(new MetaEntity(0, simpleDateFormat.format(new Date()), 20, "3"));

                imc.insertImc(new ImcEntity(0, 30, 180, "3", simpleDateFormat.format(new Date())));
            });
        }
    };
}
