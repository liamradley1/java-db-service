package com.example.funddbservice.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtils {
    public static <T> List<T> getListFromIterator(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }
}
