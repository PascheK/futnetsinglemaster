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
    private static final long serialVersionUID = 8456981305396916300L;
    @NotNull
    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;

    @NotNull
    @Column(name = "id_rencontre", nullable = false)
    private Integer idRencontre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UtilisateurRencontreId entity = (UtilisateurRencontreId) o;
        return Objects.equals(this.idRencontre, entity.idRencontre) &&
                Objects.equals(this.idUtilisateur, entity.idUtilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRencontre, idUtilisateur);
    }

}