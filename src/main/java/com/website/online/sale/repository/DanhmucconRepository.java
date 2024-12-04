package com.website.online.sale.repository;

import com.website.online.sale.model.Danhmuccon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhmucconRepository extends JpaRepository<Danhmuccon, Long> {
    List<Danhmuccon> findByIdDm(Long idDm);
}