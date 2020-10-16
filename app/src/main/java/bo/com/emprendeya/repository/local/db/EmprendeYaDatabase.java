package bo.com.emprendeya.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.local.dao.StartupDao;

@Database(entities = {Startup.class}, version = 1)
public abstract class EmprendeYaDatabase extends RoomDatabase {
    private static volatile EmprendeYaDatabase INSTANCE;

    static public EmprendeYaDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (EmprendeYaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EmprendeYaDatabase.class,
                            "emprendeya_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract StartupDao startupDao();
}
