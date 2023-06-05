package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.dto.EquipesDTO;
import ch.futnetsinglemaster.api.dto.RolesDTO;
import ch.futnetsinglemaster.api.entity.Equipe;
import ch.futnetsinglemaster.api.entity.Role;
import ch.futnetsinglemaster.api.repository.EquipeRepo;
import ch.futnetsinglemaster.api.repository.RoleRepo;
import ch.futnetsinglemaster.api.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilsServiceIMPL implements UtilsService {

    @Autowired
    private EquipeRepo equipeRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public List<EquipesDTO> getAllEquipe() {
        List<Equipe> getEquipe =(List<Equipe>) equipeRepo.findAll();

        return getEquipe.stream().map(this::mapToDTOList).toList();
    }

    @Override
    public List<RolesDTO> getAllRole() {
        List<Role> getRoles = (List<Role>) roleRepo.findAll();

        return getRoles.stream().map(this::mapToDTOList).toList();
    }

        private EquipesDTO mapToDTOList(Equipe equipe) {

        EquipesDTO equipesDTO = new EquipesDTO(equipe.getId(), equipe.getNomEquipe());

        return equipesDTO;
    }
    private RolesDTO mapToDTOList(Role role) {

        RolesDTO roleDTO = new RolesDTO(role.getId(), role.getRole());

        return roleDTO;
    }
}
