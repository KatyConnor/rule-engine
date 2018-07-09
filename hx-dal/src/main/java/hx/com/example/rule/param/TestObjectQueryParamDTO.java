package hx.com.example.rule.param;

import hx.com.example.rule.common.param.BaseParam;
import hx.data.mybatis.annotation.EqualTo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestObjectQueryParamDTO extends BaseParam {

    @EqualTo(column = "id")
    private Long id;

}