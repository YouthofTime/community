package com.nowcoder.community.entity;

public class Page {
    private int current=1; // 当前页面

    private int rows;// 总记录数

    private int limit=10;// 一页数目

    private String path;// 当前路径（复用不止index)

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1&&limit<=100)
        this.limit = limit;
    }

    public void setCurrent(int current) {
        if(current>0) // 因为current有一个默认值1，所以负数会被1给替换
            this.current = current;
    }

    public int getCurrent() {
        if(current>getTotalPages())
            current=getTotalPages();
        return current;
    }



    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0)
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getOffset(){
        int offset=(current-1)>=0?(current-1)*limit:0;
        return offset;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPages(){
        int totalPages;
        if(rows%limit==0)
            totalPages=rows/limit;
        else
            totalPages=rows/limit+1;
        return totalPages;
    }
    public int getFrom(){
        int from=(getCurrent()-2)>0?getCurrent()-2:getCurrent();
        return from;
    }
    public int getTo(){
        int totalPages=getTotalPages();
        int to=(current+2)>totalPages?totalPages:current+2;
        return to;
    }

}
