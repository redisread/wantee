package com.notekeeper.infrastructure.dal.mapper;

import com.notekeeper.infrastructure.dal.model.CountryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Redisread
 * @date 2024/12/15
 */
@Mapper
public interface CountryMapper {

    @Select("SELECT * FROM country WHERE country_name = #{name}")
    CountryDO queryByName(@Param("name") String name);
}
