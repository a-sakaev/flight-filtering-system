package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args){

        List<Flight> fly = FlightBuilder.createFlights();

        for (int i = 0; i < fly.size(); i++){
            System.out.println(fly.get(i).toString());
        }

    }

}
