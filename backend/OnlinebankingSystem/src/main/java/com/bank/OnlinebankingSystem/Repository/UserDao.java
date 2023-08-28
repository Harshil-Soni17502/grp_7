package com.bank.OnlinebankingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bank.OnlinebankingSystem.Entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
    List<User> findByEmailIdAndPassword(String email, String password);

    User findByEmailId(String email);
    
    Optional<User> findById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.emailId = :emailId")
    void updatePasswordByEmail(@Param("emailId") String email, @Param("password") String newPassword);

    //update password

    //update details

}
