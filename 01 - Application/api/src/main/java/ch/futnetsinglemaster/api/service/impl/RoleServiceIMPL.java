package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.entity.Role;
import ch.futnetsinglemaster.api.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.futnetsinglemaster.api.service.RoleService;

@Service
public class RoleServiceIMPL implements RoleService{

  @Autowired
  private RoleRepo roleRepo;

  @Override
  public Role getRoleByID(int id) {
    Role res =roleRepo.getReferenceById(id);
    if (res != null) {
      
      return res;
    }
    return null;
  }
  
}
