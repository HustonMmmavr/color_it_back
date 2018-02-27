package com.color_it.backend.services;

import com.color_it.backend.entities.UserEntity;
import com.color_it.backend.repositories.Repository;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Repository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final String passwordSault = "sault";

    @Autowired
    public UserService(final Repository repository) {
        this.userRepository = repository;
    }

    public UserEntity getUser() {
        return null;
    }

    @Autowired
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceResponse getUsers(Integer limit, Integer offset) {
        return null;
    }

    private boolean checkPasswords(String internalPassword, String externalPassword) {
        String saultedPassword = passwordSault + externalPassword + passwordSault;
        if (!passwordEncoder().matches(internalPassword, saultedPassword)) {
            return false;
        }
        return true;
    }

    // maybe entity
    public UserServiceResponse authenticateUser(UserEntity userEntity) {
        UserServiceResponse userServiceResponse = new UserServiceResponse(UserServiceStatusCode.OK_STATE);
        try {
            final UserEntity existingUserEntity = userRepository.getUser();
            if (!checkPasswords(existingUserEntity.getPassword(), userEntity.getPassword())) {
                userServiceResponse.setStatusCode(UserServiceStatusCode.PASSWORD_MATCH_ERROR_STATE);
            }
        } catch (DataAccessException dAEx) {
            userServiceResponse.setStatusCode(UserServiceStatusCode.DB_ERROR_STATE);
        }
        return userServiceResponse;
    }

    public UserServiceResponse createUser(UserEntity user) {
        final UserServiceResponse userServiceResponse = new UserServiceResponse();
        try {
            // create password
            this.userRepository.save(user);
            userServiceResponse.setStatusCode(UserServiceStatusCode.CREATED_STATE);
        } catch (DataIntegrityViolationException dIVEx) {
            ConstraintViolationException cEx = (ConstraintViolationException)dIVEx.getCause();
            if (cEx.getConstraintName().equals("nickname_constraint")) {
                userServiceResponse.setStatusCode(UserServiceStatusCode.CONFLICT_NAME_STATE);
            } else {
                userServiceResponse.setStatusCode(UserServiceStatusCode.CONFLICT_EMAIL_STATE);
            }
        } catch (DataAccessException dAEx) {
            userServiceResponse.setStatusCode(UserServiceStatusCode.DB_ERROR_STATE);
        }
        return userServiceResponse;
    }

    public UserServiceResponse updateEmail(String newEmail) {
        return null;
    }

    public UserServiceResponse updatePassword(String oldPassword, String newPassword) {
        return null;
    }


    public UserServiceResponse userExists(String nickname) {
        final UserServiceResponse userServiceResponse = new UserServiceResponse(UserServiceStatusCode.OK_STATE);
        try {
            if (!userRepository.exists(nickname)) {
                userServiceResponse.setStatusCode(UserServiceStatusCode.NAME_MATCH_ERROR_STATE);
            }
        } catch (DataAccessException daEx) {
            LOGGER.error("Error DataBase", daEx);
            userServiceResponse.setStatusCode(UserServiceStatusCode.DB_ERROR_STATE);
        }
        return userServiceResponse;
    }

//    public UserServiceResponse
}
