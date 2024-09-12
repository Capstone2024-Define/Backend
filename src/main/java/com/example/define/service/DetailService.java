package com.example.define.service;

import com.example.define.Entity.Detail;
import com.example.define.vo.DetailVo;
import com.example.define.mapper.DetailMapper;
import com.example.define.repo.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    private final DetailRepository detailRepository;
    private final DetailMapper detailMapper;


    @Autowired
    public DetailService(@Qualifier("detailRepository") DetailRepository detailRepository,
                         @Qualifier("detailMapper") DetailMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;

    }

    /*
    @Autowired
    public DetailService(@Qualifier("detailRepository") DetailRepository detailRepository) {
        this.detailRepository = detailRepository;

    }

     */

    // INSERT
    public Detail saveDetail(Detail detail) {
        return detailRepository.save(detail);
    }

    // INSERT : xml로
    public void createDetail(DetailVo detailVo) {
        detailMapper.insertDetail(detailVo);
    }

    // SELECT
    // SELECT *
    public List<Detail> allDetail() {
        return detailRepository.findAll();
    }

    public Optional<Detail> getDetailByUserCode(int userCode) {

        return detailRepository.findByuserCode(userCode);
    }

    public DetailVo getDetailByUserCodeAndDate(int userCode, Date date) {
        return detailMapper.getDetailByUserCodeAndDate(userCode, date);
    }

    public DetailVo showDetailByDate(Date date) {
        return detailMapper.getDetailByDate(date);
    }

    public DetailVo showDetailByUserCode(int userCode) {
        return detailMapper.getDetailByUserCode(userCode);
    }


}
