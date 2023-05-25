package ch.futnetsinglemaster.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "utilisateur_rencontre")
public class UtilisateurRencontre {
    @EmbeddedId
    private UtilisateurRencontreId id;

    @MapsId("idUtilisateur")
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