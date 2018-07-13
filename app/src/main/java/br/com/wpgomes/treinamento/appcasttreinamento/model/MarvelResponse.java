package br.com.wpgomes.treinamento.appcasttreinamento.model;


/**
 * Created by wgomes on 11/07/16.
 */

public class MarvelResponse<T> {
    public int code;
    public String status;
    public T data;

    @Override
    public String toString() {
        return "MarvelResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
