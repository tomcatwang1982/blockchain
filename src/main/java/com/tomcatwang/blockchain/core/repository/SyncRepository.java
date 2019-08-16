package com.tomcatwang.blockchain.core.repository;


import com.tomcatwang.blockchain.core.model.SyncEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tomcatwang wrote on 2019/08/19..
 */
public interface SyncRepository extends JpaRepository<SyncEntity, Long> {
    SyncEntity findTopByOrderByIdDesc();
}
