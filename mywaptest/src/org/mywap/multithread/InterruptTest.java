package org.mywap.multithread;

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

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptTest());

        thread.start();
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("stop");
       thread.interrupt();

    }
}
