package com.template.plus.common.framework.util.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.template.plus.common.framework.common.exception.ExcelException;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelUtil {
    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz) {
        ExcelListener<Object> excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);

        if (reader == null) {
            return null;
        }

        for (Sheet sheet : reader.getSheets()) {
            if (clazz != null) {
                sheet.setClazz(clazz);
            }
            reader.read(sheet);
        }

        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz, int sheetNo) {
        return readExcel(excel, clazz, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz, int sheetNo, int headLineNum) {
        ExcelListener<T> excelListener = new ExcelListener<T>();
        ExcelReader reader = getReader(excel, excelListener);

        if (reader == null) {
            return null;
        }

        reader.read(new Sheet(sheetNo, headLineNum, clazz));

        return excelListener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object    映射实体类，Excel 模型
     */
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> list, String fileName,
                                  String sheetName, Class<? extends BaseRowModel> clazz) {
        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setSheetName(sheetName);

        TableStyle tableStyle = new TableStyle();
        // tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
        Font font = new Font();
        font.setFontHeightInPoints((short) 11);
        tableStyle.setTableHeadFont(font);
        tableStyle.setTableContentFont(font);
        sheet.setTableStyle(tableStyle);

        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object    映射实体类，Excel 模型
     */
    public static ExcelWriterFactory writeExcelWithSheets(HttpServletResponse response,
                                                          List<? extends BaseRowModel> list, String fileName,
                                                          String sheetName, Class<? extends BaseRowModel> clazz) {
        ExcelWriterFactory writer = new ExcelWriterFactory(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setSheetName(sheetName);
        sheet.setTableStyle(getTableStyle());
        writer.write(list, sheet);

        return writer;
    }


    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        //创建本地文件
        fileName = fileName + ExcelTypeEnum.XLSX.getValue();

        try {
            fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
            response.addHeader("Content-Disposition", "filename=" + fileName);

            return response.getOutputStream();
        } catch (Exception e) {
            throw new ExcelException("导出异常！");
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();

        if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;

        try {
            inputStream = new BufferedInputStream(excel.getInputStream());

            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 读取Excel表格数据，封装成实体
     *
     * @param inputStream
     * @param clazz
     * @param sheetNo
     * @param headLineMun
     * @return
     */
    public static Object readExcel(InputStream inputStream, Class<? extends BaseRowModel> clazz, Integer sheetNo,
                                   Integer headLineMun) {
        if (null == inputStream) {

            throw new NullPointerException("the inputStream is null!");
        }

        ExcelListener listener = new ExcelListener();
        ExcelReader reader = new ExcelReader(inputStream, valueOf(inputStream), null, listener);
        reader.read(new Sheet(sheetNo, headLineMun, clazz));

        return listener.getDatas();
    }

    /**
     * 根据输入流，判断为xls还是xlsx，该方法原本存在于easyexcel 1.1.0 的ExcelTypeEnum中。
     */
    public static ExcelTypeEnum valueOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);

            if (FileMagic.OLE2.equals(fileMagic)) {
                return ExcelTypeEnum.XLS;
            }

            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }

            throw new ExcelException("excelTypeEnum can not null");

        } catch (IOException | ExcelException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置全局样式
     *
     * @return
     */
    private static TableStyle getTableStyle() {
        TableStyle tableStyle = new TableStyle();

        tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
        Font font = new Font();
        font.setBold(true);
        font.setFontHeightInPoints((short) 9);
        tableStyle.setTableHeadFont(font);
        Font fontContent = new Font();
        fontContent.setFontHeightInPoints((short) 9);
        tableStyle.setTableContentFont(fontContent);

        return tableStyle;
    }
}
