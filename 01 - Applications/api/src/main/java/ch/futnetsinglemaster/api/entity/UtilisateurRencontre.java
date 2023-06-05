package ch.futnetsinglemaster.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur_rencontre")
public class UtilisateurRencontre {
    @EmbeddedId
    private UtilisateurRencontreId id;

    public UtilisateurRencontre(Utilisateur idUtilisateur, Rencontre idRencontre, @NotNull Integer score, @NotNull Byte estJoueur1) {
        this.idUtilisateur = idUtilisateur;
        this.idRencontre = idRencontre;
        this.score = score;
        this.estJoueur1 = estJoueur1;
    }

    @MapsId("idUtilisateur")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur idUtilisateur;

    @MapsId("idRencontre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rencontre", nullable = false)
    private Rencontre idRencontre;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

    @NotNull
    @Column(name = "est_joueur1", nullable = false)
    private Byte estJoueur1;

}