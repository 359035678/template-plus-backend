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
public enum SourceTagEnum implements BaseEnum {
    BEN_DI_WAI_GUA("1","窗口办理"),
    HU_LIAN_WANG("2","互联网服务平台（12123）");

    private String code;
    private String desc;

}
