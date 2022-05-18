package com.fd.baselibrary.baseBean;

import java.util.List;

public class ResultBean {
    /**
     * total : 1
     * list : [{"shaePkid":1372,"shaeCode":"155892448314755328","contType":"0","shaeType":null,"userCode":"CLHhAxcn13IsdU_ikn6Nz63xBX0","shaeTitl":null,"sharTite":null,"userNick":"苏幕","likeNumr":null,"evaeNumr":null,"coltNumr":null,"userHead":"home/filedata/mall/0/CLHhAxcn13IsdU_ikn6Nz63xBX0/78/1545875204007.jpg","userPice":"http://91afd.oss-cn-shenzhen.aliyuncs.com/home/filedata/mall/1/1558923860554/197/1558923911960.jpg,http://91afd.oss-cn-shenzhen.aliyuncs.com/home/filedata/mall/1/1558923860554/197/1558923912381.jpg,http://91afd.oss-cn-shenzhen.aliyuncs.com/home/filedata/mall/1/1558923860554/197/1558923912779.jpg","fansNumr":null,"attnNumr":null,"shaeStas":null,"regnCode":null,"regnName":null,"creeTime":null,"creeUser":null,"modyTime":null,"modyUser":null,"tagCode":null,"tagCont":null,"areaNumr":null,"areaAdds":null,"shaeLone":null,"shaeLate":null,"pubhTime":null,"raisLook":"0","vidoCovr":null,"virlLook":null,"virlLike":null,"worsCode":null,"exaeStas":null,"userPicture":null,"loveSharingEvaluates":null,"isLike":null,"isAttention":null,"isCollection":null,"awakeUsers":null,"distance":null,"firstPicture":null,"videoPicture":null,"materials":null,"pictures":null,"loveSharings":null,"widthAndHigh":null,"flowStas":null}]
     * pageNum : 1
     * pageSize : 10
     * size : 1
     * startRow : 1
     * endRow : 1
     * pages : 1
     * prePage : 0
     * nextPage : 0
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * firstPage : 1
     * lastPage : 1
     */

    private int total;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<?> list;
    private List<Integer> navigatepageNums;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
