package bo.com.emprendeya.preparation.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class MapConverterCovidInfo {
    @TypeConverter
    public Map<String, String> fromString(String value) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public String fromStringMap(Map<String, String> map) {
        return new Gson().toJson(map);
    }
}
