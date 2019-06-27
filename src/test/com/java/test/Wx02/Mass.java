package com.java.test.Wx02;

import java.util.Date;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 20:01
 * @Description:
 */
public class Mass {


    private String name;
    private int receiver;
    private String receiver_tag;
    private int count;
    private String msgType;
    private int sendStatus;
    private Date sendTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getReceiver_tag() {
        return receiver_tag;
    }

    public void setReceiver_tag(String receiver_tag) {
        this.receiver_tag = receiver_tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Mass{" +
                "name='" + name + '\'' +
                ", receiver=" + receiver +
                ", receiver_tag='" + receiver_tag + '\'' +
                ", count=" + count +
                ", msgType='" + msgType + '\'' +
                ", sendStatus=" + sendStatus +
                ", sendTime=" + sendTime +
                '}';
    }
}
