package com.java.test.Wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 15:09
 * @Description:
 */
public class MassDto {

    //接受对象
    private int receiver;


    //原创校验
    private int checkState;

    //预发送时间
    private Date sendTime;

    //勾选48小时活跃
    private int active;

    // 是否立即发送
    private int sendNow;

    private List<Msg> msgs=new ArrayList<Msg>();

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }


    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getSendNow() {
        return sendNow;
    }

    public void setSendNow(int sendNow) {
        this.sendNow = sendNow;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }

    @Override
    public String toString() {
        return "MassDto{" +
                "receiver=" + receiver +
                ", checkState=" + checkState +
                ", sendTime=" + sendTime +
                ", active=" + active +
                ", sendNow=" + sendNow +
                ", msgs=" + msgs +
                '}';
    }
}
