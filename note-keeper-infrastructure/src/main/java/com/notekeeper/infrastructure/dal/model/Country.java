package com.notekeeper.infrastructure.dal.model;

import lombok.Data;

/**
 * @author VictorHong
 * @date 2023/2/25
 */
@Data
public class Country {

    private Integer id;
    private String countryName;
    private String countryCode;
}
