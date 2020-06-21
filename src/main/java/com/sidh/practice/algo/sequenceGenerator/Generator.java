package com.sidh.practice.algo.sequenceGenerator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Generator {
    public static final int sequenceStart = 0;

    private Supplier<List<List<Integer>>> sequenceSupplier;
    private Consumer<List<List<Integer>>> sequenceChangeCallback;

    public Generator(Supplier<List<List<Integer>>> sequenceSupplier, Consumer<List<List<Integer>>> sequenceChangeCallback) {
        this.sequenceSupplier = sequenceSupplier;
        this.sequenceChangeCallback = sequenceChangeCallback;
    }

    public int generate() {
        List<List<Integer>> sequenceList = sequenceSupplier.get();

        int sequenceFound;
        if (sequenceList.isEmpty())
            sequenceList.add(Collections.singletonList(sequenceStart));

        List<Integer> sequencePair = sequenceList.get(0);

        sequenceFound = sequencePair.get(0);

        if (sequencePair.size() == 1)
            sequenceList.remove(0);
        else if (sequencePair.size() == 2) {
            if (sequenceFound + 1 == sequencePair.get(1))
                sequencePair.remove(0);
            else
                sequencePair.set(0, sequenceFound + 1);
        }

        if (sequenceList.isEmpty())
            sequenceList.add(Collections.singletonList(sequenceFound + 1));

        new Thread(() -> sequenceChangeCallback.accept(sequenceList)).start();
        return sequenceFound;
    }

    public void delete(int id) {
        List<List<Integer>> sequenceList = sequenceSupplier.get();

        ListIterator<List<Integer>> iterator = sequenceList.listIterator();
        while (iterator.hasNext()) {
            boolean hadPrevious = iterator.hasPrevious();
            List<Integer> sequencePair = iterator.next();
            if (id <= sequencePair.get(sequencePair.size() - 1)) {
                if (id < sequencePair.get(0)) {
                    iterator.previous();
                    iterator.add(new ArrayList<>(Arrays.asList(id)));
                    if (hadPrevious) {
                        iterator.previous();
                        List<Integer> prevPair = iterator.previous();
                        iterator.next();
                        iterator.next();
                        if (prevPair.get(prevPair.size() - 1) == id - 1) {
                            if (prevPair.size() == 2)
                                prevPair.set(1, id);
                            else
                                prevPair.add(id);
                            iterator.remove();
                        }
                    }
                    List<Integer> nextPair = iterator.next();
                    iterator.previous();
                    List<Integer> prevPair = iterator.previous();
                    if (id + 1 == nextPair.get(0)) {
                        int nearest = prevPair.get(0);
                        int farthest = nextPair.get(nextPair.size() - 1);
                        nextPair.clear();
                        nextPair.add(nearest);
                        nextPair.add(farthest);
                        iterator.next();
                        iterator.remove();
                    } else
                        iterator.next();
                }
                break;
            }
        }
        List<Integer> lastPair = sequenceList.get(sequenceList.size() - 1);
        if (lastPair.size() == 2) {
            int firstElem = lastPair.get(0);
            lastPair.clear();
            lastPair.add(firstElem);
        }

        new Thread(() -> sequenceChangeCallback.accept(sequenceList)).start();
    }
}
