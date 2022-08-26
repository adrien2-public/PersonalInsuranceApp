package com.example.demo.repositories;


import com.example.demo.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepo extends JpaRepository<Transfer, String> {



    @Query("SELECT c FROM Transfer c WHERE c.sendingAccountNumber = :senderId  OR   c.receivingAccountNumber = :receiverId ")
    List<Transfer> findBySenderOrReceiverId(@Param("senderId")String senderId, @Param("receiverId")String receiverId);


}
