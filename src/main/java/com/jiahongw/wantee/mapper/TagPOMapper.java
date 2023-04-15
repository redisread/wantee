package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.entity.TagPO;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface TagPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TagPO record);

    int insertSelective(TagPO record);

    TagPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TagPO record);

    int updateByPrimaryKey(TagPO record);

    List<TagPO> listTagByNames(@Param("creatorId") Long creatorId, @Param("nameSet") Set<String> nameSet );

    int batchInsert(@Param("tagPOList") List<TagPO> tagPOList );
}