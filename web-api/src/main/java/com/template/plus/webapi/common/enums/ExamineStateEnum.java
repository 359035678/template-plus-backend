package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/6/2 15:28
 */
@Getter
@AllArgsConstructor
public enum ExamineStateEnum implements BaseEnum {

    DAI_SHEN_HE("1", "待审核"),

    TONG_GUO("2", "审核通过"),

    BU_TONG_GUO("3", "不通过"),

    ZUO_FEI("4", "作废");

    private String code;
    private String desc;

}
