package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/6/17 16:31
 */
@Getter
@AllArgsConstructor
public enum VehicleSourTagEnum implements BaseEnum {
    LU_RU("1", "手动录入"),
    LIU_HE_YI("2", "六合一录入");
    private String code;
    private String desc;
}
