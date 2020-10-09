package bo.com.emprendeya.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.repository.local.dao.StartupDao;
import bo.com.emprendeya.utils.MapConverterCovidInfo;
import bo.com.emprendeya.utils.MapConverterSchedule;

@Database(entities = {Startup.class}, version = 1)
@TypeConverters({MapConverterCovidInfo.class, MapConverterSchedule.class})
public abstract class StartupDatabase extends RoomDatabase {
    public abstract StartupDao startupApiDao();

    private static volatile StartupDatabase INSTANCE;

    static public StartupDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StartupDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StartupDatabase.class, "startups_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
