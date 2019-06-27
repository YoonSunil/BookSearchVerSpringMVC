package com.jp.software_s.book.daoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jp.software_s.book.bookVO.BookVO;
import com.jp.software_s.book.bookVO.PagingDTO;
import com.jp.software_s.book.bookVO.SearchDTO;
import com.jp.software_s.database.MariaDBConnect;

@Repository
public class BookDAOImpl implements BookDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BookDAOImpl() {
        MariaDBConnect.getInstance();
    }

    // 新規登録情報の入力
    @Override
    public void insert(BookVO bookVO) throws Exception {
        con = MariaDBConnect.getConnection();
        StringBuffer sb = new StringBuffer();

        //SQL文を作成する
        sb.append("INSERT INTO ");
        sb.append("book (isbn, jancode, title, writer, pub_com, pub_date,update_time_stamp,create_time_stamp) ");
        sb.append("VALUES ");
        sb.append("( " + bookVO.getIsbn());
        sb.append(", " + bookVO.getJanCode());
        sb.append(", '" + bookVO.getTitle() + "'");
        sb.append(", '" + bookVO.getWriter() + "'");
        sb.append(", '" + bookVO.getPubCom() + "'");
        sb.append(", '" + bookVO.getPubDate() + "'");
        //TIME_STAMPの部分
        sb.append(", now()");
        sb.append(", now()");
        sb.append(")");

        ps = con.prepareStatement(sb.toString());
        rs = ps.executeQuery();

    }

    // BOOK_IDを利用して本の情報を詳細画面を見る
    @Override
    public BookVO read(int bookId) throws Exception {
        con = MariaDBConnect.getConnection();
        String sql = "select book_id,isbn,jancode,title,writer,pub_com,pub_date from book where book_id = " + bookId;
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        BookVO data = new BookVO();
        while (rs.next()) {
            data.setBookId(rs.getInt("book_id"));
            data.setIsbn(rs.getLong("isbn"));
            data.setJanCode(rs.getLong("jancode"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));
            data.setPubCom(rs.getString("pub_com"));
            data.setPubDate(rs.getString("pub_date"));
        }
        return data;
    }

    // 本の情報を更新
    @Override
    public void update(BookVO bookVO) throws Exception {
        con = MariaDBConnect.getConnection();
        StringBuffer sb = new StringBuffer();

        sb.append("UPDATE book SET");
        sb.append(" title = " + bookVO.getIsbn());
        sb.append(", jancode = " + bookVO.getJanCode());
        sb.append(", title = '" + bookVO.getTitle() + "'");
        sb.append(", writer = '" + bookVO.getWriter() + "'");
        sb.append(", pub_com = '" + bookVO.getPubCom() + "'");
        sb.append(", pub_date = '" + bookVO.getPubDate() + "'");
        sb.append("WHERE book_id = " + bookVO.getBookId());

        ps = con.prepareStatement(sb.toString());
        rs = ps.executeQuery();
    }

    // 本の情報を消す
    @Override
    public void delete(int bookId) throws Exception {
        con = MariaDBConnect.getConnection();
        String str = "DELETE FROM book WHERE book_id = " + bookId;
        ps = con.prepareStatement(str);
        rs = ps.executeQuery();

    }

    // listのPaging
    @Override
    public List<BookVO> listALL(PagingDTO pagingDTO) throws Exception {
        con = MariaDBConnect.getConnection();
        String sql = "select * from(select book_id,isbn,jancode,title,writer,pub_com,pub_date from book) as book LIMIT " + ((pagingDTO.getPage() - 1) * 20 + 1) + ", 20";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        List<BookVO> list = new ArrayList<BookVO>();

        while (rs.next()) {
            BookVO data = new BookVO();
            data.setBookId(rs.getInt("book_id"));
            data.setIsbn(rs.getLong("isbn"));
            data.setJanCode(rs.getLong("jancode"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));
            data.setPubCom(rs.getString("pub_com"));
            data.setPubDate(rs.getString("pub_date"));
            list.add(data);
        }

        return list;
    }

    // 条件無しで全ての情報をlistに保存
    @Override
    public List<BookVO> listSearch(SearchDTO searchDTO) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM book WHERE book_id > 0");
        sb = makeSearchString(sb, searchDTO);
        con = MariaDBConnect.getConnection();
        ps = con.prepareStatement(sb.toString());
        rs = ps.executeQuery();

        List<BookVO> list = new ArrayList<BookVO>();

        while (rs.next()) {
            BookVO data = new BookVO();
            data.setBookId(rs.getInt("book_id"));
            data.setIsbn(rs.getLong("isbn"));
            data.setJanCode(rs.getLong("jancode"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));
            data.setPubCom(rs.getString("pub_com"));
            data.setPubDate(rs.getString("pub_date"));
            list.add(data);
        }

        return list;
    }

    // 本の情報の数
    @Override
    public int listCount() throws Exception {
        con = MariaDBConnect.getConnection();
        String sql = "select count(book_id) as cnt from book";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("cnt");
    }

    // 登録本の情報を利用してBOOK_IDを探す
    @Override
    public int insertBookId(BookVO bookVO) throws Exception {
        con = MariaDBConnect.getConnection();
        String sql = "select book_id from book where isbn =" + bookVO.getIsbn();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("book_id");
    }

    // 検索結果の数
    @Override
    public int searchCount(SearchDTO searchDTO) throws Exception {
        con = MariaDBConnect.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append("select count(book_id) as cnt from book where book_id >= 0");
        sb = makeSearchString(sb, searchDTO);
        ps = MariaDBConnect.getConnection().prepareStatement(sb.toString());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("cnt");

    }

    // 検索結果のPaging
    @Override
    public List<BookVO> listSearchPaging(SearchDTO searchDTO, PagingDTO pagingDTO) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM book WHERE book_id > 0 ");
        sb = makeSearchString(sb, searchDTO);
        sb.append(" limit " + ((pagingDTO.getPage() - 1) * 20 + 1) + ", " + 20);

        ps = MariaDBConnect.getConnection().prepareStatement(sb.toString());
        rs = ps.executeQuery();

        //SQLで検索した内容を入れるListを準備する
        List<BookVO> list = new ArrayList<BookVO>();

        //SQLの内容のROWの次があったら
        while (rs.next()) {
            BookVO data = new BookVO();
            //SQLのColumnの順番に合わせて入れる
            data.setBookId(rs.getInt("book_id"));
            data.setIsbn(rs.getLong("isbn"));
            data.setJanCode(rs.getLong("jancode"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));
            data.setPubCom(rs.getString("pub_com"));
            data.setPubDate(rs.getString("pub_date"));
            //ListにBeanの情報を入れる
            list.add(data);
        }
        return list;
    }

    // 検索SQL作成
    public StringBuffer makeSearchString(StringBuffer sb, SearchDTO searchDTO) throws SQLException {
        if (searchDTO.getIsbn() != 0) {
            sb.append(" AND isbn = " + searchDTO.getIsbn());
        }
        if (searchDTO.getJanCode() != 0) {
            sb.append(" AND jancode = " + searchDTO.getIsbn());
        }
        if (searchDTO.getPubCom() != "") {
            switch (searchDTO.getMatchPubCom()) {
                case "full":
                    sb.append(" AND pub_com = '" + searchDTO.getPubCom() + "'");
                    break;
                case "forward":
                    sb.append(" AND pub_com LIKE CONCAT('" + searchDTO.getPubCom() + "', '%')");
                    break;
                case "partial":
                    sb.append(" AND pub_com LIKE CONCAT('%', '" + searchDTO.getPubCom() + "','%')");
                    break;
            }
        }
        if (searchDTO.getTitle() != "") {
            switch (searchDTO.getMatchTitle()) {
                case "full":
                    sb.append(" AND title = '" + searchDTO.getTitle() + "'");
                    break;
                case "forward":
                    sb.append(" AND title LIKE CONCAT('" + searchDTO.getTitle() + "', '%')");
                    break;
                case "partial":
                    sb.append(" AND title LIKE CONCAT('%', '" + searchDTO.getTitle() + "','%')");
                    break;
            }
        }
        if (searchDTO.getWriter() != "") {
            switch (searchDTO.getMatchWriter()) {
                case "full":
                    sb.append(" AND writer = '" + searchDTO.getWriter() + "'");
                    break;
                case "forward":
                    sb.append(" AND writer LIKE CONCAT('" + searchDTO.getWriter() + "', '%')");
                    break;
                case "partial":
                    sb.append(" AND writer LIKE CONCAT('%', '" + searchDTO.getWriter() + "','%')");
                    break;
            }
        }

        if (searchDTO.getStartDate() != "" && searchDTO.getEndDate() != "") {
            sb.append(" AND pub_date BETWEEN '" + searchDTO.getStartDate() + "00' AND pub_date('" + searchDTO.getEndDate() + "00')");
        }
        return sb;
    }

    // 全ての処理が終わったらDBConnect終了
    public void disconnect() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        ps.close();
        con.close();
    }
}
