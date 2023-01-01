package com.jiahongw.wantee.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jiahongw.wantee.model.notion.OptionModel;
import com.jiahongw.wantee.model.notion.SelectModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * Notion信息解析工具类
 *
 * @author VictorHong
 * @date 2023/1/1
 */
public class NotionParseUtils {

    private static final String PROPERTIES = "properties";

    /*================= database 解析 ========================*/

    /**
     * 获取database所有属性
     *
     * @param databaseJsonText
     * @return
     */
    public static JSONObject getPropertiesJSONObject(String databaseJsonText) {
        JSONObject databaseObject = JSONObject.parseObject(databaseJsonText);
        return databaseObject.getJSONObject(PROPERTIES);
    }

    /**
     * 获取属性
     *
     * @param databaseJsonText
     * @param propertyName
     * @return
     */
    public static JSONObject getPropertyJSONObject(String databaseJsonText, String propertyName) {
        JSONObject databaseObject = JSONObject.parseObject(databaseJsonText);
        String path = StringUtils.joinWith(".", PROPERTIES, propertyName);
        return (JSONObject) databaseObject.getByPath(path);
    }

    /**
     * 通过属性名称获取选择属性
     * @param databaseJsonText
     * @param propertyName
     * @return
     */
    public static SelectModel getSelectModelByPropertyName(String databaseJsonText, String propertyName) {
        JSONObject propertyJSONObject = NotionParseUtils.getPropertyJSONObject(databaseJsonText, propertyName);
        if (Objects.isNull(propertyJSONObject)) {
            return null;
        }
        if (Objects.isNull(propertyJSONObject.get("type")) || !"select"
            .equals(propertyJSONObject.get("type").toString())) {
            return null;
        }
        SelectModel selectModel = new SelectModel();
        selectModel.setId(propertyJSONObject.get("id").toString());
        selectModel.setName(propertyJSONObject.get("name").toString());
        selectModel.setType(propertyJSONObject.get("type").toString());
        JSONArray optionJsonArray = propertyJSONObject.getJSONObject(selectModel.getType()).getJSONArray("options");
        List<OptionModel> optionModelList = optionJsonArray.stream()
            .map(jsonObject -> JsonUtils.fromJson(jsonObject.toString(), OptionModel.class))
            .collect(Collectors.toList());
        selectModel.setOptions(optionModelList);
        return selectModel;
    }

    /*================= database 解析 end ========================*/

    /**
     * 使用递归的方式，把Json中的每个节点放到Map中
     *
     * @param object
     * @param map
     * @return
     */
    public static Map<String, Object> jsonLoop(Object object, Map<String, Object> map) {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String entryKey = entry.getKey();
                Object objValue = entry.getValue();
                map.put(entryKey, objValue);
                if (objValue instanceof JSONObject) {
                    JSONObject jsonObjectSun = (JSONObject) objValue;
                    if (jsonObjectSun.entrySet().size() >= 1) {
                        jsonLoop(objValue, map);
                    }
                }
            }
        }
        return map;
    }

    public static Map<String, Object> getMapFromJson(String jsonText) {
        JSONObject jsonObject = JSON.parseObject(jsonText);
        Map<String, Object> result = new HashMap<>();
        jsonLoop(jsonObject, result);
        return result;
    }

}
