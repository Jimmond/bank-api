package com.jimmy.bankapi.repository;
import java.util.List;
import com.jimmy.bankapi.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository
        extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findByAccountId(
            Long accountId
    );
}