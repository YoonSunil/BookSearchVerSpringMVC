package com.jp.software_s.book.bookVO;

import org.springframework.stereotype.Component;

@Component
public class BookVO {

    private int bookId;
    private long isbn;
    private long janCode;
    private String title;
    private String writer;
    private String pubCom;
    private String pubDate;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

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


    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "bookVO [bookId=" + bookId + ", isbn=" + isbn + ", janCode=" + janCode + ", title=" + title + ", writer=" + writer + ", pub_com=" + pubCom + ", pub_date=" + pubDate + "]";
    }

}
