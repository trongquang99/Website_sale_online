package com.website.online.sale.repository;

import com.website.online.sale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findById(Long Long);
    User findFirstByUserNameAndPass(String userName, String pass);
    User findByUserNameAndPass(String userName, String pass);

}
