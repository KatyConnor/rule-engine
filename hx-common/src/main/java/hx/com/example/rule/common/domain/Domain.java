package hx.com.example.rule.common.domain;

import hx.com.example.rule.common.aop.DomainAop;
import hx.com.example.rule.common.utils.ObjectUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-14 14:16
 */
@Getter
@Setter
public class Domain implements Serializable {

    private String gid;

    private String id;

    private Date createTime;

    private Date updateTime;

    private Long version;

    @Override
    public String toString() {
        return ObjectUtils.objectToString(this);
    }
}
