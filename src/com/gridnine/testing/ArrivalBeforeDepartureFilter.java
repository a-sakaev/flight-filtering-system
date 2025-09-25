package com.gridnine.testing;

import java.util.List;

public class ArrivalBeforeDepartureFilter implements FlightFilter {

    @Override
    public boolean test(Flight flight) {

        boolean result = true;

        if (flight == null) {
            return false;
        }

        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        for (int i = 0; i < segments.size(); i ++) {
            if (segments.get(i).getDepartureDate().isAfter(segments.get(i).getArrivalDate())) {
                return false;
            }
        }
        return result;
    }
}
