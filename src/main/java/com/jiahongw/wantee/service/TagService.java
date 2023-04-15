package com.jiahongw.wantee.service;

import com.jiahongw.wantee.entity.TagPO;
import com.jiahongw.wantee.mapper.TagPOMapper;
import com.jiahongw.wantee.model.note.TagModel;
import com.jiahongw.wantee.service.convert.TagServiceConvert;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
public class TagService {

    @Resource
    private TagPOMapper tagPOMapper;

    public List<TagModel> queryTagListByName(Long creatorId, Set<String> nameSet) {
        if (CollectionUtils.isEmpty(nameSet)) {
            return Collections.emptyList();
        }
        List<TagPO> tagPOList = tagPOMapper.listTagByNames(creatorId, nameSet);
        List<TagModel> tagModelList = tagPOList.stream()
            .map(TagServiceConvert::convert2TagModel)
            .collect(Collectors.toList());
        return tagModelList;
    }

    public List<TagModel> createTag(Long creatorId, Set<String> nameSet) {
        List<TagPO> tagPOList = nameSet.stream()
            .map(name -> TagServiceConvert.convertTagPO(creatorId, name))
            .collect(Collectors.toList());
        tagPOMapper.batchInsert(tagPOList);
        List<TagModel> tagModelList = tagPOList.stream()
            .map(TagServiceConvert::convert2TagModel)
            .collect(Collectors.toList());
        return tagModelList;
    }
}
