package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.WithAssertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TripServiceTest implements WithAssertions {
    @Test(expected = UserNotLoggedInException.class)
    public void shouldThrowExceptionWhenUserIsNotLoggedIn() {
        loggedUser = NON_LOGGED_USER;
        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void shouldNotReturnTripsWhenLoggedUserIsNotAFriend() {
        List<Trip> trips = tripService.getTripsByUser(targetUser);

        assertThat(trips.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnTripsWhenLoggedUserIsAFriend() {
        givenJohnHasAFriendAnd2Trips();

        List<Trip> trips = tripService.getTripsByUser(john);

        assertThat(trips).isEqualTo(john.trips());
        assertThat(trips.size()).isEqualTo(2);
    }

    private void givenJohnHasAFriendAnd2Trips() {
        john.addFriend(loggedUser);
        john.addTrip(new Trip());
        john.addTrip(new Trip());
    }

    private TripService createTripService() {
        return new TripService() {
            @Override
            protected List<Trip> findTripsByUser(User user) {
                return user.trips();
            }

            @Override
            protected User loggedUser() {
                return loggedUser;
            }
        };
    }

    @Before
    public void initialise() {
        tripService = createTripService();
    }

    private static final User UNUSED_USER = null;
    private static final User NON_LOGGED_USER = null;
    private final User targetUser = new User();
    private final User john = new User();
    private User loggedUser = new User();
    private TripService tripService;
}

