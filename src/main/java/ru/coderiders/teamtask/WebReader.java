package ru.coderiders.teamtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebReader extends Reader {
    private Document doc;

    @Override
    public void read() throws IOException {
        this.doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
        Elements elems = this.doc.select("table.data").select("tr");
        elems.remove(0);

        for (Element i : elems){
            ParamsContainer params = new ParamsContainer();
            params.name = i.child(3).text();
            params.ratio = Double.parseDouble(i.child(4).text().replaceAll(",", "."));
            params.quantity = Integer.parseInt(i.child(2).text());
            this.data.put(i.child(1).text(), params);
        }

        ParamsContainer rus = new ParamsContainer();
        rus.name = "Российский рубль";
        rus.ratio = 1.0;
        rus.quantity = 1;
        this.data.put("RUS", rus);
    }
}
