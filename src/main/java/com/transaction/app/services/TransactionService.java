package com.transaction.app.services;

import com.transaction.app.models.Transaction;
import com.transaction.app.requests.TransactionRequest;
import com.transaction.app.responses.TransactionResponse;

import java.util.List;

public interface TransactionService {

    void createTransaction(TransactionRequest transactionRequest) throws Exception;

    List<TransactionResponse> findAllByUserId(Long id);
}
