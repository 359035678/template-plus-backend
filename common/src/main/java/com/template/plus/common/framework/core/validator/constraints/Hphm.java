package com.template.plus.common.framework.core.validator.constraints;


import com.template.plus.common.framework.core.validator.HphmValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义车牌号正则验证注解
 * @author geekidea
 * @date 2018-11-08
 */
@Documented
@Constraint(validatedBy = { HphmValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Hphm {
	String message() default "请输入有效的号牌号码";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
