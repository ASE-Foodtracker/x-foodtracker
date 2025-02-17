package de.jmf.application.ports;

import java.util.List;

public interface DataWriter {
    void saveAll(List<String[]> input);
    void clear();
}
