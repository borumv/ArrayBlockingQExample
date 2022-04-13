package org;

import com.google.common.collect.MinMaxPriorityQueue;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

@Data
public class Consumer<T extends PrirorityTask> {

    private PriorityBlockingQueue<T> arrayBlockingQueue;
    private List<T> arrayList;
    private Object object = new Object();
    static Random random = new Random(15);

    public Consumer(PriorityBlockingQueue<T> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    public void consumeQueue() throws InterruptedException {
        try {
            Thread.sleep(20);
            System.out.println(Thread.currentThread().getName() + " work with task -  " + arrayBlockingQueue.take());
            doSmth();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
        }

    }

    public static void doSmth() {
        System.out.println("Result - " + Thread.currentThread().getName() + " - " + (random.nextInt() * random.nextInt() + 15444 - random.nextInt()));
    }
}
