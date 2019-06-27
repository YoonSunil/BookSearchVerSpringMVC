package com.jp.software_s.book.bookVO;

import org.springframework.stereotype.Component;

//検索する時のText AreaのMapping
@Component
public class SearchDTO {

    private long isbn;
    private long janCode;

    private String title;
    private String matchTitle;

    private String writer;
    private String matchWriter;

    private String pubCom;
    private String matchPubCom;

    private String startDate;
    private String endDate;

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public long getJanCode() {
        return janCode;
    }

    public void setJanCode(long janCode) {
        this.janCode = janCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPubCom() {
        return pubCom;
    }

    public void setPubCom(String pubCom) {
        this.pubCom = pubCom;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public String getMatchWriter() {
        return matchWriter;
    }

    public void setMatchWriter(String matchWriter) {
        this.matchWriter = matchWriter;
    }

    public String getMatchPubCom() {
        return matchPubCom;
    }

    public void setMatchPubCom(String matchPubCom) {
        this.matchPubCom = matchPubCom;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchDTO [isbn=" + isbn + ", janCode=" + janCode + ", title=" + title + ", writer=" + writer + ", pub_com=" + pubCom + ", matchTitle=" + matchTitle + ", matchWriter=" + matchWriter + ", matchPubCom=" + matchPubCom
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
