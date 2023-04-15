package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.entity.ResourcePO;

public interface ResourcePOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourcePO record);

    int insertSelective(ResourcePO record);

    ResourcePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourcePO record);

    int updateByPrimaryKey(ResourcePO record);
}