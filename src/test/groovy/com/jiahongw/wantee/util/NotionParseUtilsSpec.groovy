package com.jiahongw.wantee.util


import com.alibaba.fastjson2.JSONObject
import com.jiahongw.wantee.model.notion.SelectModel
import spock.lang.Specification

class NotionParseUtilsSpec extends Specification {

    private String jsonText

    def setup() {
        jsonText = "{\"object\":\"database\",\"id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"cover\":{\"type\":\"external\",\"external\":{\"url\":\"https://images.unsplash.com/photo-1586381135803-7e9091ed0690?ixlib=rb-1.2.1&q=80&cs=tinysrgb&fm=jpg&crop=entropy\"}},\"icon\":{\"type\":\"emoji\",\"emoji\":\"✏️\"},\"created_time\":\"2022-10-03T12:15:00.000Z\",\"created_by\":{\"object\":\"user\",\"id\":\"73157947-0f30-4881-b55c-fbcebdbed2c3\"},\"last_edited_by\":{\"object\":\"user\",\"id\":\"73157947-0f30-4881-b55c-fbcebdbed2c3\"},\"last_edited_time\":\"2022-12-25T13:06:00.000Z\",\"title\":[{\"type\":\"text\",\"text\":{\"content\":\"卡片盒笔记(整理)\",\"link\":null},\"annotations\":{\"bold\":false,\"italic\":false,\"strikethrough\":false,\"underline\":false,\"code\":false,\"color\":\"default\"},\"plain_text\":\"卡片盒笔记(整理)\",\"href\":null}],\"description\":[],\"is_inline\":false,\"properties\":{\"URL\":{\"id\":\"%3CgI~\",\"name\":\"URL\",\"type\":\"url\",\"url\":{}},\"下一条\":{\"id\":\"%3Clwo\",\"name\":\"下一条\",\"type\":\"relation\",\"relation\":{\"database_id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"type\":\"dual_property\",\"dual_property\":{\"synced_property_name\":\"上一条\",\"synced_property_id\":\"iSL~\"}}},\"状态\":{\"id\":\"%3DQlu\",\"name\":\"状态\",\"type\":\"status\",\"status\":{\"options\":[{\"id\":\"dKW;\",\"name\":\"再次回顾\",\"color\":\"blue\"},{\"id\":\"d{P~\",\"name\":\"连接中\",\"color\":\"blue\"},{\"id\":\"0b343f85-0690-4ca4-8aaf-b82aa5d7619c\",\"name\":\"未开始\",\"color\":\"default\"},{\"id\":\"fe82c0df-285a-4727-b857-fc59003f49b2\",\"name\":\"处理中\",\"color\":\"blue\"},{\"id\":\"636fd67a-6ed3-4480-80c7-30b4dceaf316\",\"name\":\"完成\",\"color\":\"green\"}],\"groups\":[{\"id\":\"986de44e-0474-4a5b-b260-c9685b8d5a1d\",\"name\":\"To-do\",\"color\":\"gray\",\"option_ids\":[\"0b343f85-0690-4ca4-8aaf-b82aa5d7619c\"]},{\"id\":\"2fa8a02a-60b6-4f2a-88e9-ccc70df1683e\",\"name\":\"In progress\",\"color\":\"blue\",\"option_ids\":[\"dKW;\",\"d{P~\",\"fe82c0df-285a-4727-b857-fc59003f49b2\"]},{\"id\":\"61cb4b2e-370f-4c1c-8f13-6df23089cd49\",\"name\":\"Complete\",\"color\":\"green\",\"option_ids\":[\"636fd67a-6ed3-4480-80c7-30b4dceaf316\"]}]}},\"类型\":{\"id\":\"%40TKk\",\"name\":\"类型\",\"type\":\"select\",\"select\":{\"options\":[{\"id\":\";uRi\",\"name\":\"视频\",\"color\":\"pink\"},{\"id\":\"ko\\\\~\",\"name\":\"文章\",\"color\":\"yellow\"},{\"id\":\"XTZW\",\"name\":\"音频\",\"color\":\"blue\"},{\"id\":\"`dQS\",\"name\":\"书籍\",\"color\":\"brown\"},{\"id\":\"251a4ece-af73-40a4-a134-0243aeacc89d\",\"name\":\"想法\",\"color\":\"purple\"},{\"id\":\"7fc4d5a8-8bc9-46f0-85b4-3bca4583136a\",\"name\":\"笔记\",\"color\":\"gray\"}]}},\"入口卡片\":{\"id\":\"I_pk\",\"name\":\"入口卡片\",\"type\":\"relation\",\"relation\":{\"database_id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"type\":\"dual_property\",\"dual_property\":{\"synced_property_name\":\"入口卡片指向笔记\",\"synced_property_id\":\"nQr%3D\"}}},\"变得很快的随机数\":{\"id\":\"MEhH\",\"name\":\"变得很快的随机数\",\"type\":\"formula\",\"formula\":{\"expression\":\"mod(mod(timestamp(prop(\\\"创建时间\\\")) * 1.00011979e+8 + 5.00067713e+8, 9.00066731e+8) * mod(timestamp(now()) * 8.00067089e+8 + 8.00068411e+8, 8.00053967e+8) + 9.00067309e+8, 9.00066571e+8)\"}},\"笔记掌握度\":{\"id\":\"Pb%7DI\",\"name\":\"笔记掌握度\",\"type\":\"select\",\"select\":{\"options\":[{\"id\":\"nnPd\",\"name\":\"新鲜\",\"color\":\"gray\"},{\"id\":\"MXwW\",\"name\":\"评估中\",\"color\":\"pink\"},{\"id\":\"IztI\",\"name\":\"尝试\",\"color\":\"red\"},{\"id\":\"{tRY\",\"name\":\"内化\",\"color\":\"green\"},{\"id\":\"NljZ\",\"name\":\"执行完成\",\"color\":\"brown\"},{\"id\":\"pT[z\",\"name\":\"记忆\",\"color\":\"default\"}]}},\"更新时间\":{\"id\":\"XjaJ\",\"name\":\"更新时间\",\"type\":\"last_edited_time\",\"last_edited_time\":{}},\"摘要\":{\"id\":\"Z%3Fd_\",\"name\":\"摘要\",\"type\":\"rich_text\",\"rich_text\":{}},\"标签\":{\"id\":\"ZtNX\",\"name\":\"标签\",\"type\":\"multi_select\",\"multi_select\":{\"options\":[{\"id\":\"a0f78f30-6c85-4a5b-8b18-e9fc311bbd8e\",\"name\":\"生产力\",\"color\":\"blue\"},{\"id\":\"a70be6a0-12e2-4e1b-92ad-b29ea29a7bb8\",\"name\":\"学习\",\"color\":\"brown\"},{\"id\":\"da2369ed-48df-4b3e-8c4f-9dd25d32f9ef\",\"name\":\"卡片盒笔记法\",\"color\":\"yellow\"},{\"id\":\"f45e7c1d-79d6-43cc-ab42-5528bef9fed6\",\"name\":\"flomo\",\"color\":\"purple\"},{\"id\":\"6a58f82d-aab5-4a29-a8ef-29df4b79588d\",\"name\":\"方法论\",\"color\":\"purple\"},{\"id\":\"e8fc2f65-c2be-42bc-8de6-c311adcb3969\",\"name\":\"Notion\",\"color\":\"red\"},{\"id\":\"638315e2-95a8-4a08-b8ec-1150b4d0948e\",\"name\":\"第二大脑\",\"color\":\"green\"},{\"id\":\"77a91f72-4e6a-4e3c-88e1-fc00d8dda9f7\",\"name\":\"RSS\",\"color\":\"brown\"},{\"id\":\"4e8548a5-7774-49c3-aa36-5cb7f1c066c4\",\"name\":\"少数派\",\"color\":\"red\"},{\"id\":\"0c4fd7c7-6a5a-4bf6-b2b6-fc0e89e6ae14\",\"name\":\"极简\",\"color\":\"purple\"},{\"id\":\"aa2beae3-490a-4258-b251-df1e5033d698\",\"name\":\"效率\",\"color\":\"brown\"},{\"id\":\"9e003d02-0b2c-4905-89b3-bb0e0c974c52\",\"name\":\"知识管理\",\"color\":\"green\"},{\"id\":\"f6d52cad-3058-482b-b22d-0df49184edcf\",\"name\":\"认知\",\"color\":\"orange\"},{\"id\":\"f09210aa-afc5-4d18-939c-b4303a18934a\",\"name\":\"github\",\"color\":\"default\"},{\"id\":\"b3404d5e-3b04-4f92-8feb-764adfbb8a68\",\"name\":\"action\",\"color\":\"gray\"},{\"id\":\"f9cef514-a6cc-4135-89ce-c353e29669d7\",\"name\":\"持续集成\",\"color\":\"pink\"},{\"id\":\"5ab1b785-f59a-4a0d-8720-a0572635563f\",\"name\":\"自动化\",\"color\":\"red\"},{\"id\":\"ec89c3c0-157a-489e-9197-e83b4f529ab5\",\"name\":\"Java\",\"color\":\"purple\"},{\"id\":\"0ae41b43-186a-451d-b98f-5b47915e89b9\",\"name\":\"软件开发\",\"color\":\"purple\"},{\"id\":\"8976d6dd-b3f7-4729-b7f9-8ef3428e338e\",\"name\":\"面试\",\"color\":\"yellow\"},{\"id\":\"a74c8b03-e77e-4bb9-9cce-92cc143dc0ea\",\"name\":\"数据结构\",\"color\":\"yellow\"},{\"id\":\"978a8d7a-9634-436f-9760-f245377721fe\",\"name\":\"List\",\"color\":\"pink\"},{\"id\":\"5547e35b-85aa-417d-83b1-64a51353bd86\",\"name\":\"算法\",\"color\":\"pink\"},{\"id\":\"664ac0f3-bf2c-41c2-91fc-24ce075693d0\",\"name\":\"计算机\",\"color\":\"gray\"},{\"id\":\"cf7900b6-e0d1-4e00-bdcb-b33eef5a5d94\",\"name\":\"排序\",\"color\":\"red\"},{\"id\":\"3c66f5b7-6702-414c-99e0-8a984f57262e\",\"name\":\"并发\",\"color\":\"orange\"},{\"id\":\"a97808b5-aa04-497f-99b3-1c45abd0c02b\",\"name\":\"Volatile\",\"color\":\"purple\"},{\"id\":\"462a1b4f-9fed-4baf-9fcb-f3764840beef\",\"name\":\"ES\",\"color\":\"red\"},{\"id\":\"58ae9550-2f08-46d8-aa27-3cedc1c6a2a7\",\"name\":\"中间件\",\"color\":\"pink\"},{\"id\":\"b99de063-32f4-4410-a599-0f5cc891e011\",\"name\":\"最佳实践\",\"color\":\"yellow\"},{\"id\":\"2173eb01-3fbe-43b5-87f3-090737e5d2b9\",\"name\":\"vim\",\"color\":\"default\"},{\"id\":\"5aff129d-c789-45e6-a892-42a606b0faaa\",\"name\":\"谷歌插件\",\"color\":\"pink\"},{\"id\":\"976be9a1-fd3c-445f-9fc5-dfbd77514541\",\"name\":\"MySQL\",\"color\":\"brown\"},{\"id\":\"14fdbd68-5476-41f2-b55c-9dcace24e9e9\",\"name\":\"仓储\",\"color\":\"pink\"},{\"id\":\"d75f1d41-4a84-4140-a928-f1ebba769bde\",\"name\":\"库存管理\",\"color\":\"blue\"},{\"id\":\"a288e013-0966-4b28-aee5-3b067118595b\",\"name\":\"优选\",\"color\":\"pink\"},{\"id\":\"6aa362ce-51a5-43c7-b2db-bfe8ac7df707\",\"name\":\"Kakfa\",\"color\":\"green\"},{\"id\":\"6dddc130-6434-4720-96f8-0000bd415738\",\"name\":\"Kafka\",\"color\":\"purple\"},{\"id\":\"daf896ad-0f7b-465a-be32-04003a1a79f1\",\"name\":\"单元测试\",\"color\":\"default\"},{\"id\":\"a90fc723-a589-40bd-9321-08ad0484e1e5\",\"name\":\"PowerMock\",\"color\":\"green\"},{\"id\":\"ebd5b1e8-0339-4e5b-93c3-b53d6143a6c2\",\"name\":\"Markdown\",\"color\":\"pink\"},{\"id\":\"603303fc-e205-4d03-a1ba-f5e270badfc6\",\"name\":\"规范\",\"color\":\"brown\"},{\"id\":\"3fc5c8db-0233-4981-8d3d-f2bdbd5e856f\",\"name\":\"音乐\",\"color\":\"default\"},{\"id\":\"ca50135c-80eb-44f5-bc4c-14113bdc813e\",\"name\":\"BigDecimal\",\"color\":\"default\"}]}},\"相关文献笔记\":{\"id\":\"%5BCO%7B\",\"name\":\"相关文献笔记\",\"type\":\"relation\",\"relation\":{\"database_id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"type\":\"single_property\",\"single_property\":{}}},\"卡片类型\":{\"id\":\"%5Dy%7Bx\",\"name\":\"卡片类型\",\"type\":\"select\",\"select\":{\"options\":[{\"id\":\"GWZ_\",\"name\":\"灵感笔记\",\"color\":\"brown\"},{\"id\":\"P>GO\",\"name\":\"文献笔记\",\"color\":\"purple\"}]}},\"UID\":{\"id\":\"%5D%7C%3FG\",\"name\":\"UID\",\"type\":\"formula\",\"formula\":{\"expression\":\"timestamp(prop(\\\"创建时间\\\"))\"}},\"上一条\":{\"id\":\"iSL~\",\"name\":\"上一条\",\"type\":\"relation\",\"relation\":{\"database_id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"type\":\"dual_property\",\"dual_property\":{\"synced_property_name\":\"下一条\",\"synced_property_id\":\"%3Clwo\"}}},\"十分钟变化一次的随机数\":{\"id\":\"lZdE\",\"name\":\"十分钟变化一次的随机数\",\"type\":\"formula\",\"formula\":{\"expression\":\"mod(mod(timestamp(prop(\\\"创建时间\\\")) * 1.00011979e+8 + 5.00067713e+8, 9.00066731e+8) * mod(round(timestamp(now()) / 6e+5) * 6e+5 * 8.00067089e+8 + 8.00068411e+8, 8.00053967e+8) + 9.00067309e+8, 9.00066571e+8)\"}},\"入口卡片指向笔记\":{\"id\":\"nQr%3D\",\"name\":\"入口卡片指向笔记\",\"type\":\"relation\",\"relation\":{\"database_id\":\"e0e1dd7d-81f2-4ac1-9155-22c02666bda7\",\"type\":\"dual_property\",\"dual_property\":{\"synced_property_name\":\"入口卡片\",\"synced_property_id\":\"I_pk\"}}},\"创建时间\":{\"id\":\"q%3AIC\",\"name\":\"创建时间\",\"type\":\"created_time\",\"created_time\":{}},\"Name\":{\"id\":\"title\",\"name\":\"Name\",\"type\":\"title\",\"title\":{}}},\"parent\":{\"type\":\"page_id\",\"page_id\":\"1d38649c-ee36-4035-ac89-dec7496453c4\"},\"url\":\"https://www.notion.so/e0e1dd7d81f24ac1915522c02666bda7\",\"archived\":false}"

    }

    def "test getMapFromJson"() {
        when:
        Map<String, Object> result = NotionParseUtils.getMapFromJson(jsonText)
        then:
        Objects.nonNull(result.get("状态"))
    }

    def "test getProperty"() {
        when:
        JSONObject property = NotionParseUtils.getPropertyJSONObject(jsonText, "状态")
        then:
        Objects.nonNull(property)
    }

    def "test parse SelectModel"() {
        given:
        String name = "类型"
        when:
        SelectModel selectModel = NotionParseUtils.getSelectModelByPropertyName(jsonText, name)
        then:
        Objects.nonNull(selectModel) && name == selectModel.getName()
    }
}