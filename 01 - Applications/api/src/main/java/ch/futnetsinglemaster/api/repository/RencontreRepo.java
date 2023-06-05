
package ch.futnetsinglemaster.api.repository;

import ch.futnetsinglemaster.api.entity.Rencontre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RencontreRepo extends JpaRepository<Rencontre, Integer> {
}