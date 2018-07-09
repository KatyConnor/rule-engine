package hx.com.example.rule.param;

import hx.com.example.rule.common.param.BaseParam;
import hx.data.mybatis.annotation.EqualTo;
import hx.data.mybatis.param.PageParam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuQueryParamDTO extends PageParam {

    @EqualTo(column = "system")
    private String systemCode;

    private String distinct;

    private String orderByClause = "create_time";

    private List<MenuQueryParamDTO> oredCriteria =new ArrayList<>();

}