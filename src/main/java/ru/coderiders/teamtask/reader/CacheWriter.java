package ru.coderiders.teamtask.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CacheWriter { // not used yet
    public static void write(Map<String, Reader.CurrencyData> data) throws IOException {
        FileWriter writer = new FileWriter("/Users/pelp88/teamtask/cache.json");
        String json = new ObjectMapper().writeValueAsString(data);
        writer.write(json);
        writer.close();
    }
}
