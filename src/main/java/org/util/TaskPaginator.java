package org.util;


import lombok.Data;
import org.Task;

import java.util.List;

@Data
public class TaskPaginator implements ProducerPageable {

    private int actualPage = 1;
    private int countPage;
    private DAO dao;

    public TaskPaginator() {
        setDao(new DAO());
        setCountPage(dao.getCountPageTask());
    }

    public boolean hasNext() {
        return actualPage - 1 != countPage;
    }

    public List<Task> getTasks() {
        List<Task> taskList = dao.getTaskListWithPagination(actualPage);
        actualPage++;
        return taskList;
    }

    @Override
    public List<Task> getList() {
        return dao.getTaskListWithPagination(actualPage);

    }

    @Override
    public List<Task> nextPage() {
        actualPage++;
        return dao.getTaskListWithPagination(actualPage);
    }

    @Override
    public int getCountPage(){
        return this.countPage;
    }

    @Override
    public int getActualePage() {
        return this.actualPage;
    }
}
