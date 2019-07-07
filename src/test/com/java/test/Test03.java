package com.java.test;

/**
 * @author ashura1110
 * @ClassName XULEI
 * @description TODO
 * @Date 2019/7/2 23:18
 * @Version 1.0
 */
public class Test03 {

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {

            show(i);
        }

        for (int i = 0; i < 5; i++) {

            if(i==3){
                return;
            }
            System.out.println("i的值是" + i);
        }

    }

    private static void show(int i) {

        if (i==3){
            return;
        }



        ;
    }

}
