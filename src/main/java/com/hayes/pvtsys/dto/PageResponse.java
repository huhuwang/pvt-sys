package com.hayes.pvtsys.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponse<T> implements Serializable {

    private int currentPage;

    private int totalPage;

    private int totalRecordNum;

    private List<T> data;

    public PageResponse(Page<T> page) {
       this.currentPage = page.getNumber() + 1;
       this.totalPage = page.getTotalPages();
       this.totalRecordNum = ((Long)page.getTotalElements()).intValue();
       this.data = page.getContent();
    }

    public PageResponse(Integer currentPage, Integer totalPage, Integer totalRecordNum, List<T> data) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalRecordNum = totalRecordNum;
        this.data = data;
    }
}
