package com.example.admin.recycleviewdboptimizationexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/19/2016.
 */
public class Handsome {

    private String name, url;
    public static List<Handsome> handsomeList = new ArrayList<>();

    static{
        handsomeList.add(new Handsome("John Nuccio", "https://dl.dropboxusercontent.com/u/103474456/john.jpg"));
        handsomeList.add(new Handsome("Jonathan Sanchez", "https://dl.dropboxusercontent.com/u/103474456/jonathan.jpg"));
        handsomeList.add(new Handsome("Andy Lin", "https://dl.dropboxusercontent.com/u/103474456/Andy.png"));
        handsomeList.add(new Handsome("Eugenio Kuri", "https://dl.dropboxusercontent.com/u/103474456/kuri.png"));
    }

    public Handsome(){}

    public Handsome(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
