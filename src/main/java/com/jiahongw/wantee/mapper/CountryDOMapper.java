package com.jiahongw.wantee.mapper;

import com.jiahongw.wantee.entity.CountryDO;

public interface CountryDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    int insert(CountryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    int insertSelective(CountryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    CountryDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    int updateByPrimaryKeySelective(CountryDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbg.generated Sat Feb 25 21:10:20 CST 2023
     */
    int updateByPrimaryKey(CountryDO record);
}