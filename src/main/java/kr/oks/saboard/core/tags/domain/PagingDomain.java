package kr.oks.saboard.core.tags.domain;

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
