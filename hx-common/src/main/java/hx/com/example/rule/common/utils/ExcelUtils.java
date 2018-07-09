package hx.com.example.rule.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.entity.ExcelEntityDTO;
import hx.com.example.rule.common.entity.ExcelSheetDTO;
import hx.com.example.rule.common.enums.DateFormatEnum;
import hx.com.example.rule.common.enums.ExcelTitleEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * excel 的导入导出
 * @Author mingliang
 * @Date 2017-12-27 10:20
 */
public class ExcelUtils {

    private static String timeFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sf = new SimpleDateFormat(timeFormat);

    /**
     * 读取Excel表格
     * @param inputStream 输入流
     * @return 返回一个json格式数据
     * @throws IOException
     */
    public static String loadExcel(InputStream inputStream) throws IOException, ParseException {
        //根据指定的文件输入流导入Excel从而产生Workbook对象
//        Workbook workbook = new XSSFWorkbook(inputStream);
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        Workbook workbook = new HSSFWorkbook(fs);
        //获取Excel文档中的第一个表单
        Sheet sht0 = workbook.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        JSONObject result = new JSONObject();

        List<String> titles = new ArrayList<>();
        List<JSONObject> list = new ArrayList<>();
        for (Row row : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            int count = row.getLastCellNum();
            if (row.getRowNum() < 1) {
                for (int i = 0; i< count; i++){
                    titles.add(row.getCell(i).getStringCellValue());
                }
                continue;
            }
            JSONObject object = new JSONObject();
            for (int i = 0; i < count; i++) {
                String code = ExcelTitleEnum.getByMessage(titles.get(i)).getCode();
                Cell cell = row.getCell(i);
                if (cell==null || cell.toString().trim().equals("")) {
                    continue;
                }
                object.put(code, getCellValue(row.getCell(i),code));
            }
            list.add(object);
        }
        result.put("data", list);
        System.out.println(JSONArray.toJSONString(list));
        inputStream.close();
        return result.toJSONString();
    }

    /**
     * 写入Excel
     * @param excelEntityDTO
     * @return XSSFWorkbook
     */
    public static XSSFWorkbook writeExcel(ExcelEntityDTO excelEntityDTO){

        List<ExcelSheetDTO> excelSheetDTOS = excelEntityDTO.getExcelSheetDTOS();

        // 创建一个Excel
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFCellStyle cellStyle = setStyle(workbook);
        XSSFCellStyle rowStyle = setRowStyle(workbook);
        // 创建sheet
        for (ExcelSheetDTO excelSheet : excelSheetDTOS){
            XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
            XSSFRow row = sheet.createRow(0);
            row.setHeight((short) 500);
            // 表头
            List<String> title = excelSheet.getTitleList();
            List<Integer> width = excelSheet.getWidth();
            for (int i = 0; i < title.size(); i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(ExcelTitleEnum.getMsgByCode(title.get(i)));
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(i, width.get(i));
            }
            // 设置值
            List<Map<String,Object>> valueList = excelSheet.getValueList();
            setValue(valueList,sheet,rowStyle);
        }
        return workbook;
    }

    /**
     *
     * @param workbook
     * @return
     */
    private static XSSFCellStyle setStyle(XSSFWorkbook workbook){
        // 设置cell样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     *
     * @param workbook
     * @return
     */
    private static XSSFCellStyle setRowStyle(XSSFWorkbook workbook){
        // 每行样式
        XSSFCellStyle rowStyle = workbook.createCellStyle();
        return rowStyle;
    }

    private static void setValue(List<Map<String,Object>> valueList,XSSFSheet sheet,
                                 XSSFCellStyle rowStyle){
        int i = 0;
        for (Map<String,Object> valueMap : valueList){
            XSSFRow row = sheet.createRow(i+1);
            row.setRowStyle(rowStyle);
            int j = 0;
            for (Map.Entry entry : valueMap.entrySet()){
                String type = ExcelTitleEnum.getByCode((String) entry.getKey()).getType();
                switch (type){
                    case "int":
                        row.createCell(j).setCellValue((int) entry.getValue());
                        break;
                    case "double":
                        row.createCell(j).setCellValue((double) entry.getValue());//
                        break;
                    case "String":
                        row.createCell(j).setCellValue((String) entry.getValue());
                        break;
                    case "Date":
                        try {
                            row.createCell(j).setCellValue(sf.format((Date) entry.getValue()));
                        }catch (Exception e){

                        }
                        break;
                    case "Boolean":
                        row.createCell(j).setCellValue((boolean) entry.getValue());
                        break;
                    default:
                }
                j++;
            }
            i++;
        }
    }

    private static Object getCellValue(Cell cell,String code) throws NumberFormatException {
        String type = ExcelTitleEnum.getByCode(code).getType();
        try{
            switch (type){
                case "int":
                    return Integer.valueOf(String.valueOf(getValue(cell)));
                case "double":
                    return Double.parseDouble(String.valueOf(getValue(cell)));
                case "String":
                    return String.valueOf(getValue(cell)).replace(".0","");
                case "Date":
                    return sf.format(getValue(cell));
                case "Boolean":
                    return Boolean.valueOf(String.valueOf(getValue(cell)))?"Y":"N";
                default:
                    return "";
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("数字格式错误！");
        }
    }


    /**
     * Excel 类型判断
     * @param cell
     * @return
     */
    private static Object getValue(Cell cell){
        if (null != cell) {
            switch (cell.getCellType()) {
                // 数字
                case XSSFCell.CELL_TYPE_NUMERIC:
                    //判断单元格的类型是否则NUMERIC类型
                    if (0 == cell.getCellType()) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            return cell.getDateCellValue();
                        } else {
                            return cell.getNumericCellValue();
                        }
                    }
                    break;
                case XSSFCell.CELL_TYPE_STRING: // 字符串
                    // 判断是否为时间字符串
                    String value = cell.getStringCellValue();
                    if (strIsDate(value)){
                        try {
                            String dateStr = DateFormatEnum.getTimeByDateStr(value);
                            if (StringUtils.isNotBlank(dateStr)){
                                return sf.parse(dateStr);
                            }else {
                                return null;
                            }
                        } catch (ParseException e) {
                            return null;
                        }
                    }else {
                        return value;
                    }
                case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    return cell.getBooleanCellValue();
                case XSSFCell.CELL_TYPE_FORMULA: // 公式
                    return cell.getCellFormula() + "";
                case XSSFCell.CELL_TYPE_BLANK: // 空值
                    return "";
                case XSSFCell.CELL_TYPE_ERROR: // 故障
                    new NumberFormatException("非法类型！");
                default:
                    return "";
            }
        }
        return null;
    }

    private static boolean strIsDate(String str){
        Pattern pattern = Pattern.compile(DateFormatEnum.getRegString());
        Matcher m = pattern.matcher(str);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        try {
//            FileInputStream fileIn = new FileInputStream("C:\\Users\\Administrator\\Desktop\\债权转让表字段内容.xlsx");
//            loadExcel(fileIn);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        // TODO Auto-generated method stub
        String content = readWord("C:\\Users\\andy\\Desktop\\智能外呼平台工单任务模块接口文档v2.1.8(1)新.docx");
        System.out.println("content===="+content);
    }

    /**
     * 读取word文件内容
     *
     * @param path
     * @return buffer
     */

    public static String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(path);
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

}
