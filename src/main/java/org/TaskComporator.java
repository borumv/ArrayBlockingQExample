package org;

import java.util.Comparator;

public enum TaskComporator implements Comparator<Task> {
    AMOUNT {
        @Override
        public int compare(Task o1, Task o2) {


            return o1.getPriority() < o2.getPriority() ? 1:(o1.getPriority() > o2.getPriority() ? -1 : 0);
        }
    }
    }