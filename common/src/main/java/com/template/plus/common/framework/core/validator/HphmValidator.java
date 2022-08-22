package com.template.plus.common.framework.core.validator;


import com.template.plus.common.framework.core.validator.constraints.Hphm;
import com.template.plus.common.framework.util.HphmUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义手机号码验证注解实现类
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class HphmValidator implements ConstraintValidator<Hphm, String> {
    @Override
    public void initialize(Hphm parameters) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return HphmUtil.validator(value);
    }
}
