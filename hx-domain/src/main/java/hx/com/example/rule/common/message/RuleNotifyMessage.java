package hx.com.example.rule.common.message;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.enums.HttpTypeEnum;

/**
 * @Author mingliang
 * @Date 2018-01-29 11:12
 */
public class RuleNotifyMessage {

    private String url;
    private HttpTypeEnum requestMethod;
    private JSONObject data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpTypeEnum getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(HttpTypeEnum requestMethod) {
        this.requestMethod = requestMethod;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
