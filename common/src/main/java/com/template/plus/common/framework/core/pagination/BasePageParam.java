package com.template.plus.common.framework.core.pagination;

import com.template.plus.common.config.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 *
 * @author geekidea
 * @since 2018-11-08
 */
@Data
@Schema(name = "查询参数对象")
public abstract class BasePageParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @Schema(description = "页码,默认为1", example = "1")
    private Long pageNum = CommonConstant.DEFAULT_PAGE_NUM;

    @Schema(description = "页大小,默认为10", example = "10")
    private Long pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

    @Schema(description = "搜索字符串", example = "")
    private String keyword;

    @Schema(description = "count计数")
    private Boolean isSearchCount = true;

    public void setPageNum(Long pageNum) {
        if (pageNum == null || pageNum <= 0) {
            this.pageNum = CommonConstant.DEFAULT_PAGE_NUM;
        } else {
            this.pageNum = pageNum;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize < 0) {
            this.pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    public void noPage() {
        this.pageSize = 99999999L;
        this.isSearchCount = false;
    }

}
