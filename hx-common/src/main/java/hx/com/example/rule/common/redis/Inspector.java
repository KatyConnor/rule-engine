package hx.com.example.rule.common.redis;

import hx.com.example.rule.common.enums.InspectParamEnum;

import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-05-07 9:40
 */
public interface Inspector {

    boolean inspect(Map<InspectParamEnum, Object> paramMap);
}
