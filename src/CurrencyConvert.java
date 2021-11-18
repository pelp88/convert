import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConvert {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> currencyCode = new HashMap<>();

        currencyCode.put(1, "USD");
        currencyCode.put(2, "RUB");
        currencyCode.put(3, "EUR");
        currencyCode.put(4, "BYN");
        currencyCode.put(5, "KZT");
        currencyCode.put(6, "UAH");

        String fromCur, toCur = null;
        double amount;
        Scanner scan = new Scanner(System.in);
        System.out.println("Выберите валюты которую нужно конвертировать:");
        System.out.println("1:USD\n2:RUB\n3:EUR\n4:BYN\n5:KZT\n6:USH");
        fromCur = currencyCode.get(scan.nextInt());
        System.out.println("Выберите валюты В которую нужно конвертировать:");
        System.out.println("1:USD\n2:RUB\n3:EUR\n4:BYN\n5:KZT\n6:USH");
        toCur = currencyCode.get(scan.nextInt());
        System.out.println("Введите суммы для конвертации:");
        amount = scan.nextDouble();
        sendHttpGetMes(fromCur, toCur, amount);


    }

    public static void sendHttpGetMes(String fromCur, String toCur, double amount) throws IOException {
        DecimalFormat f = new DecimalFormat("00.00");
        String GET_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=4824ae928d85f608da8e83483d576c9c&from=" + toCur + "&to=" + fromCur;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int respCode = httpURLConnection.getResponseCode();
        if (respCode == HttpURLConnection.HTTP_OK) {
            BufferedReader buf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = buf.readLine()) != null) {
                response.append(input);
            }
            buf.close();
            JSONObject obj = new JSONObject(response.toString());
            Double exchangeRate = obj.getJSONObject("rates").getDouble(fromCur);
            //System.out.println(obj.getJSONObject("rates"));
            System.out.println(exchangeRate);
            System.out.println();
            System.out.println(f.format(amount) + " " + fromCur + " = " + f.format(amount / exchangeRate) + " " + toCur);
        } else {
            System.out.println("ERROR GET request!");
        }


    }
}
