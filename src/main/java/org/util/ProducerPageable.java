package org.util;

import java.util.List;

public interface ProducerPageable <T>{
   List<T> getList();
   List<T> nextPage();
   int getCountPage();
   int getActualePage();
   boolean hasNext();
}
