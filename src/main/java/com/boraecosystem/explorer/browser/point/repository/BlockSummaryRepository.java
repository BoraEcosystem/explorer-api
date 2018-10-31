package com.boraecosystem.explorer.browser.point.repository;

import com.boraecosystem.explorer.browser.point.domain.BlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BlockSummaryRepository extends PagingAndSortingRepository<BlockSummary, BigInteger> {
    Page<BlockSummary> findAllByOrderByBlockNoDesc(Pageable pageable);
}
