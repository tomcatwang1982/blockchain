package com.tomcatwang.blockchain.core.repository;


import com.tomcatwang.blockchain.core.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
