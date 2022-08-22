package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/6/17 16:28
 */

@Getter
@AllArgsConstructor
public enum ImportantVehicleSourceTagEnum implements BaseEnum {
    XI_TONG_LU_RU("1", "系统录入"),
    WEI_FA_GAO_FA("2", "违法高发车辆");
    private String code;
    private String desc;
}
