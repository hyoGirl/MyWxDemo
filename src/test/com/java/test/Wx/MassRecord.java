package com.java.test.Wx;

import java.util.Date;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 14:55
 * @Description:
 */
public class MassRecord {

    private  int id;

    //接受对象  1 全部  2 群组  3 标签
    private int receiver;


    //总接受人数
    private int count;

    //消息类型
    private String msgType;


    //素材标志
    private String mediaId;

    //原创校验
    private int checkState;

    //预发送时间
    private Date  sendTime;

    //勾选48小时活跃
    private int active;

    // 是否立即发送
    private int sendNow;


    private int org_id;
    private int create_user_id;

    private Date create_time;
    private Date update_time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
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


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "MassRecord{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", count=" + count +
                ", msgType='" + msgType + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", checkState=" + checkState +
                ", sendTime=" + sendTime +
                ", active=" + active +
                ", sendNow=" + sendNow +
                ", org_id=" + org_id +
                ", create_user_id=" + create_user_id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
