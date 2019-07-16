package com.java.test.Wx;

/**
 * @Auther: xulei
 * @Date: 2019/6/19 0019 15:13
 * @Description:
 */
public class Msg {


    //消息类型
    private String msgType;

    //发送内容
    private String content;

    //群组标志
    private String receiver_tag;

    //当前群组下所有人数
    private int count;

    //素材标志
    private String mediaId;


    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReceiver_tag() {
        return receiver_tag;
    }

    public void setReceiver_tag(String receiver_tag) {
        this.receiver_tag = receiver_tag;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", receiver_tag='" + receiver_tag + '\'' +
                ", count=" + count +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
