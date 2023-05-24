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

    @MapsId("utilisateurPkUtilisateur")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Utilisateur_PK_Utilisateur", nullable = false)
    private Utilisateur utilisateurPkUtilisateur;

    @MapsId("rencontrePkRencontre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Rencontre_PK_Rencontre", nullable = false)
    private Rencontre rencontrePkRencontre;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

    @NotNull
    @Column(name = "estJoueur1", nullable = false)
    private Byte estJoueur1;

}