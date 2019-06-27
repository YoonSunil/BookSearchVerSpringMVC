package com.jp.software_s.book.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jp.software_s.book.bookService.BookService;
import com.jp.software_s.book.bookVO.BookVO;
import com.jp.software_s.book.bookVO.PageMaker;
import com.jp.software_s.book.bookVO.PagingDTO;
import com.jp.software_s.book.bookVO.SearchDTO;

@Controller
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Inject
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/booklist/{pageNum}", method = RequestMethod.GET)
    public String listGET(@PathVariable("pageNum") int pageNum, Model model) {
        logger.info("paging listGET() called...");

        try {
            PagingDTO pagingDTO = new PagingDTO();
            pagingDTO.setPage(pageNum);

            PageMaker pageMaker = new PageMaker();
            pageMaker.setPagingDTO(pagingDTO);
            pageMaker.setTotalCount(bookService.listCount());
            pageMaker.setNowPage(pageNum);
            pageMaker.setNextPage(pageNum);
            pageMaker.setFrontPage(pageNum);
            List<BookVO> data = bookService.listALL(pagingDTO);

            model.addAttribute("data", data);
            model.addAttribute("pageMaker", pageMaker);
            model.addAttribute("status", "list");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "booklist";
    }

    @RequestMapping(value = "/booklist", method = RequestMethod.POST)
    public String listPOST(Model model) {
        logger.info("paging listPOST() called...");
        return "booklist/1";
    }

    @RequestMapping(value = "/bookinsert", method = RequestMethod.GET)
    public String registGET(Model model) {
        logger.info("paging registGET() called...");
        return "bookinsert";
    }

    @RequestMapping(value = "/bookinsert", method = RequestMethod.POST)
    public String registPOST(BookVO bookVO, RedirectAttributes redirectAttributes, Model model) {
        logger.info("paging registPOST() called...");
        int bookId;

        try {
            bookService.insert(bookVO);
            redirectAttributes.addFlashAttribute("msg", "図書情報の登録が完了しました。");
            bookId = bookService.lnsertBookId(bookVO);

            return "redirect:/bookdetail/" + bookId;
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "error");
        }
        return "redirect:/bookinsert";
    }

    @RequestMapping(value = "/bookdetail/{bookId}", method = RequestMethod.GET)
    public String detailGET(@PathVariable("bookId") int bookId, Model model) throws Exception {
        logger.info("paging detailGET() called...");

        BookVO data = bookService.read(bookId);
        model.addAttribute("data", data);

        return "bookdetail";
    }

    @RequestMapping(value = "/bookupload/{bookId}", method = RequestMethod.GET)
    public String updateGET(@PathVariable("bookId") int bookId, Model model) throws Exception {
        logger.info("paging updateGET() called...");

        BookVO data = bookService.read(bookId);
        model.addAttribute("data", data);
        return "bookupload";
    }

    @RequestMapping(value = "/bookupload/{booId}", method = RequestMethod.POST)
    public String updatePOST(@RequestParam("bookId") int bookID, BookVO bookVO, Locale locale, Model model) {
        logger.info("paging updatePOST() called...");

        try {
            bookService.update(bookVO);
            BookVO data = bookService.read(bookVO.getBookId());
            model.addAttribute("msg", "図書情報の更新が完了しました。");
            model.addAttribute("data", data);
            return "bookdetail";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "error");
            return "booklist/1";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePOST(@RequestParam("bookId") int id, RedirectAttributes redirectAttributes) {
        logger.info("paging deletePOST() called...");

        try {
            bookService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "図書情報の削除が完了しました。");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "error");
        }

        return "redirect:/booklist/1";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPOST(@ModelAttribute SearchDTO searchDTO, Model model) throws Exception {
        logger.info("paging searchPOST() called...");

        List<BookVO> data = bookService.listSearch(searchDTO);
        model.addAttribute("data", data);

        return "booklist";
    }

    @RequestMapping(value = "/search/{pageNum}", method = RequestMethod.GET)
    public String searchGET(@ModelAttribute SearchDTO searchDTO, @PathVariable("pageNum") int pageNum, Model model) throws Exception {
        logger.info("paging searchGET() called...");
        try {

            PagingDTO pagingDTO = new PagingDTO();
            PageMaker pageMaker = new PageMaker();
            pagingDTO.setPage(pageNum);
            pageMaker.setPagingDTO(pagingDTO);
            pageMaker.setTotalCount(bookService.searchCount(searchDTO));
            pageMaker.setNowPage(pageNum);
            pageMaker.setNextPage(pageNum);
            pageMaker.setFrontPage(pageNum);
            List<BookVO> data = bookService.listSearchPaging(searchDTO, pagingDTO);

            model.addAttribute("data", data);
            model.addAttribute("searchDTO", searchDTO);
            model.addAttribute("pageMaker", pageMaker);
            model.addAttribute("status", "search");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "booklist";
    }

}
