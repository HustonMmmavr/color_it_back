package com.color_it.backend.controllers;

import com.color_it.backend.views.ResponseView;
import com.color_it.backend.views.UpdateEmailView;
import com.color_it.backend.views.ViewStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Locale;


// update email, nick, password, avatar
@RestController
@RequestMapping(path="/api/user/")
public class UserController {
    @GetMapping(path="/user_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseView> getUser(HttpSession httpSession, Locale locale) {
        return new ResponseEntity<>(new ResponseView(), HttpStatus.OK);
    }

    @PostMapping(path="update_email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseView> updateEmail(@RequestBody UpdateEmailView updateEmailView, HttpSession httpSession, Locale locale) {
        final ViewStatus viewStatus = updateEmailView.checkValid();
        if (!viewStatus.isValid()) {
            return Response
        }
        return null;
    }

    @PostMapping(path="update_password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseView> updatePassword(@Req) {
        return null;
    }

    @GetMapping(value = "/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseView> getRating() {
        return null;
    }
}
