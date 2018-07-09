package hx.com.example.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hx.http.client.service.HttpClientService;
import org.apache.commons.httpclient.HttpClient;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.springframework.http.HttpRequest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-04-19 14:40
 */
public class BaiduNetDisk {

    public static List<Map<String, Object>> getUrl(String url) throws Exception {
        HttpClientService httpClientService = new HttpClientService();
        List<String> fs_id = new ArrayList<String>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Document doc = Jsoup.connect(url).get();
        String html = doc.toString();
        int a = html.indexOf("{\"typicalPath");
        int b = html.indexOf("yunData.getCon");
        int sign_head = html.indexOf("yunData.SIGN = \"");
        int sign_foot = html.indexOf("yunData.TIMESTAMP");
        int time_head = html.indexOf("yunData.TIMESTAMP = \"");
        int time_foot = html.indexOf("yunData.SHARE_UK");
        int share_id_head = html.indexOf("yunData.SHARE_ID = \"");
        int share_id_foot = html.indexOf("yunData.SIGN ");
        String sign = html.substring(sign_head, sign_foot);
        sign = sign.substring(sign.indexOf("\"") + 1, sign.indexOf("\";"));
        String time = html.substring(time_head, time_foot);
        time = time.substring(time.indexOf("\"") + 1, time.indexOf("\";"));
        String share_id = html.substring(share_id_head, share_id_foot);
        share_id = share_id.substring(share_id.indexOf("\"") + 1,
                share_id.indexOf("\";"));
        System.out.println(share_id);
        html = html.substring(a, b);
        a = html.indexOf("{\"typicalPath");
        b = html.indexOf("};");
        JSONObject jsonObject = JSONObject.parseObject(html.substring(a, b + 1));
        String uk = jsonObject.getString("uk");
        String shareid = jsonObject.getString("shareid");
        String path = URLEncoder.encode(jsonObject.getString("typicalPath"),
                "utf-8");
        jsonObject = JSONObject.parseObject(jsonObject.getString("file_list"));
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("list"));
        jsonObject = jsonArray.getJSONObject(0);
        String app_id = jsonObject.getString("app_id");
        if (jsonObject.getString("isdir").equals("1")) {
            String url1 = "http://pan.baidu.com/share/list?uk="
                    + uk
                    + "&shareid="
                    + shareid
                    + "&page=1&num=100&dir="
                    + path
                    + "&order=time&desc=1&_="
                    + time
                    + "&bdstoken=c51077ce0e0e313a16066612a13fbcd4&channel=chunlei&clienttype=0&web=1&app_id="
                    + app_id;
            String fileListJson = httpClientService.doGet(url1);
            System.out.println(fileListJson);
            jsonObject =JSONObject.parseObject(fileListJson);
            jsonArray = JSONArray.parseArray(jsonObject.getString("list"));
        }
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            jsonObject = jsonArray.getJSONObject(i);
            String fileName = jsonObject.getString("server_filename");
            //储存文件名
            map.put("fileName", fileName);
            fs_id.add(jsonObject.getString("fs_id"));
            String fileInfo = httpClientService.doGet("http://pan.baidu.com/api/sharedownload?sign="
                    + sign
                    + "×tamp="
                    + time
                    + "&bdstoken=c51077ce0e0e313a16066612a13fbcd4&channel=chunlei&clienttype=0&web=1&app_id=250528&encrypt=0&product=share&uk="
                    + uk + "&primaryid=" + share_id + "&fid_list=%5B"
                    + fs_id.get(i) + "%5D");
            JSONObject json_data = JSONObject.parseObject(fileInfo);
            if (json_data.getString("errno").equals("0")) {
                JSONArray jsonArray2 = JSONArray.parseArray(json_data.getString("list"));
                json_data = jsonArray2.getJSONObject(0);
                //储存文件下载实链
                map.put("url", json_data.getString("dlink"));
            } else if (json_data.getString("errno").equals("-20")) {
                return null;
                // String getVerCode();
            } else {
                return null;
            }
            list.add(map);
        }
        return list;
    }


}
