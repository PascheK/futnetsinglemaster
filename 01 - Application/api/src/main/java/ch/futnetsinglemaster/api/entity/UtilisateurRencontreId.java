package ch.futnetsinglemaster.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UtilisateurRencontreId implements Serializable {
    private static final long serialVersionUID = 530611932578396605L;
    @NotNull
    @Column(name = "Utilisateur_PK_Utilisateur", nullable = false)
    private Integer utilisateurPkUtilisateur;

    @NotNull
    @Column(name = "Rencontre_PK_Rencontre", nullable = false)
    private Integer rencontrePkRencontre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UtilisateurRencontreId entity = (UtilisateurRencontreId) o;
        return Objects.equals(this.utilisateurPkUtilisateur, entity.utilisateurPkUtilisateur) &&
                Objects.equals(this.rencontrePkRencontre, entity.rencontrePkRencontre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateurPkUtilisateur, rencontrePkRencontre);
    }

}