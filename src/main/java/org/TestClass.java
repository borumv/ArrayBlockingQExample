package org;

import com.google.common.collect.MinMaxPriorityQueue;
import org.util.TaskPaginator;
import java.util.concurrent.*;

public class TestClass {
    public static final int LIMITQUE = 5;
    public static void main(String[] args) throws InterruptedException {

        TaskPaginator taskPaginator = new TaskPaginator();
        PriorityBlockingQueue <Task> taskPriorityBlockingQueue = new PriorityBlockingQueue<>(LIMITQUE,TaskComporator.AMOUNT);
        Producer<Task> taskProducer = new Producer<>(taskPriorityBlockingQueue, taskPaginator);
        Consumer<Task> taskConsumer = new Consumer<>(taskPriorityBlockingQueue);

        //Producer
        new Thread(() -> {
            boolean work = true;
            while (taskPaginator.hasNext()) {
                System.out.println("Таски " + (taskPaginator.getActualPage()) + " cтраницы:");
                try {
                    taskProducer.produceQue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //Consumers
        Thread consumer1 = new Thread(() -> {
            while (taskPaginator.hasNext()) {
                try {
                    Thread.sleep(100);
                    taskConsumer.consumeQueue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer2 = new Thread(() -> {
            while (taskPaginator.hasNext()) {
                try {
                    Thread.sleep(100);
                    taskConsumer.consumeQueue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        consumer1.start();
        consumer2.start();
        consumer1.join();
        consumer2.join();

        System.out.println(consumer1.getState());
        System.out.println(consumer2.getState());

    }
}
