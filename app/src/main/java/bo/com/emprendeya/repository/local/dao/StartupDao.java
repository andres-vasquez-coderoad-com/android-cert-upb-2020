package bo.com.emprendeya.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.com.emprendeya.model.Startup;

@Dao
public interface StartupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Startup> startups);

    @Query("SELECT * FROM startup_table ORDER BY displayName ASC")
    LiveData<List<Startup>> getAll();

    @Query("DELETE FROM startup_table")
    void deleteAll();
}
