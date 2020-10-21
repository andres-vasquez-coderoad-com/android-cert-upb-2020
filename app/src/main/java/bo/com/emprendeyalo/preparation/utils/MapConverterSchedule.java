package bo.com.emprendeyalo.preparation.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class MapConverterSchedule {
    @TypeConverter
    public Map<String, List<String>> fromString(String value) {
        Type mapType = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public String fromStringMap(Map<String, List<String>> map) {
        return new Gson().toJson(map);
    }
}
