package com.transaction.app.controllers;


import com.transaction.app.models.User;
import com.transaction.app.repositories.UserRepository;
import com.transaction.app.requests.TransactionRequest;
import com.transaction.app.requests.UserLoginRequest;
import com.transaction.app.responses.ResponseMessage;
import com.transaction.app.responses.TransactionResponse;
import com.transaction.app.responses.UserBalanceResponse;
import com.transaction.app.responses.UserLoginResponse;
import com.transaction.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000/")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest){

        String email = userLoginRequest.getMail();
        User user=userService.findByEmail(email);
        if(user==null){
            return ResponseEntity.badRequest().body(new ResponseMessage("no user found"));
        }
        else{
            if(user.getPassword().equals(userLoginRequest.getPassword())){
                return ResponseEntity.ok().body(new UserLoginResponse(user.getId(),user.getEmail()));
            }
            else{
                return ResponseEntity.badRequest().body(new ResponseMessage("password incorrect"));
            }
        }

    }

    @GetMapping("/getbalance")
    public ResponseEntity getbalance(@RequestParam Long id){

        User user=userService.findById(id);

        return ResponseEntity.ok(new UserBalanceResponse(user.getBalance()));





    }



}
