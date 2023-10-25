package work.keepcode.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class GsonUtils {
    public static final Gson GSON;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        GSON = builder.create();
    }

    private GsonUtils() {
    }


    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static String toJsonQuietly(Object obj) {
        try {
            return GSON.toJson(obj);
        } catch (Exception e) {
            log.error("toJsonQuietly error", e);
            return "";
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> T fromJsonQuietly(String json, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (Exception e) {
            log.error("fromJsonQuietly error", e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        return GSON.fromJson(json, typeToken.getType());
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken, T defaultValue) {
        try {
            return GSON.fromJson(json, typeToken.getType());
        } catch (Exception e) {
            log.error("fromJson error", e);
            return defaultValue;
        }
    }
}
