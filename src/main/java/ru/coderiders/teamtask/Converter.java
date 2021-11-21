package ru.coderiders.teamtask;

public class Converter {
    private Double ratio;
    private Integer quantity;

    public Double convert(Double value){
        return (value * this.ratio) / this.quantity;
    }

    public Double reverseConvert(Double value){
        return (value * this.quantity) / this.ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
