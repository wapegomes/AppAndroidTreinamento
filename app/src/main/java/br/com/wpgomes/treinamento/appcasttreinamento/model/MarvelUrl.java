package br.com.wpgomes.treinamento.appcasttreinamento.model;


import java.io.Serializable;

public class MarvelUrl implements Serializable {

    public String type;
    public String url;

    @Override
    public String toString() {
        return "MarvelUrl{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
