package com.lucasmoraist.devideosapi.category.domain;

public enum ColorsEnum {
    VERMELHO("Vermelho"),
    AMARELO("Amarelo"),
    AZUL("Azul"),
    VERDE("Verde"),
    ROSA("Rosa"),
    PRETO("Preto"),
    CINZA("Cinza"),
    ROXO("Roxo");

    private String color;

    public String getColor() {
        return color;
    }

    ColorsEnum(String color){
        this.color = color;
    }
}
