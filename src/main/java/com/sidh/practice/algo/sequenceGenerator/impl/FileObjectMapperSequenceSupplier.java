package com.sidh.practice.algo.sequenceGenerator.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidh.practice.algo.sequenceGenerator.abs.ISequenceSupplier;
import com.sidh.practice.algo.sequenceGenerator.interfaces.ISequenceStore;

import java.util.LinkedList;
import java.util.List;

public class FileObjectMapperSequenceSupplier extends ISequenceSupplier {
    private FileSequenceStore store = new FileSequenceStore();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected List<List<Integer>> parse(ISequenceStore store) {
        String sequence = store.getSequence();
        try {
            return objectMapper.readValue(sequence, new TypeReference<LinkedList<List<Integer>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ISequenceStore getStore() {
        return store;
    }

    @Override
    public void accept(List<List<Integer>> integers) {
        try {
            store.setSequence(objectMapper.writeValueAsString(integers));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
