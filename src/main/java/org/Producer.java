package org;

import com.google.common.collect.MinMaxPriorityQueue;
import lombok.Data;
import org.util.ProducerPageable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

@Data
public class Producer<T extends PrirorityTask> {


    private List<T> arrayList;
    private ProducerPageable<T> pageableController;
    private PriorityBlockingQueue<T> priorityBlockingQueue;

    public Producer(PriorityBlockingQueue<T> arrayBlockingQueue, ProducerPageable<T> producerPageable) {
        this.priorityBlockingQueue = arrayBlockingQueue;
        this.pageableController = producerPageable;
    }

    public void produceQue() throws InterruptedException {
        List<T> tList = pageableController.getList();
        List<T> workingList = new ArrayList<>(tList);
        while (pageableController.hasNext()) {
            if (pageableController.getActualePage() == pageableController.getCountPage()) {
                for (T t : tList) {
                    priorityBlockingQueue.put(t);
                    while (priorityBlockingQueue.size() == TestClass.LIMITQUE) {
                        Thread.sleep(50);
                    }
                    System.out.println("SIZE ARRAY: " + priorityBlockingQueue.size());
                }
            } else {
                for (int i = 0; i < tList.size() - 2; i++) {
                    priorityBlockingQueue.put(tList.get(i));
                    while (priorityBlockingQueue.size() == TestClass.LIMITQUE) {
                        Thread.sleep(50);
                    }
                    System.out.println("SIZE ARRAY: " + priorityBlockingQueue.size());
                    workingList.remove(tList.get(i));
                }
            }
            workingList.addAll(pageableController.nextPage());
            tList.clear();
            tList.addAll(workingList);
        }
    }


}

