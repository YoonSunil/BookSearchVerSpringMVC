package com.jp.software_s.book.bookService;

import java.util.List;

import com.jp.software_s.book.bookVO.BookVO;
import com.jp.software_s.book.bookVO.PagingDTO;
import com.jp.software_s.book.bookVO.SearchDTO;

//Controllerで呼ぶMethodでありInterface
public interface BookService {

    void insert(BookVO bookVO) throws Exception;

    BookVO read(int bookId) throws Exception;

    void update(BookVO bookVO) throws Exception;

    void delete(int bookId) throws Exception;

    List<BookVO> listALL(PagingDTO pagingDTO) throws Exception;

    List<BookVO> listSearch(SearchDTO searchDTO) throws Exception;

    List<BookVO> listSearchPaging(SearchDTO searchDTO, PagingDTO pagingDTO) throws Exception;

    int listCount() throws Exception;

    int lnsertBookId(BookVO bookVO) throws Exception;

    int searchCount(SearchDTO searchDTO) throws Exception;
}
