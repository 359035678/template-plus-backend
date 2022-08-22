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
public enum SysConfigTypeEnum implements BaseEnum {
    AREA_CODE("area_code", "行政区划父级编码"),
    FXC_CAR_TYPE("fxc_car_type","非现场执法统计车型"),
    LINE_FUFFER_PASS_CHECK("line_buffer_pass_check","根据路线查询视频缓冲区 单位 米"),
    LINE_BUFFER_RESTRICTION("line_buffer_restriction","根据路线查询视频缓冲区 单位 米"),
    NIGHT("night","夜间定义"),
    REGISTRATION_EFFICIENCY("registration_efficiency","通行证办证效率统计类型");

    private String code;

    private String desc;


}
