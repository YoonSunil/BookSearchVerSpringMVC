package com.jp.software_s.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jp.software_s.user.service.UserService;
import com.jp.software_s.user.userVO.UserDTO;


@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/bookmain", method = RequestMethod.POST)
    public String loginPOST(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserPw(userPw);

        System.out.println(userId);
        System.out.println(userPw);
        try {
            if (userService.check(userDTO)) {
                return "redirect:booklist/1";
            } else {
                model.addAttribute("user_id", userId);
                model.addAttribute("error", "wrong");
                return "bookmain";
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:booklist/1";
    }
}
