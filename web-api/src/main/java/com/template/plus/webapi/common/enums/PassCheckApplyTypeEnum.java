package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/6/8 13:56
 */
@Getter
@AllArgsConstructor
public enum PassCheckApplyTypeEnum implements BaseEnum {

    XIN_ZENG_SHEN_QING("1", "新增申请"),

    YAN_QI_SHEN_QING("2", "延期申请");

    private String code;

    private String desc;
}
