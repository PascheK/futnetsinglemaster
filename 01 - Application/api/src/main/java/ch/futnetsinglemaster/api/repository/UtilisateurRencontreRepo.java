package ch.futnetsinglemaster.api.repository;

import ch.futnetsinglemaster.api.entity.UtilisateurRencontre;
import ch.futnetsinglemaster.api.entity.UtilisateurRencontreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UtilisateurRencontreRepo extends JpaRepository<UtilisateurRencontre, UtilisateurRencontreId> {
}