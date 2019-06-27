package com.jp.software_s.book.bookService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jp.software_s.book.bookVO.BookVO;
import com.jp.software_s.book.bookVO.PagingDTO;
import com.jp.software_s.book.bookVO.SearchDTO;
import com.jp.software_s.book.daoList.BookDAO;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Inject
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void insert(BookVO bookVO) throws Exception {
        bookDAO.insert(bookVO);
    }

    @Override
    public BookVO read(int bookId) throws Exception {
        return bookDAO.read(bookId);
    }

    @Override
    public void update(BookVO bookVO) throws Exception {
        bookDAO.update(bookVO);
    }

    @Override
    public void delete(int bookId) throws Exception {
        bookDAO.delete(bookId);
    }

    @Override
    public List<BookVO> listALL(PagingDTO pagingDTO) throws Exception {
        return bookDAO.listALL(pagingDTO);
    }

    @Override
    public List<BookVO> listSearch(SearchDTO searchDTO) throws Exception {
        return bookDAO.listSearch(searchDTO);
    }


    @Override
    public int listCount() throws Exception {

        return bookDAO.listCount();
    }

    @Override
    public int lnsertBookId(BookVO bookVO) throws Exception {
       return bookDAO.insertBookId(bookVO);
    }

    @Override
    public int searchCount(SearchDTO searchDTO) throws Exception {
        return bookDAO.searchCount(searchDTO);
    }

    @Override
    public List<BookVO> listSearchPaging(SearchDTO searchDTO, PagingDTO pagingDTO) throws Exception {
        return bookDAO.listSearchPaging(searchDTO, pagingDTO);
    }

}
