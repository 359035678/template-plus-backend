package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/5/31 19:56
 */
@Getter
@AllArgsConstructor
public enum PassCheckStatusEnum implements BaseEnum {
    YOU_XIAO("1", "有效"),
    GUO_QI("2", "过期"),
    WEI_KAI_SHI("3", "未开始");

    private String code;

    private String desc;


}
