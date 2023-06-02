package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.dto.EquipesDTO;
import ch.futnetsinglemaster.api.dto.RolesDTO;

import java.util.List;

public interface UtilsService {
    List<EquipesDTO> getAllEquipe();


    List<RolesDTO> getAllRole();
}
