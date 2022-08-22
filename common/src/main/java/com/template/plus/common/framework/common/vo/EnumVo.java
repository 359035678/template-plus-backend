package com.template.plus.common.framework.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 枚举类型VO
 *
 * @author geekidea
 * @date 2019-11-02
 **/
@Data
@Accessors(chain = true)
public class EnumVo<T> {

    /**
     * 枚举code
     */
    private String code;

    /**
     * 枚举描述
     */
    private String desc;

    /**
     * 枚举类型
     */
    private T baseEnum;

}
