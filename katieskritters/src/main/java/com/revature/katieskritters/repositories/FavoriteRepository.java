package com.revature.katieskritters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Favorite;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);

    Optional<Favorite> findByUserAndFish(User user, Fish fish);
}
