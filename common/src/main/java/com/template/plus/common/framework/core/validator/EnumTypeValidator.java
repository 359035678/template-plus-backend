package com.template.plus.common.framework.core.validator;


import com.template.plus.common.framework.core.validator.constraints.EnumType;
import com.template.plus.common.framework.util.BaseEnumUtil;
import com.template.plus.common.framework.common.enums.BaseEnum;
import com.template.plus.common.framework.common.exception.BusinessException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义系统内的枚举验证注解实现类
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class EnumTypeValidator implements ConstraintValidator<EnumType, String> {

    private Class<? extends BaseEnum> baseEnum;

    @Override
    public void initialize(EnumType parameters) {
        baseEnum = parameters.type();
        if (baseEnum == null) {
            throw new BusinessException("请传入枚举类型类");
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return BaseEnumUtil.exists(baseEnum, value);
    }
}
