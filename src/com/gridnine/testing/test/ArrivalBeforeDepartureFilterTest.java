package com.gridnine.testing.test;

import com.gridnine.testing.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalBeforeDepartureFilterTest {

    @Test
    public void whenFlightListIsEmpty_returnFalse() {

        Flight flight = new Flight(List.of());

        ArrivalBeforeDepartureFilter afilter = new ArrivalBeforeDepartureFilter();

        boolean result = afilter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenFlightIsNull_returnFalse() {

        Flight flight = null;

        ArrivalBeforeDepartureFilter afilter = new ArrivalBeforeDepartureFilter();

        boolean result = afilter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenDepartureTimeBeforeArrival_returnTrue() {

        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        Flight flight = new Flight(List.of(segment1));

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

        boolean result = filter.test(flight);

        assertTrue(result);
    }

    @Test
    public void whenDepartureTimeAfterArrival_returnFalse() {

        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now());

        Flight flight = new Flight(List.of(segment1));

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }


}
