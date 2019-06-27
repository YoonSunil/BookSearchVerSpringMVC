package com.jp.software_s.book.bookVO;

import org.springframework.stereotype.Component;

//Pagination Setting
@Component
public class PagingDTO {
    //PageNunber
    private int page;
    //一つの画面に表示するDATAの数の設定
    private int perPageNum;

    public PagingDTO(){
        this.page = 1;
        this.perPageNum = 20;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0){
            this.page = 1;
            return;
        }
        this.page = page;
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int perPageNum) {
        if(perPageNum <= 0 || perPageNum >100){
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }
    //pageの始まり
    public int getPageStart(){
      return(this.page-1) * perPageNum;
    }

    @Override
    public String toString(){
        return "PegingDTO [page="+page+", perPageNum"+perPageNum+"]";
    }
}
