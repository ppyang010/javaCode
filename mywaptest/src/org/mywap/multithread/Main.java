package org.mywap.multithread;

class Thread1 extends Thread{  
    private String name;  
    public Thread1(String name) {  
       this.name=name;  
    }  
    public void run() {  
        for (int i = 0; i < 5; i++) {  
            System.out.println(name + "运行  :  " + i);  
            try {  
                sleep((int) Math.random() * 10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
         
    }  
}  
public class Main {  
  
    public static void main(String[] args) {  
        Thread1 mTh1=new Thread1("A");  
        Thread1 mTh2=new Thread1("B");  
//        mTh1.start();  
//        mTh2.start(); 
        Runnable runnable1 = new Thread2("C"); 
        Runnable runnable2 = new Thread2("D");
        new Thread(runnable1).start();  
        new Thread(runnable2).start(); 
  
    }  
  
}  

class Thread2 implements Runnable{  
    private String name;  
  
    public Thread2(String name) {  
        this.name=name;  
    }  
  
    @Override  
    public void run() {  
          for (int i = 0; i < 5; i++) {  
                System.out.println(name + "运行  :  " + i);  
                try {  
                    Thread.sleep((int) Math.random() * 10);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
          
    }  
      
}  
