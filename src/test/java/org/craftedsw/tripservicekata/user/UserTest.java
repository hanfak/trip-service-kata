package org.craftedsw.tripservicekata.user;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;


public class UserTest implements WithAssertions {
    @Test
    public void isFriendsWithShouldBeTrue() {
        User john = new User();
        User bob = new User();

        john.addFriend(bob);

        assertThat(john.isFriendsWith(bob)).isTrue();
    }

    @Test
    public void isNotFriendsWithShouldBeFalse() {
        User john = new User();
        User bob = new User();

        assertThat(john.isFriendsWith(bob)).isFalse();
    }
}
