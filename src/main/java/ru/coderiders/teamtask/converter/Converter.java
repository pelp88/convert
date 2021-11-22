package ru.coderiders.teamtask.converter;

import java.text.DecimalFormat;

public class Converter {
    private Double ratio;
    private Integer quantity;
    private DecimalFormat format = new DecimalFormat("#.##");

    public Double convert(Double value){
        return
                Double.parseDouble(format.format((value * this.ratio) / this.quantity)
                        .replaceAll(",", "."));
    }

    public Double reverseConvert(Double value){
        return
                Double.parseDouble(format.format((value * this.quantity) / this.ratio)
                        .replaceAll(",", "."));
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
