package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.WithAssertions;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TripServiceTest implements WithAssertions {
    private final User loggedUser = new User();
    private TripService tripService;


//    @Test
//    public void shouldThrowExceptionWhenUserNotLoggedIn() throws Exception {
//
//    }
//

    @Before
    public void initialise() {
        tripService = createTripService();
    }

    private TripService createTripService() {
        return new TripService() {
            @Override
            protected User loggedUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> findTripsByUser(User user) {
                return user.trips();
            }
        };
    }
}

