package ch.futnetsinglemaster.api.repository;

import ch.futnetsinglemaster.api.entity.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TournoiRepo extends JpaRepository<Tournoi, Integer> {
}