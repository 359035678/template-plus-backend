package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lhj
 * @date 2022/6/06 13:00
 */
@Getter
@AllArgsConstructor
public enum TrendEnum implements BaseEnum {
    SHANG_SHENG("1", "上升"),
    XIA_JIANG("2", "下降"),
    CHI_PING("3", "持平");

    private String code;

    private String desc;


}
