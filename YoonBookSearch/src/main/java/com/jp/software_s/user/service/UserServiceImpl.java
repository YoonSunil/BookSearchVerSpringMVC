package com.jp.software_s.user.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jp.software_s.user.controller.UserController;
import com.jp.software_s.user.daoList.UserDAO;
import com.jp.software_s.user.userVO.UserDTO;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDAO userDAO;

    @Inject
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public boolean check(UserDTO userDTO) throws Exception {
        logger.info("UserService check");
        return userDAO.check(userDTO);
    }

}
