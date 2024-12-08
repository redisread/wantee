package com.notekeeper.domain.util;


import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Json 序列化工具
 *
 * @author wujiahong06
 * @date 2022/12/28
 */
public class JsonUtils {

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create();

    private static JsonParser jsonParser = new JsonParser();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String values, Class<T> cls) {
        return GSON.fromJson(values, cls);
    }

    public static <T> T fromJsonByType(String values, Type type) {
        return GSON.fromJson(values, type);
    }

    /**
     * Json字符串转换成List
     **/
    public static <T> List<T> parseString2List(String json, Class<T> clazz) {
        //泛型转换
        Type type = new ParameterizedTypeImpl(clazz);
        return GSON.fromJson(json, type);
    }

    /**
     * Json字符串转换成JsonObject
     **/
    public static JsonObject fromStringToObject(String strJson) {
        return jsonParser.parse(strJson).getAsJsonObject();
    }

    /**
     * Json字符串转换成JsonArray
     **/
    public static JsonArray fromStringToArray(String strJson) {
        return jsonParser.parse(strJson).getAsJsonArray();
    }

    /**
     * Gson解析不支持泛型，利用ParameterizedType获取泛型参数类型
     */
    private static class ParameterizedTypeImpl implements ParameterizedType {

        private Type clazz;

        public <T> ParameterizedTypeImpl(Class<T> clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            //返回实际类型组成的数据
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            //返回原生类型，即HashMap
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            //返回Type对象
            return null;
        }
    }



    /**
     * 获取Json字符串内部某个属性
     *
     * @param jsonText
     * @param propertyName
     * @return
     */
    public static JSONObject getPropertyJSONObject(String jsonText, String propertyName) {
        JSONObject databaseObject = JSONObject.parseObject(jsonText);
        return (JSONObject) databaseObject.get(propertyName);
    }


}

