package hx.com.example.rule.common.entity;

import hx.com.example.rule.common.enums.ExcelTitleEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 每一个sheet值存储对象
 * @Author mingliang
 * @Date 2018-01-03 11:33
 */
public class ExcelSheetDTO implements Serializable{

    /** sheet名称 */
    private String sheetName;
    /** 列的标题名称,按照显示的顺序排序传入 */
    private List<String> titleList;
    /** 实际的值 */
    private List<Map<String,Object>> valueList;
    /** 列宽 */
    private List<Integer> width;

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ExcelSheetDTO(List<String> titleList, List<Map<String, Object>> valueList) {
        this.titleList = new ArrayList<>();
        this.valueList = new ArrayList<>();
        width = new ArrayList<>();
        setTitleList(titleList);
        setValueList(valueList);
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    /**
     * 可以过滤掉错误的表头
     * @param titleList
     */
    private void setTitleList(List<String> titleList) {
        for (String str : titleList){
            this.titleList.add(ExcelTitleEnum.getCodeByMessage(str));
        }
    }

    public List<Map<String, Object>> getValueList() {
        return valueList;
    }

    private void setValueList(List<Map<String, Object>> valueList){
        // 按照title循序进行排序
        for (Map<String, Object> valueMap : valueList){
            // 进行排序
            Map<String, Object> cellValueMap = new LinkedHashMap<>();
            for (String key : titleList){
                cellValueMap.put(key,valueMap.get(key));
            }
            this.valueList.add(cellValueMap);
        }
        setWidth();
    }

    public List<Integer> getWidth() {
        return width;
    }

    private void setWidth(){
        if (valueList.size() > 0){
            Map<String,Object> valueMap = valueList.get(0);
            for (Map.Entry entry : valueMap.entrySet()){
                String message = ExcelTitleEnum.getMsgByCode((String) entry.getKey());
                int lenth = 10;
                if (StringUtils.isNotBlank(message)){
                    lenth = message.length() *2;
                }
                int cellWidth = (entry.getValue().toString().length()*10*32+lenth*10*60) / 2;
                System.out.println(cellWidth);
                width.add(cellWidth);
            }
        }
    }
}
