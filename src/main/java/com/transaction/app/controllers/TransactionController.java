package com.transaction.app.controllers;

import com.transaction.app.models.User;
import com.transaction.app.requests.TransactionRequest;
import com.transaction.app.responses.ResponseMessage;
import com.transaction.app.responses.TransactionResponse;
import com.transaction.app.responses.UserLoginResponse;
import com.transaction.app.services.TransactionService;
import com.transaction.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:3000/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/createtransaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest){


    try{
        transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok().body(new ResponseMessage("created"));

    }catch (Exception e){
        return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
    }



    }

    @GetMapping("/getalltransactions")
    public ResponseEntity createTransaction(@RequestParam Long id){

        List<TransactionResponse> transactionResponses=transactionService.findAllByUserId(id);
        return ResponseEntity.ok(transactionResponses);





    }



}
