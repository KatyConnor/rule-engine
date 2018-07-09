package hx.com.example.rule.param;

import hx.com.example.rule.common.param.BaseParam;
import hx.data.mybatis.annotation.EqualTo;
import hx.data.mybatis.param.PageParam;

import java.io.Serializable;

/**
 * @Author mingliang
 * @Date 2017-12-20 17:45
 */
public class ReferenceVariabQueryParamDTO extends PageParam {

    @EqualTo
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
