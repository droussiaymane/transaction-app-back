package com.transaction.app.repositories;

import com.transaction.app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRespository extends JpaRepository<Transaction,Long> {

    public List<Transaction> findAllBySender_Id(Long id);
    public List<Transaction> findAllByReceiver_Id(Long id);

}
