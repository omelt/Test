package com.company.quicksort;

public class Main {

    public static void printArray(int[] t){
        for (int a: t
             ) {
            System.out.print(a+" ");
        }
        System.out.println();
    }


    public static void qucickSort(int t[],int start,int end){

        if(start>=end) return;

        int low = start,high=end;
        int key=t[low];

        while (low<high){
            while (low<high && t[high]>=key){
                --high;
            }
            t[low]=t[high];
            while (low<high && t[low]<=key){
                ++low;
            }
            t[high]=t[low];
        }
        t[low] = key;

        //递归
        //去掉标志
        qucickSort(t,start,low-1);
        qucickSort(t,low+1,end);
    }

    public static boolean check(int a[],int b[]){
        for (int i = 0; i < a.length; i++) {
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int b[] = {57, 68, 59, 52, 72, 28, 96, 33, 24};

        qucickSort(b,0,b.length-1);

        printArray(b);

    }
}
