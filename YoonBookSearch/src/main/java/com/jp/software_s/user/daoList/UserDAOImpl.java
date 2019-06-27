package com.jp.software_s.user.daoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jp.software_s.database.MariaDBConnect;
import com.jp.software_s.user.controller.UserController;
import com.jp.software_s.user.userVO.UserDTO;


@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAOImpl() {
        MariaDBConnect.getInstance();
    }

    @Override
    public boolean check(UserDTO userDTO) throws Exception {
        logger.info("UserDAO check");

        con = MariaDBConnect.getConnection();
        String sql = "select user_id,user_pw from user where user_id = '" + userDTO.getUserId() + "' and user_pw = '" + userDTO.getUserPw() + "'";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        boolean success = rs.next();
        return success;
    }

}
