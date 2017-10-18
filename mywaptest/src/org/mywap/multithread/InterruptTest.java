package org.mywap.multithread;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by s on 2017/10/13.
 */
public class InterruptTest implements Runnable {

    @Override
    public void run() {
        int count=0;
        while(true){
            System.out.println(count++);
            if(count==100){
                break;
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(100);
    }

//    public static void main(String[] args) {
//        Thread thread = new Thread(new InterruptTest());
//
//        thread.start();
//        thread.start();
////        ThreadLocal
////        try {
////            Thread.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        System.out.println("stop");
////       thread.interrupt();
////        Object object=null;
////        LinkedHashMap map=new LinkedHashMap();
////        new HashMap<String,String>(16);
////        new ConcurrentHashMap<String,String>();
////        new CopyOnWriteArrayList<String>();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService
//
//
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello world !!!");
//            }
//        });

//        Lock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//        lock.lock();
//        try {
//            while(true) {
//                condition.wait();
//            }
//            // ´¦ÀíÂß¼­
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }



//    }


    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }

    public static  void h(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService
    }
}
