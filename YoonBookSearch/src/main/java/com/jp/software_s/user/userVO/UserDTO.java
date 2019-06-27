package com.jp.software_s.user.userVO;

public class UserDTO {
    private String userId;
    private String userPw;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "UserDTO [user_id=" + userId + ", user_pw=" + userPw + "]";
    }

}
