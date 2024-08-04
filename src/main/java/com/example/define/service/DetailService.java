package com.example.define.service;

import com.example.define.Entity.Detail;
import com.example.define.repo.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(@Qualifier("detailRepository") DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    // INSERT
    public Detail saveDetail(Detail detail) {
        return detailRepository.save(detail);
    }

    // SELECT *
    public List<Detail> allDetail() {
        return detailRepository.findAll();
    }

    // SELECT
    public Optional<Detail> getDetailByUserCode(int userCode) {

        return detailRepository.findByuserCode(userCode);
    }

}
