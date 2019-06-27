package com.java.test.Wx;

/**
 * @Auther: xulei
 * @Date: 2019/6/22 0022 11:05
 * @Description:
 */
public class Test01 {


    public static void main(String[] args) {
//
//        int a=3;
//        int b=a/100;
//        System.out.println(b);
//        System.out.println(b+1);
        int len=5;
        int temp;

        for (int i = 0; i < len; i++) {



            temp=10000*i+1;
            if(i==0){
                temp=0;
            }

            String sql="select openid from table limit   "+temp+", 10000";


            System.out.println(sql);
        }

    }

}
