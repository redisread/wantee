package com.jiahongw.wantee.model.bilibli;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户关系状态数 模型
 *
 * @author VictorHong
 * @date 2023/1/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserStatModel {

    /**
     * 用户id
     */
    private String mid;

    /**
     * 关注数量
     */
    private Integer following;

    /**
     * 悄悄关注数
     */
    private Integer whisper;

    /**
     * 黑名单数
     */
    private Integer black;

    /**
     * 粉丝数
     */
    private Integer follower;
}
