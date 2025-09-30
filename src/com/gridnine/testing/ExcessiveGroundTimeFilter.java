package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class ExcessiveGroundTimeFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {

        boolean result = true;

        if (flight == null) {
            return false;
        }

        List<Segment> segments = flight.getSegments();
        int totalGroundTime = 0;

        if (segments.isEmpty()) {
            return false;
        }
        if (segments.size() <= 1){
            return true;
        }

        for (int i = 0; i < segments.size()-1; i++) {

            Duration groundTime = Duration.between(segments.get(i).getArrivalDate(), segments.get(i+1).getDepartureDate());
            totalGroundTime += (int) groundTime.toMinutes();

            if (totalGroundTime < 0 || totalGroundTime > 120){
                result = false;
            }

        }

        return result;

    }
}
