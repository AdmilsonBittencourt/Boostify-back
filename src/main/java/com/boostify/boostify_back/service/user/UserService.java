package com.boostify.boostify_back.service.user;

import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.model.dto.UserDTO;
import com.boostify.boostify_back.service.BaseService;

public interface UserService extends BaseService<UserDTO, Long> {

    User checkUserExists(Long id);
}

