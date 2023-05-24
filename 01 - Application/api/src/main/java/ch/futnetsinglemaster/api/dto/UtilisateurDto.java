package ch.futnetsinglemaster.api.dto;

import java.io.Serializable;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link ch.futnetsinglemaster.api.entity.Utilisateur} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurDto  {
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String password;
    private String role;
    private String nomEquipe;

}