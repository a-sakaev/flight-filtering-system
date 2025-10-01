package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        if (flight == null) {
            return false;
        }
        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        return !segments.get(0).getDepartureDate().isBefore(LocalDateTime.now());
    }
}
