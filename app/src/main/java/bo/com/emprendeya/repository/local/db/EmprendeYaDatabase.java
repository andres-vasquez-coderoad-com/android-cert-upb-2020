package bo.com.emprendeya.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.local.dao.StartupDao;
import bo.com.emprendeya.utils.MapConverterCovidInfo;

@Database(entities = {Startup.class}, version = 2)
@TypeConverters(MapConverterCovidInfo.class)
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
