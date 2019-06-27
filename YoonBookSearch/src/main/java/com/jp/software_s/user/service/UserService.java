package com.jp.software_s.user.service;

import com.jp.software_s.user.userVO.UserDTO;

public interface UserService {
    public boolean check(UserDTO userDTO) throws Exception;

}
