package ru.coderiders.teamtask.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Reader {
    public HashMap data = new HashMap<>();
    public static class CurrencyData {
        public String name;
        public Double ratio;
        public Integer quantity;
    }

    public abstract void read() throws IOException;
    public Map<String, CurrencyData> getData(){
        return this.data;
    }
}
