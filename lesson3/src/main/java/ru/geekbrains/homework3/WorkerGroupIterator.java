package ru.geekbrains.homework3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkerGroupIterator implements Iterator<Worker> {
    private int counter;
    private final List<Worker> freelancers;


    public WorkerGroupIterator(List<Worker> freelancers) {
        this.freelancers = freelancers;
        this.counter = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return counter<freelancers.size();
    }
    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Worker next() {
        if(!hasNext())
        {
            return null;
        }
        //counter++;
        return freelancers.get(counter++);
    }

}
