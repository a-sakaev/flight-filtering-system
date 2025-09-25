package com.gridnine.testing;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightFilteringModule {

    public static List<Flight> filter(List<Flight> flights, FlightFilter filter) {

        if (flights == null || filter == null){
            throw new IllegalArgumentException("Не верные параметры фильтров и перелетов!");
        }

        return flights.stream()
                .filter(filter::test)
                .toList();
    }

    public static List<Flight> filter(List<Flight> flights, List<FlightFilter> filters) {

        if (flights == null || filters == null){
            throw new IllegalArgumentException("Не верные параметры фильтров и перелетов!");
        }

        return flights.stream()
                .filter(flight -> filters.stream()
                        .allMatch(filter -> filter.test(flight)))
                .toList();
    }
}
