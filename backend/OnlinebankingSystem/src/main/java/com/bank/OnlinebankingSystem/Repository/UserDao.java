package com.bank.OnlinebankingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.OnlinebankingSystem.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
    List<User> findByEmailIdAndPassword(String email, String password);

    User findByEmailId(String email);
    
    Optional<User> findById(Long id);

    //update password

    //update details

}
