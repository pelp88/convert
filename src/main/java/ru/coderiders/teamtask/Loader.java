package ru.coderiders.teamtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Loader {
    private Document doc;
    public Map<String, LinkedList<String>> ratios = new HashMap<>();

    public Loader() {
        int status = 1;
        while (status != 0) {
            status = this.getPage();
        }
        this.parse();
    }

    public Double convert(String currency1, String currency2, double value){
        if (Objects.equals(currency1, "RUB")){
            return value * this.getConvertingRatio(currency2) / this.getCurrencyQuantity(currency2);
        } else if (currency2.equals("RUB")){
            return value * this.getConvertingRatio(currency1) / this.getCurrencyQuantity(currency1);
        } else if (currency1.equals(currency2)){
            return value;
        } else {
            Double subValue = value * this.getConvertingRatio(currency1) / this.getCurrencyQuantity(currency1);
            return subValue * this.getConvertingRatio(currency2) / this.getCurrencyQuantity(currency2);
        }
    }

    public Double getConvertingRatio(String key){
        return Double.parseDouble(ratios.get(key).get(1));
    }

    public String getCurrencyName(String key){
        return ratios.get(key).get(0);
    }

    public Integer getCurrencyQuantity(String key){
        return Integer.parseInt(ratios.get(key).get(2));
    }

    public Set<String> getCurrencies() {
        return ratios.keySet();
    }

    private void parse() {
        Elements pool = this.doc.select("table.data").select("tr");
        pool.remove(0);
        LinkedList<String> first = new LinkedList<>(Arrays.asList("Российский рубль", "1", "1"));
        ratios.put("RUB", first);

        for (Element i : pool){
            LinkedList<String> value = new LinkedList<>();
            value.add(i.child(3).text());
            value.add(i.child(4).text().replaceAll(",", "."));
            value.add(i.child(2).text());
            ratios.put(i.child(1).text(), value);
        }

        System.out.println("Page parsed!");
    }

    private int getPage() {
        try {
            this.doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
            System.out.println("Page downloaded!");
            return 0;
        } catch (IOException exception) {
            System.out.println("Smth went wrong, repeating...");
            return 1;
        }
    }
}
