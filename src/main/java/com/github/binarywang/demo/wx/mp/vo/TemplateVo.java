package com.github.binarywang.demo.wx.mp.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: ashur
 * @Date: 2019/6/12 0012 15:52
 * @Description:
 */
@Data
public class TemplateVo implements Serializable{


    private static final long serialVersionUID = -8092557504374744594L;


    private String touser;
    private String template_id;
    private String data;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
