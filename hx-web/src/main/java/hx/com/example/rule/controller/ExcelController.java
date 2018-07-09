package hx.com.example.rule.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.DebtResourcesInfoDTO;
import hx.com.example.rule.common.entity.ExcelEntityDTO;
import hx.com.example.rule.common.entity.ExcelSheetDTO;
import hx.com.example.rule.common.enums.ExcelTitleEnum;
import hx.com.example.rule.common.utils.BeanMapUtil;
import hx.com.example.rule.common.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-01-03 15:43
 */
@RequestMapping("/excel")
@RestController
public class ExcelController {

    @RequestMapping("/load")
    public String loadExcel(ModelMap modelMap, @RequestParam("excelFile") MultipartFile file,
            HttpServletResponse request, HttpServletResponse response){

        return "成功！";
    }

    @RequestMapping("/upload")
    public void uploadExcel(ModelMap modelMap,HttpServletResponse request,
                                           HttpServletResponse response){
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\andy\\Desktop\\桌面\\住房公积金个人提取申请表 (1).xls");
            String result = ExcelUtils.loadExcel(fileIn);
            ExcelEntityDTO excelEntityDTO = new ExcelEntityDTO();
            excelEntityDTO.setFileName("CQXYD-TYC0-20180507.xlsx");
            List<ExcelSheetDTO> excelSheetDTOS = new ArrayList<>();
            JSONObject object = JSONObject.parseObject(result);
//            JSONObject valueObject = JSONObject.parseObject();
            List<DebtResourcesInfoDTO> list = JSONArray.parseArray(JSONObject.toJSONString(object.get("data")),DebtResourcesInfoDTO.class);
            List<String> titleList = new ArrayList<>();
            List<ExcelTitleEnum> titleEnums = ExcelTitleEnum.getAll();
            for (ExcelTitleEnum excelTitleEnum : titleEnums){
                titleList.add(excelTitleEnum.getMessage());
//                valueMap.put(excelTitleEnum.getCode(),excelTitleEnum.getCode().indexOf("Time") != -1?
//                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueObject.get(excelTitleEnum.getCode()).toString()):
//                        valueObject.get(excelTitleEnum.getCode()));
            }
            List<Map<String, Object>> valueList = new ArrayList<>();
            for (DebtResourcesInfoDTO infoDTO : list){
                Map<String, Object> valueMap = new LinkedHashMap<>();
                valueMap.putAll(BeanMapUtil.beanToMap(infoDTO));
                valueList.add(valueMap);
            }
            ExcelSheetDTO excelSheetDTO = new ExcelSheetDTO(titleList,valueList);
            excelSheetDTO.setSheetName("mySheet");
            excelSheetDTOS.add(excelSheetDTO);
            excelEntityDTO.setExcelSheetDTOS(excelSheetDTOS);
            XSSFWorkbook workbook = ExcelUtils.writeExcel(excelEntityDTO);


            // 请见：http://zmx.javaeye.com/blog/622529
//            filename = Util.encodeFilename(filename, request);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + excelEntityDTO.getFileName());
            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        return response;
    }

    @RequestMapping("/index")
    public ModelAndView index(ModelMap modelMap,ModelAndView modelAndView){
        modelAndView.setViewName("/index/index");
        return modelAndView;
    }

    public static void main(String[] args) {
//        String str = "{\"payPrincipal\":23.32,\"paidInterest\":36.36,\"sigLoanTime\":\"2017-12-25 00:00:00\",\"lendingTime\":\"2017-12-25 00:00:00\",\"transferAmount\":1695.36,\"paidFine\":23.65,\"userName\":\"涨薪\",\"transferContractSignTime\":\"2017-12-25 00:00:00\",\"unpaidPrincipal\":1652.63,\"telNo\":\"16958962365\",\"certNo\":\"500226199512261000\",\"unpaidInterest\":36.45,\"transferContractEffectTime\":\"2017-12-25 00:00:00\",\"loanNumber\":\"2017CQS556398710201734472A\",\"loanCapital\":1563.23}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        System.out.println(jsonObject.toJSONString());
    }
}
