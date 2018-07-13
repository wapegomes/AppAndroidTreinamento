package br.com.wpgomes.treinamento.appcasttreinamento.model;


import java.util.List;

/**
 * Created by wgomes on 11/07/16.
 */

public class MarvelCollection<T> {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<T> results;

    @Override
    public String toString() {
        return "MarvelCollection{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", total=" + total +
                ", count=" + count +
                ", results=" + results +
                '}';
    }
}