package ru.coderiders.teamtask.reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CacheReader extends Reader{ // not used yet
    @Override
    public void read() throws IOException {
        String contents = Files.readString(
                Paths.get("./cache.json"), StandardCharsets.UTF_8);

        Map<String, Map> data = new ObjectMapper().readValue(contents, HashMap.class);
        this.data = this.toCurrencyData(data);
    }

    private HashMap<String, Reader.CurrencyData> toCurrencyData(Map<String, Map> data) {
        HashMap<String, Reader.CurrencyData> output = new HashMap<>();

        for (var item : data.entrySet()) {
            output.put(item.getKey(), new Reader.CurrencyData(item.getValue()));
        }

        return output;
    }
}
