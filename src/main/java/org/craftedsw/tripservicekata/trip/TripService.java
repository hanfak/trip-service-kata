package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private List<Trip> tripList = new ArrayList<>();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = loggedUser();

        if (loggedUser != null) {
            return user.getFriends().stream()
                    .filter(friend -> friend.equals(loggedUser))
                    .findFirst()
                    .map(friend -> findTripsByUser(user))
                    .orElse(tripList);
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User loggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}