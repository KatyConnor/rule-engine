package hx.com.example.rule.common.param;

import java.io.Serializable;

/**
 * @Author mingliang
 * @Date 2017-12-12 17:15
 */
public abstract class BaseParam implements Serializable {

    private int pageNum = 1;
    private int pageSize = 20;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
