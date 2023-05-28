package ch.futnetsinglemaster.api.repository;

import ch.futnetsinglemaster.api.entity.Rencontre;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.entity.UtilisateurRencontre;
import ch.futnetsinglemaster.api.entity.UtilisateurRencontreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UtilisateurRencontreRepo extends JpaRepository<UtilisateurRencontre, UtilisateurRencontreId> {
    UtilisateurRencontre findByIdRencontreAndIdUtilisateur(Rencontre r, Utilisateur u);
    List<UtilisateurRencontre> findAllByIdRencontre(Rencontre r);
    @Query(value="SELECT COUNT(*) FROM utilisateur_rencontre AS c1 INNER JOIN utilisateur_rencontre as c2 ON c1.id_rencontre = c2.id_rencontre AND c1.id_utilisateur != c2.id_utilisateur WHERE c1.id_utilisateur=:u1 AND c2.id_utilisateur=:u2", nativeQuery = true)
    int countRencontreByUsersID(@Param("u1") int u1, @Param("u2") int user2);

    int countAllByIdUtilisateurAndScore(Utilisateur u, int score);
}