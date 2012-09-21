package kr.oks.saboard.common.tags.domain;

/**
 * Created by IntelliJ IDEA.
 * User: ceres22
 * Date: 2009. 12. 8
 * Time: 오후 3:33:34
 * To change this template use File | Settings | File Templates.
 */
public class PagingDomain {
    private int pageNo = 1;
    private int pageSize = 15;
    private int totalSize = 0;

    public PagingDomain() {}
    public PagingDomain(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getStartNo() {
        return (this.pageNo - 1) * this.pageSize;
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
