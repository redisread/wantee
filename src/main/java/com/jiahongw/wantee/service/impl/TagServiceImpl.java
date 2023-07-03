package com.jiahongw.wantee.service.impl;

import static com.jiahongw.wantee.entity.table.Tables.TAG;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.jiahongw.wantee.dto.tag.TagDTO;
import com.jiahongw.wantee.entity.Tag;
import com.jiahongw.wantee.mapper.TagMapperExt;
import com.jiahongw.wantee.service.TagService;
import com.jiahongw.wantee.util.SaTokenUtils;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * @author VictorHong
 * @date 2023/4/15
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapperExt tagMapper;

    @Override
    public void create(Integer userId, List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            return;
        }
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(TAG)
            .where(TAG.USER_ID.eq(userId))
            .and(TAG.NAME.in(tagNameList));
        List<Tag> tagList = tagMapper.selectListByQuery(query);

        Set<String> tagSet = tagList.stream().map(Tag::getName).collect(Collectors.toSet());

        List<Tag> tags = tagNameList.stream()
            .filter(t -> !tagSet.contains(t))
            .map(t -> {
                Tag tag = new Tag();
                tag.setName(t);
                tag.setUserId(userId);
                tag.setCreateTime(LocalDateTime.now());
                tag.setUpdateTime(tag.getCreateTime());
                return tag;
            })
            .collect(Collectors.toList());
        tagMapper.insertBatch(tags);
    }

    @Override
    public void delete(Integer userId, List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            return;
        }
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(TAG)
            .where(TAG.NAME.in(tagNameList))
            .and(TAG.USER_ID.eq(userId));
        List<Tag> tagList = tagMapper.selectListByQuery(query);
        if (!CollectionUtils.isEmpty(tagList)) {
            tagMapper.deleteBatchByIds(
                tagList.stream().map(Tag::getId).collect(Collectors.toList()));
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Map<Integer, String> tagId2NameMap) {
        List<Integer> tagIdList = Lists.newArrayList(tagId2NameMap.keySet());
        List<Tag> tagList = tagMapper.selectListByIds(tagIdList);
        tagList.forEach(t -> {
            t.setName(tagId2NameMap.get(t.getId()));
            t.setUpdateTime(LocalDateTime.now());
            tagMapper.update(t);
        });
    }

    @Override
    public List<TagDTO> listByUser(Integer userId) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(TAG)
            .where(TAG.USER_ID.eq(userId));
        List<Tag> tagList = tagMapper.selectListByQuery(query);

        return tagList.stream()
            .map(tag -> {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setId(tag.getId());
                tagDTO.setName(tag.getName());
                return tagDTO;
            }).collect(Collectors.toList());
    }

    @Override
    public List<TagDTO> listByNames(Integer userId, List<String> nameList) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(TAG)
            .where(TAG.USER_ID.eq(userId))
            .and(TAG.NAME.in(nameList));
        List<Tag> tagList = tagMapper.selectListByQuery(query);
        return tagList.stream()
            .map(tag -> {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setId(tag.getId());
                tagDTO.setName(tag.getName());
                return tagDTO;
            }).collect(Collectors.toList());
    }

    @Override
    public TagDTO getByName(Integer userId, String name) {
        QueryWrapper query = QueryWrapper.create()
            .select()
            .from(TAG)
            .where(TAG.USER_ID.eq(userId))
            .and(TAG.NAME.eq(name));
        Tag tag = tagMapper.selectOneByQuery(query);
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }
}
