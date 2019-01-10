package com.company.Heapsort;

import java.util.Arrays;

public class Main {

    public static void swap(int[] tar,int a,int b) {
        int temp=tar[a];
        tar[a]=tar[b];
        tar[b]=temp;
    }

    public static void heapSortK(int[] tar,int k){

        //先初始化成为堆
        for (int i = tar.length/2-1; i >= 0 ; i--) {
            adjustHeap(tar,i,tar.length);
        }

        int n=tar.length-k;
        for (int i = tar.length-1; i >= n; i--) {

            swap(tar,i,0);

            adjustHeap(tar,0,i);

        }


    }

    public static void heapSort(int[] tar){

        //先初始化成为堆
        for (int i = tar.length/2-1; i >= 0 ; i--) {
            adjustHeap(tar,i,tar.length);
        }


        for (int i = tar.length-1; i >=0 ; i--) {

            swap(tar,i,0);

            adjustHeap(tar,0,i);

        }


    }

    public static void adjustHeap(int[] tar,int now,int count){

        for (int i = 2*now+1; i < count; i = 2*i+1) {

            if(i+1<count && tar[i]<tar[i+1] ){
                i++;
            }

            if(tar[i]>tar[now]){
                swap(tar,now,i);
                now=i;
            }
            else {
                break;
            }

        }
    }



    public static void main(String[] args) {
        int[] array={50,93,44,55,64,14,68,15,10,94,58,33,6,84,82,26,42,29,39,98,26,22,18,24,44,47,80,52,26,51,59,71,35,48,20,83,81,15,23,0,77,50,18,99,72,20,24,21,3,85,37};
//        int[] array1={50,93,44,55,64,14,68,15,10,94,58,33,6,84,82,26,42,29,39,98,26,22,18,24,44,47,80,52,26,51,59,71,35,48,20,83,81,15,23,0,77,50,18,99,72,20,24,21,3,85,37};
//
//
//        Arrays.sort(array1);
//        heapSort(array);
//
//        for (int i = 0; i < array.length; i++) {
//            if(array[i]!=array1[i]){
//                System.out.println(false);
//                break;
//            }
//        }
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i]+" ");
//        }
//
//        System.out.println("\nTarget:");
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array1[i]+" ");
//        }
        int k=4;
        heapSortK(array,k);
        System.out.println(array[array.length-k]);

    }
}
