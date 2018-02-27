package com.color_it.backend.services;

import com.color_it.backend.common.AbstractServiceresponse;
import com.color_it.backend.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public class UserServiceResponse implements AbstractServiceresponse {
    UserEntity entity;
    UserServiceStatusCode statusCode;

    public UserServiceResponse() {
        statusCode = UserServiceStatusCode.OK_STATE;
    }

    public UserServiceResponse(UserServiceStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public UserServiceStatusCode getStatusCode() {
        return statusCode;
    }

    public boolean isValid() {
        return this.statusCode == UserServiceStatusCode.OK_STATE;
    }

    public void setStatusCode(UserServiceStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public ResponseEntity toHttpResponse() {
        return null;
    }
}
