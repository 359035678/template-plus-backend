package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/5/31 16:36
 */
@Getter
@AllArgsConstructor
public enum SpatialTypeEnum implements BaseEnum {

    area("1","区域"),

    line("2","路线");

    private String code;
    private String desc;

}
