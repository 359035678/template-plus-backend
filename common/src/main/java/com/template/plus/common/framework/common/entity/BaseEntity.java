package com.template.plus.common.framework.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 实体父类
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Schema(name = "BaseEntity")
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -7176390653391227433L;

}
