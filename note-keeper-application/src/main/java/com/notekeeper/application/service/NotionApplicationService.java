package com.notekeeper.application.service;

import com.notekeeper.application.gateway.NotionGateway;
import jakarta.annotation.Resource;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Redisread
 * @date 2024/12/8
 */
@Slf4j
@Service
public class NotionApplicationService {
    private static String CARD_BOX_PAGE_CREATE_INFO = "{\"parent\":{\"database_id\":\"%s\"},\"properties\":{\"Name\":{\"title\":[{\"text\":{\"content\":\"%s\"}}]},\"类型\":{\"select\":{\"name\":\"%s\"}},\"卡片类型\":{\"select\":{\"name\":\"文献笔记\"}},\"URL\":{\"url\":\"%s\"}}}";


    @Resource
    private NotionGateway notionGateway;


//    /**
//     * 卡片盒笔记数据库id
//     */
//    @Value("${notion.infos.card-box-database-id}")
//    private String notionCardBoxDatabaseId;



}
