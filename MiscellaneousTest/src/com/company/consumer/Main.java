package com.company.consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{  //生产者和消费者都要操作的资源

    private String name;
    private int count=1;
    private boolean flag=false;

    /*
    此时代码,但是如果有多个生产者和多个消费者，上面的代码是有问题,因为
    可能出现生产的1个商品生产了一次而被消费了2次，或者连续生产2个商品而只
    有1个被消费，这是因为此时共有4个线程在操作Resource对象r,  而notify()唤醒的
    是线程池中第1个wait()的线程，所以生产者执行notify()时，唤醒的线程
    有可能是另1个生产者线程，这个生产者线程从wait()中醒来后不会再判
    断flag，而是直接向下运行打印出一个新的商品，这样就出现了连续生产2个商品。
     */

    //将上下两个if改为while    notify改为notifyall
    public synchronized void set(String name){
        while (flag)
            try{wait();}catch(Exception e){}
        flag=true;
        this.name=name+"---"+count++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
        this.notifyAll();
    }

    public synchronized void out(){
        while(!flag)
            try{wait();}catch(Exception e){}
        flag=false;
        System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
        this.notifyAll();
    }
}
//class Resource{
//    private String name;
//    private int count=1;
//    private boolean flag=false;
//    private Lock lock = new ReentrantLock();/*Lock是一个接口，ReentrantLock是该接口的一个直接子类。*/
//    private Condition condition_pro=lock.newCondition(); /*创建代表生产者方面的Condition对象*/
//    private Condition condition_con=lock.newCondition(); /*使用同一个锁，创建代表消费者方面的Condition对象*/
//
//    public void set(String name){
//        lock.lock();//锁住此语句与lock.unlock()之间的代码
//        try{
//            while(flag)
//                condition_pro.await(); //生产者线程在conndition_pro对象上等待
//            this.name=name+"---"+count++;
//            System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
//            flag=true;
//            condition_con.signalAll();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally{
//            lock.unlock(); //unlock()要放在finally块中。
//        }
//    }
//    public void out(){
//        lock.lock(); //锁住此语句与lock.unlock()之间的代码
//        try{
//            while(!flag)
//                condition_con.await(); //消费者线程在conndition_con对象上等待
//            System.out.println(Thread.currentThread().getName()+"...消费者..."+this.name);
//            flag=false;
//            condition_pro.signalAll(); /*唤醒所有在condition_pro对象下等待的线程，也就是唤醒所有生产者线程*/
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally{
//            lock.unlock();
//        }
//    }
//}
class Producer implements Runnable{
    private Resource res;
    Producer(Resource res){
        this.res=res;
    }
    public void run(){
        while(true){
            res.set("商品");
        }
    }
}

class Consumer implements Runnable{
    private Resource res;
    Consumer(Resource res){ this.res=res;
    }
    public void run(){
        while(true){
            res.out();
        }
    }
}

public class Main{
    public static void main(String[] args){
        Resource r=new Resource();

        Producer pro1=new Producer(r);
        Producer pro2=new Producer(r);
        Producer pro3=new Producer(r);

        Consumer con1=new Consumer(r);
        Consumer con2=new Consumer(r);
        Consumer con3=new Consumer(r);

        //--

        new Thread(pro1).start();
        new Thread(pro2).start();
        new Thread(pro3).start();
        new Thread(con1).start();
        new Thread(con2).start();
        new Thread(con3).start();

    }
}//运行结果正常，生产者生产一个商品，紧接着消费者消费一个商品。
