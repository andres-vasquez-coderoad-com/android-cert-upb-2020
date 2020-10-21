package bo.com.emprendeya.preparation.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.com.emprendeya.preparation.models.Startup;

@Dao
public interface StartupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insert(List<Startup> startups);

    @Query("DELETE FROM startup_table")
    void deleteAll();

    @Query("SELECT * from startup_table ORDER BY uuid ASC")
    LiveData<List<Startup>> getAll();
}
