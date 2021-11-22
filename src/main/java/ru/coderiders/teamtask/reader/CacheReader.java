package ru.coderiders.teamtask.reader;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CacheReader extends Reader{ // not used yet
    @Override
    public void read() throws IOException {
        FileReader reader = new FileReader("./cache.json");
        Scanner scanner = new Scanner(reader);
        reader.close();

        this.data = new ObjectMapper().readValue(scanner.toString(), HashMap.class);
    }
}
