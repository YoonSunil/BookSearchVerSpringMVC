package com.jp.software_s.user.daoList;

import com.jp.software_s.user.userVO.UserDTO;

public interface UserDAO {
    boolean check(UserDTO userDTO) throws Exception;

}
