package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.entity.Country;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author victor
 */
@Repository
@Mapper
public interface CountryMapper {

    @Select("SELECT country_name,country_code FROM country")
    @Results({
        @Result(property = "countryName", column = "country_name"),
        @Result(property = "countryCode", column = "country_code")
    })
    List<Country> getAll();
}
