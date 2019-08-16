package com.tomcatwang.blockchain.core.manager;

import com.tomcatwang.blockchain.core.model.SyncEntity;
import com.tomcatwang.blockchain.core.repository.SyncRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Service
public class SyncManager {
    @Resource
    private SyncRepository syncRepository;

    public SyncEntity findLastOne() {
        return syncRepository.findTopByOrderByIdDesc();
    }

    public SyncEntity save(SyncEntity syncEntity) {
        return syncRepository.save(syncEntity);
    }

    public Object findAll(Pageable pageable) {
        return syncRepository.findAll(pageable);
    }

    public void deleteAll() {
        syncRepository.deleteAll();
    }
}
