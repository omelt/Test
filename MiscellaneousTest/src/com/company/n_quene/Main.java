package com.company.n_quene;

import static java.lang.Math.abs;

public class Main {

    private static int[] a=new int[21];

    private static int n=8;

    private static int sum=0;

    static boolean Try(int x,int y)			//测试x行y列可否摆放棋子，成功返回1，否则返回0
    {
        int j=1;
        while(j<x)					//与数组中已放好的数比较
        {
            if((a[j] == y) || (abs(x-j) == abs(y-a[j]))) //在同一列或者在同一斜线
                return false;
            ++j;					//右移一格继续尝试
        }
        return true;
    }

    static void place(int x)				//递归函数
    {

        int y;
        if(x>n)						//棋子第n行已摆好，则打印成功方案
        {
            sum++;
        }
        else
        {
            for(y=1;y<=n;++y)		//该行棋子依次从左向右移
                if(Try(x,y))		//如果可以摆放
                {
                    a[x]=y;			//给a[x]赋值
                    place(x+1);		//继续下一行的递归

                }
        }
    }

    public static void main(String[] args) {
        for (int i = 4; i <=14; i++) {
            sum=0;n=i;
            long before=System.currentTimeMillis();
            place(1);
            long after=System.currentTimeMillis();
            System.out.println(n+" : "+sum+"   Time:"+(after-before));
        }
    }
}
