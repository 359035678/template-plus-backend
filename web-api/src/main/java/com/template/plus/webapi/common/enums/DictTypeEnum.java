package com.template.plus.webapi.common.enums;

import com.template.plus.common.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lp
 * @date 2022/5/26 9:59
 */
@Getter
@AllArgsConstructor
public enum DictTypeEnum implements BaseEnum {
    date_type("date_type", "日期类型"),
    restriction_spatial_type("restriction_spatial_type", "禁行区域类型"),
    hpzl("hpzl", "号牌种类"),
    apply_person_type("apply_person_type", "申请人类型"),
    apply_paper_type("apply_paper_type", "申请人证件类型"),
    pass_check_classification("pass_check_classification", "通行证分类"),
    pass_check_spatial_type("pass_check_spatial_type", "通行证通行区域类型"),
    pass_check_apply_type("pass_check_apply_type", "通行证申请类型"),
    pass_check_file_type("pass_check_file_type", "通行证申请材料类型"),
    pass_check_file_sub_type_company("pass_check_file_sub_type_company", "通行证申请材料企业子类型"),
    pass_check_file_sub_type_vehicle("pass_check_file_sub_type_vehicle", "通行证申请材料车辆子类型"),
    pass_check_file_sub_type_person("pass_check_file_sub_type_person", "通行证申请材料个人子类型"),
    pass_check_source_tag_type("pass_check_source_tag_type", "通行证数据来源"),
    examine_state("examine_state", "审核状态"),
    restriction_rule_type("restriction_rule_type", "禁行类型"),
    syxz("syxz", "使用性质"),

    cllx("cllx", "车辆类型"),
    clzt("clzt", "车辆状态"),

    pass_check_status("pass_check_status", "通行证状态");
    private String code;
    private String desc;

}
