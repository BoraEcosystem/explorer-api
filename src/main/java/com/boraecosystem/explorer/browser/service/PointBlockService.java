package com.boraecosystem.explorer.browser.service;

import com.boraecosystem.explorer.browser.config.DataSourceContextHolder;
import com.boraecosystem.explorer.browser.point.domain.BlockSummary;
import com.boraecosystem.explorer.browser.point.repository.BlockSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointBlockService {

    private final BlockSummaryRepository blockRepository;

    @Autowired
    public PointBlockService(BlockSummaryRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Page<BlockSummary> getBlockSummaries(String appId, final Pageable pageable) {
        DataSourceContextHolder.set(appId);
        Page<BlockSummary> summaries = blockRepository.findAllByOrderByBlockNoDesc(pageable);
        DataSourceContextHolder.clear();
        return summaries;
    }

}
