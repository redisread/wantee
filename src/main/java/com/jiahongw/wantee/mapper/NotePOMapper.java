package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.entity.NotePO;

public interface NotePOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NotePO record);

    int insertSelective(NotePO record);

    NotePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotePO record);

    int updateByPrimaryKeyWithBLOBs(NotePO record);

    int updateByPrimaryKey(NotePO record);
}