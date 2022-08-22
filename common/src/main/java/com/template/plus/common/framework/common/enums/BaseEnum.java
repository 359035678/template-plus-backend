package com.template.plus.common.framework.common.enums;


/**
 * 枚举类型父接口
 *
 * @author geekidea
 * @date 2018-11-08
 */
public interface BaseEnum {

    /**
     * 获取枚举标识
     *
     * @return
     */
    String getCode();

    /**
     * 获取枚举描述
     *
     * @return
     */
    String getDesc();

    /**
     * 通过枚举类型和code值获取对应的枚举类型
     * @param enumType
     * @param code
     * @param <T>
     * @return
     */
    static <T extends BaseEnum> T valuesOf(Class<? extends BaseEnum> enumType, String code) {
        if (enumType == null || code == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            String enumCode = enumConstant.getCode();
            if (code.equals(enumCode)) {
                return enumConstant;
            }
        }
        return null;
    }

}
