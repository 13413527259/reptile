package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Seller;

@Repository
public interface SellerDao extends JpaRepository<Seller, Integer>{

}
