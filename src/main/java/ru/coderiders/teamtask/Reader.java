package ru.coderiders.teamtask;

import java.io.IOException;
import java.util.HashMap;

public abstract class Reader {
    public HashMap<String, ParamsContainer> data = new HashMap<>();
    public static class ParamsContainer {
        public String name;
        public Double ratio;
        public Integer quantity;
    }

    public abstract void read() throws IOException;
    public HashMap<String, ParamsContainer> getData(){
        return this.data;
    }
}
