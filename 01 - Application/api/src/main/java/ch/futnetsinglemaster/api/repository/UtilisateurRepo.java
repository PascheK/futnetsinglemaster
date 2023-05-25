package ch.futnetsinglemaster.api.repository;

import ch.futnetsinglemaster.api.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByUsername(String username);
}