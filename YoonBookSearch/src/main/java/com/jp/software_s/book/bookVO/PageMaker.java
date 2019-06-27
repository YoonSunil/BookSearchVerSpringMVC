package com.jp.software_s.book.bookVO;

import org.springframework.stereotype.Component;

@Component
public class PageMaker {

    private int totalCount; // 掲示板全体のデータの数
    private int displayPageNum = 5; // 掲示板に一気に表示するページの数 (
                                    // 1,2,3,4,5,6,7,9,10)

    private int startPage; // 現在画面で見えるstartPage番号
    private int endPage; // 現在画面で見える endPage 番号
    private int maxPage;// listの maxPage 番号

    private int frontPage = 1;
    private int nowPage;// 今現在のpage番号
    private int nextPage = nowPage + 1;// 次のpage番号

    private boolean prev; // ページング以前ボタンの活性化かどうか
    private boolean next; // ページング次へボタンの活性化かどうか

    private PagingDTO pagingDTO;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(pagingDTO.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        //Listの最大page番号を計算する
        if ((totalCount & 20) != 0) {
            setMaxPage(totalCount / 20 + 1);
        } else {
            setMaxPage(totalCount / 20);
        }

        int tempEndPage = (int) (Math.ceil(totalCount / (double) pagingDTO.getPerPageNum()));

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * pagingDTO.getPerPageNum() >= totalCount ? false : true;
    }

    public int getFrontPage() {
        return frontPage;
    }
    //"<<"BOTTONを押す時に0Page目に移動しないように設定
    public void setFrontPage(int frontPage) {
        if (frontPage == 1) {
            this.frontPage = 1;
        } else if (frontPage >= 2) {
            this.frontPage = frontPage - 1;
        }
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nowPage) {
        if (maxPage == nowPage) {
            this.nextPage = this.maxPage;
        } else {
            this.nextPage = nowPage + 1;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public PagingDTO getPagingDTO() {
        return pagingDTO;
    }

    public void setPagingDTO(PagingDTO pagingDTO) {
        this.pagingDTO = pagingDTO;
    }

    @Override
    public String toString() {
        return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", pagingDTO=" + pagingDTO + "]";
    }

}
