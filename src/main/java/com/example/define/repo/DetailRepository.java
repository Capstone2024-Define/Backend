package com.example.define.repo;

import com.example.define.Entity.Detail;
import com.example.define.Entity.DetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface DetailRepository extends JpaRepository<Detail, DetailPK> {
public interface DetailRepository extends JpaRepository<Detail, DetailPK> {
    Optional<Detail> findByuserCode(int userCode);
}