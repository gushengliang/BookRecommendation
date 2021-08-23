package org.bookrec.utils;

import java.io.Serializable;
import java.util.List;

/**
 * PageUtil
 *
 * @author a1311
 */
public class PageUtil implements Serializable {
    /***/
    private static final long serialVersionUID = 1L;

    /**
     * 总的记录条数
     */
    private int totalRecord;

    /**
     * 记录列表
     */
    private List<?> records;
    /**
     * 页码
     */
    private int pageNo = 1;

    /**
     * 每页显示长度
     */
    private int pageSize = 10;

    /**
     * 分页结束
     *
     * @return 结束位置
     */
    public String getEnd() {
        return String.valueOf((pageNo * pageSize));
    }

    /**
     * 分页开始
     *
     * @return 开始位置
     */
    public String getStart() {
        return String.valueOf(((pageNo - 1) * pageSize));
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
