package com.boraecosystem.explorer.browser.point.repository;

import com.boraecosystem.explorer.browser.point.domain.TransactionSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionSummaryRepository extends PagingAndSortingRepository<TransactionSummary, String> {
    Page<TransactionSummary> findAllByOrderByBlockDateDescTransactionIndexDesc(Pageable pageable);

    TransactionSummary findByTransactionHash(String transactionHash);

    Page<TransactionSummary> findAllByFromAddressOrToAddressOrderByBlockDateDescTransactionHashAsc(String fromAddress, String toAddress, Pageable pageable);
}
