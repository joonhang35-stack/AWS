package com.bcs.zsg.apiresponse.vo;

import java.util.List;

public class CRMClaimedVoucherResponseVO {

    private String status;
    private String timestamp;
    private String message;
    private Integer totalCount;
    private List<CRMVoucherVO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<CRMVoucherVO> getData() {
        return data;
    }

    public void setData(List<CRMVoucherVO> data) {
        this.data = data;
    }

    }