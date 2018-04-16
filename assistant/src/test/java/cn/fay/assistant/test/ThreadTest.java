package cn.fay.assistant.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/15 上午10:27.
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("thread 1");
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 2");
                try {
                    wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            System.out.println("thread 3");
        });
        Thread.sleep(100);
        t2.start();
//        t2.wait();
        t1.start();
//        t1.wait();
        t3.start();
    }
}
