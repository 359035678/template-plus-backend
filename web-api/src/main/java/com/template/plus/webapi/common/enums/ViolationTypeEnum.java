package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/5/26 9:59
 */
@Getter
@AllArgsConstructor
public enum ViolationTypeEnum implements BaseEnum {
    YEJIANBANLI("1", "夜间办理"),
    CAILIAOBUZU("2", "材料不足"),
    CHONGFUBUZHENG("3", "重复补证");
    private String code;
    private String desc;

}
