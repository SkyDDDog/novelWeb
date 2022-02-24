package com.lyd.utills;

public enum ResultCommon {
    LOGIN_SUCCESS(701,"登录成功"),
    UNKNOW_ACCOUNT(702,"用户名错误"),
    INCORRECT_CREDENTIALS(703,"密码错误"),
    REG_ADMIN(601,"成功注册为管理员"),
    REG_USER(602,"成功注册为普通用户"),
    REG_SAMEUSERNAME(603,"用户名冲突"),
    REG_FAIL(604,"注册失败"),
    ADD_SUCCESS(801,"添加成功"),
    ADD_FAILURE(802,"添加失败"),
    ADDNOVEL_SUCCESS(901,"审核成功"),
    ADDNOVEL_UNPASS(902,"审核不通过"),
    ADDNOVEL_FAILURE(903,"添加失败");

    private int StateCode;      //状态码
    private String StateDec;    //状态描述

    private ResultCommon(int stateCode, String stateDecs){
        this.StateCode = stateCode;
        this.StateDec = stateDecs;
    }

    public int getStateCode() {
        return StateCode;
    }

    public void setStateCode(int stateCode) {
        StateCode = stateCode;
    }

    public String getStateDec() {
        return StateDec;
    }

    public void setStateDec(String stateDec) {
        StateDec = stateDec;
    }



}
