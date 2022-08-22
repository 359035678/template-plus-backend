package com.template.plus.common.framework.util.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.template.plus.common.config.constant.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lp
 * @date 2022/6/17 17:40
 */
public class ExcelDateTimeConverter implements Converter<Date> {

    @Override
    public Class<LocalDateTime> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) {
        try {
            return DateUtils.parseDate(cellData.getStringValue(), DatePattern.YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CellData<String> convertToExcelData(Date value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        return new CellData<>(DateFormatUtils.format(value, DatePattern.YYYY_MM_DD_HH_MM_SS));

    }

}
