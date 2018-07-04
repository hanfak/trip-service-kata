package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = loggedUser();

        checkLoggedUSerIsLoggedIn(loggedUser);

        return user.getFriends().stream()
                .filter(isUserFriendsWithLoggedInUser(loggedUser))
                .findFirst()
                .map(friend -> findTripsByUser(user))
                .orElse(new ArrayList<>());
    }

    private Predicate<User> isUserFriendsWithLoggedInUser(User loggedUser) {
        return friend -> friend.equals(loggedUser);
    }

    private void checkLoggedUSerIsLoggedIn(User loggedUser) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    protected User loggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}