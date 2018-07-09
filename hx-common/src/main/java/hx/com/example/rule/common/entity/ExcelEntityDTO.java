package hx.com.example.rule.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-01-03 14:01
 */
public class ExcelEntityDTO implements Serializable{

    private String fileName;
    private List<ExcelSheetDTO> excelSheetDTOS;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<ExcelSheetDTO> getExcelSheetDTOS() {
        return excelSheetDTOS;
    }

    public void setExcelSheetDTOS(List<ExcelSheetDTO> excelSheetDTOS) {
        this.excelSheetDTOS = excelSheetDTOS;
    }
}
