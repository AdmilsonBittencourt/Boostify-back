package com.boostify.boostify_back.common;

import com.boostify.boostify_back.controller.dto.UserDTO;
import com.boostify.boostify_back.model.User;

public class UserConstants {

    public static final User USER = new User("Pedro", "pedro@gmail.com", "Barca0202@");
    public static final UserDTO USER_DTO = new UserDTO("Pedro", "pedro@gmail.com", "Barca0202@");
    public static final UserDTO INVALID_USER = new UserDTO("", "", "");
}
