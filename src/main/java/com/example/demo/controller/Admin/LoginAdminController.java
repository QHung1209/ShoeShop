package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.demo.service.Admin.imp.LoginAdminServiceImp;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.payload.ResponseData;
import com.example.demo.payload.Admin.SignUpAdminRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")

public class LoginAdminController {

    @Autowired
    LoginAdminServiceImp loginServiceImp;

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {

        ResponseData responseData = new ResponseData();
        if (loginServiceImp.checkLogin(username, password)) {
            responseData.setData(true);
        } else {
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignUpAdminRequest signUpAdminRequest) {

        ResponseData responseData = new ResponseData();

        responseData.setData(loginServiceImp.addUser(signUpAdminRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException, java.io.IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("src/main/resources/templates/Admin/login.html");
    }

}
