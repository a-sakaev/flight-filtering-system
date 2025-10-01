package com.gridnine.testing.test;

import com.gridnine.testing.DepartureBeforeNowFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartureBeforeNowFilterTest {

    @Test
    public void whenFlightListIsEmpty_returnFalse() {

        Flight flight = new Flight(List.of());

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenFlightIsNull_returnFalse() {

        Flight flight = null;

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenDepartureTimeBeforeNow_thenReturnFalse(){

        Segment segment = new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1));

        Flight flight = new Flight(List.of(segment));

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenDepartureTimeAfterNow_thenReturnTrue(){

        Segment segment = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(1));

        Flight flight = new Flight(List.of(segment));

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

        boolean result = filter.test(flight);

        assertTrue(result);
    }

}
