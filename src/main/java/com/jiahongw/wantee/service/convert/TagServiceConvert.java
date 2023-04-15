package com.jiahongw.wantee.service.convert;

import com.jiahongw.wantee.entity.TagPO;
import com.jiahongw.wantee.model.note.TagModel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @author VictorHong
 * @date 2023/4/15
 */
public class TagServiceConvert {

    public static TagModel convert2TagModel(TagPO tagPO) {
        if (Objects.isNull(tagPO)) {
            return null;
        }
        TagModel tagModel = new TagModel();
        tagModel.setId(tagPO.getId());
        tagModel.setName(tagPO.getName());
        tagModel.setCreatorId(tagPO.getCreatorId());
        Instant instant = tagPO.getCreateTime().toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        tagModel.setCreateTime(localDateTime);
        return tagModel;
    }

    public static TagPO convertTagPO(Long creatorId, String name) {
        TagPO tagPO = new TagPO();
        tagPO.setCreateTime(new Date());
        tagPO.setCreatorId(creatorId);
        tagPO.setName(name);
        return tagPO;
    }
}
