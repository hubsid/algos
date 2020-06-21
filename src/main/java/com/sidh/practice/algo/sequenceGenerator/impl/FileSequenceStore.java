package com.sidh.practice.algo.sequenceGenerator.impl;

import com.sidh.practice.algo.sequenceGenerator.interfaces.ISequenceStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileSequenceStore implements ISequenceStore {
    public static final String filePath = "src/main/resources/algo/sequenceGenerator/storageFile";

    public String getSequence() {
        try {
            return Files.readAllLines(Paths.get(filePath))
                    .stream()
                    .collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSequence(String sequence) {
        try {
            Files.write(Paths.get(filePath), sequence.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
