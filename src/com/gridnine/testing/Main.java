package com.gridnine.testing;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> fly = FlightBuilder.createFlights();

        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter excessiveGroundTimeFilter = new ExcessiveGroundTimeFilter();

        List<FlightFilter> filter = Arrays.asList(
                new ArrivalBeforeDepartureFilter(),
                new ExcessiveGroundTimeFilter(),
                new DepartureBeforeNowFilter()
        );

        //Вывод всех перелетов
        System.out.println("------------Перелеты------------");
        printAllFlights(fly);

        //Вывод перелетов до настоящего времени
        List<Flight> filtered1 = FlightFilteringModule.filter(fly, departureBeforeNowFilter);
        System.out.println("Фильтр перелетов до текущего времени:");
        printAllFlights(filtered1);

        //Вывод перелетов c вылетом раньше прилета
        List<Flight> filtered2 = FlightFilteringModule.filter(fly, arrivalBeforeDepartureFilter);
        System.out.println("Фильтр перелетов c вылетом раньше прилета:");
        printAllFlights(filtered2);

        //Вывод перелетов с суммарным временем на земле более 2 часов
        List<Flight> filtered3 = FlightFilteringModule.filter(fly, excessiveGroundTimeFilter);
        System.out.println("Фильтр перелетов с суммарным временем на земле более 2 часов:");
        printAllFlights(filtered3);

        //Все фильтры
        List<Flight> filtered4 = FlightFilteringModule.filter(fly, filter);
        System.out.println("------Применение всех фильтров------");
        printAllFlights(filtered4);

    }

    private static void printAllFlights(List<Flight> fly) {

        if (fly.isEmpty()) {
            System.out.println("Перелетов нет!");
        } else {
            for (int i = 0; i < fly.size(); i++) {
                System.out.println(fly.get(i).toString());
            }
        }
        System.out.println();

    }


}
