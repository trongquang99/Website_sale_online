package com.website.online.sale.repository.store;

import com.website.online.sale.model.Cuahang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuahangRepository extends JpaRepository<Cuahang, Long> {
}