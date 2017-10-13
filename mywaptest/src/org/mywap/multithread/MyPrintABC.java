package org.mywap.multithread;

/**
 * Created by s on 2017/10/13.
 */
public class MyPrintABC implements Runnable{
    private String name;
    private String prev;
    private String self;

    public  MyPrintABC(String name,String prev ,String self){
        this.name=name;
        this.prev=prev;
        this.self=self;
    }

    @Override
    public void run() {
        int count=10;
        while (count>0){
            synchronized (prev){
                synchronized (self){
                    count--;
                    System.out.println(name);
                    self.notify();
                    Thread.currentThread().interrupt();
                }
                try {
                    //等待被通知后在去争
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static final void Main(String[] args) throws InterruptedException {
        MyPrintABC a = new MyPrintABC("A", "C", "A");
        MyPrintABC b = new MyPrintABC("B", "A", "B");
        MyPrintABC c = new MyPrintABC("C", "B", "C");

        new Thread(a).start();
        Thread.sleep(100);
        new Thread(b).start();
        Thread.sleep(100);
        new Thread(c).start();
        Thread.sleep(100);


    }
}
