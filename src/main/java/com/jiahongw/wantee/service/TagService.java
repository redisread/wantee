package com.jiahongw.wantee.service;

import com.jiahongw.wantee.dto.tag.TagDTO;
import java.util.List;
import java.util.Map;

/**
 * @author Redisread
 * @date 2023/5/14
 */
public interface TagService {

    void create(Integer userId,List<String> tagNameList);

    void delete(Integer userId,List<String> tagNameList);

    void update(Map<Integer,String> tagId2NameMap);

    List<TagDTO> listByUser(Integer userId);

    List<TagDTO> listByNames(Integer userId, List<String> nameList);

    TagDTO getByName(Integer userId, String name);
}
