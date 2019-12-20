package com.dsc.springboot.dao;

/**
 * @author DSC
 * @description 返回结果集
 * @date 2019/12/19 19:31
 */
public class ResponseDao {
    int code = 0;
    Object data = "";
    String msg = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", data:'" + data + '\'' +
                ", msg:'" + msg + '\'' +
                '}';
    }
}
