package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = loggedUser();

        checkLoggedUserIsLoggedIn(loggedUser);

        return user.getFriends().stream()
                .filter(loggedUser::isFriendsWith)
                .findFirst()
                .map(friend -> findTripsByUser(user))
                .orElse(new ArrayList<>());
    }

    private void checkLoggedUserIsLoggedIn(User loggedUser) {
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