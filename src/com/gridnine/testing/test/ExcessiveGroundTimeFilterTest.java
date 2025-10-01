package com.gridnine.testing.test;

import com.gridnine.testing.ExcessiveGroundTimeFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExcessiveGroundTimeFilterTest {

    @Test
    public void whenFlightListIsEmpty_returnFalse() {

        Flight flight = new Flight(List.of());

        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenFlightIsNull_returnFalse() {

        Flight flight = null;

        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenExcessiveGroundTimeMoreTwoHours_thenReturnFalse(){

        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(7));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(9), LocalDateTime.now().plusHours(10));

        Flight flight = new Flight(List.of(segment1, segment2, segment3));

        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();

        boolean result = filter.test(flight);

        assertFalse(result);
    }

    @Test
    public void whenExcessiveGroundTimeLessTwoHours_thenReturnTrue(){

        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusMinutes(90), LocalDateTime.now().plusHours(7));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(10));

        Flight flight = new Flight(List.of(segment1, segment2, segment3));

        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();

        boolean result = filter.test(flight);

        assertTrue(result);
    }



}
