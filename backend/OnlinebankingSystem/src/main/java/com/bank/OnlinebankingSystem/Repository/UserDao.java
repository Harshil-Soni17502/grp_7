package com.bank.OnlinebankingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.OnlinebankingSystem.Entity.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
    List<User> findByEmailIdAndPassword(String email, String password);

    User findByEmailId(String email);

    //update password

    //update details

}
