package com.transaction.app.services;

import com.transaction.app.models.Transaction;
import com.transaction.app.models.User;
import com.transaction.app.repositories.TransactionRespository;
import com.transaction.app.repositories.UserRepository;
import com.transaction.app.requests.TransactionRequest;
import com.transaction.app.responses.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    UserService userService;

    @Autowired
    TransactionRespository transactionRespository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void createTransaction(TransactionRequest transactionRequest) throws Exception {


        User sender =userService.findByEmail(transactionRequest.getEmailSender());
        User receiver =userService.findByEmail(transactionRequest.getEmailReceiver());
        if(receiver==null){
            throw new Exception("receiver Email is not found");
        }

        if(receiver.getId().equals(sender.getId())){
            throw new Exception("change the receiver email");
        }
        if(sender.getBalance() < transactionRequest.getAmount()){
            throw new Exception("Insuffisant Balance");
        }

        Transaction transaction=new Transaction();
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setAmount(transactionRequest.getAmount());

        receiver.setBalance(receiver.getBalance()+transaction.getAmount());
        sender.setBalance(sender.getBalance()-transaction.getAmount());


        transactionRespository.save(transaction);


    }

    @Override
    public List<TransactionResponse> findAllByUserId(Long id) {
        List<Transaction> results=transactionRespository.findAllByReceiver_Id(id);
        List<Transaction> transactionsSenderList=transactionRespository.findAllBySender_Id(id);


        results.addAll(transactionsSenderList);
        List<TransactionResponse> transactionResponses=new ArrayList<>();
        results.forEach(transaction -> {
            TransactionResponse transactionResponse=new TransactionResponse(transaction.getId(),transaction.getAmount(),transaction.getSender().getEmail(),transaction.getReceiver().getEmail());
            transactionResponses.add(transactionResponse);
        });


        return transactionResponses;
    }
}
