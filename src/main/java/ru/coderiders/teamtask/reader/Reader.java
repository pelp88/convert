package ru.coderiders.teamtask.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Reader {
    public HashMap<String, CurrencyData> data = new HashMap<>();
    public static class CurrencyData {
        public CurrencyData(){}

        public CurrencyData(Map<String, Object> map){
            this.name = (String) map.get("name");
            this.ratio = (Double) map.get("ratio");
            this.quantity = (Integer) map.get("quantity");
        }

        public String name;
        public Double ratio;
        public Integer quantity;
    }

    public abstract void read() throws IOException;
    public Map<String, CurrencyData> getData(){
        return this.data;
    }
}
