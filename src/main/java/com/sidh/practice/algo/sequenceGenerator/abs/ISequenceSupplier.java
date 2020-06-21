package com.sidh.practice.algo.sequenceGenerator.abs;

import com.sidh.practice.algo.sequenceGenerator.interfaces.ISequenceStore;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ISequenceSupplier implements Supplier<List<List<Integer>>>, Consumer<List<List<Integer>>> {
    @Override
    public List<List<Integer>> get() {
        return parse(getStore());
    }

    protected abstract List<List<Integer>> parse(ISequenceStore store);

    protected abstract ISequenceStore getStore();
}
