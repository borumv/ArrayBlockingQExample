package org.Deprecate;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class LimitPQ <T> extends PriorityBlockingQueue {

    private int maxItems;
    private Object observe;
    ReentrantLock lock;

    public LimitPQ(int maxItems, Comparator comparator, Object observe){
        super(maxItems, comparator);
        this.maxItems = maxItems;
        this.observe = observe;


    }


}