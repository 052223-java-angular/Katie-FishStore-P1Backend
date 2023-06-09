package com.revature.katieskritters.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.entities.Favorite;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.FavoriteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public List<Favorite> findAllFavoritesByUser(User user) {
        return favoriteRepository.findByUser(user);
    }
    
    public Favorite saveFavorite(User user, Fish fish) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFish(fish);
        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(User user, Fish fish) {
        Favorite favorite = favoriteRepository.findByUserAndFish(user, fish)
                .orElseThrow(() -> new RuntimeException("No favorite found to delete!"));
        favoriteRepository.delete(favorite);
    }

}