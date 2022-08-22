package com.template.plus.common.framework.core.pagination;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.template.plus.common.config.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果对象
 *
 * @author geekidea
 * @date 2018-11-08
 */

@Slf4j
@Data
@Schema(name = "分页结果对象")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = 4784961132604516495L;

    @Schema(description = "总行数")
    @JSONField(name = CommonConstant.PAGE_TOTAL_NAME)
    @JsonProperty(CommonConstant.PAGE_TOTAL_NAME)
    private long total = 0;

    @Schema(description = "数据列表")
    @JSONField(name = CommonConstant.PAGE_LIST_NAME)
    @JsonProperty(CommonConstant.PAGE_LIST_NAME)
    private List<T> list = Collections.emptyList();

    @Schema(description = "页码")
    @JSONField(name = CommonConstant.PAGE_NUM_NAME)
    @JsonProperty(CommonConstant.PAGE_NUM_NAME)
    private Long pageNum;

    @Schema(description = "页大小")
    @JSONField(name = CommonConstant.PAGE_SIZE_NAME)
    @JsonProperty(CommonConstant.PAGE_SIZE_NAME)
    private Long pageSize;

    public Paging() {

    }

    public Paging(IPage<T> page) {
        this.total = page.getTotal();
        this.list = page.getRecords();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }

}
