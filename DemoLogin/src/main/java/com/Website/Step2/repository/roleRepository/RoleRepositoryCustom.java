package com.Website.Step2.repository.roleRepository;


import com.Website.Step2.model.Role;

import java.util.List;

public interface RoleRepositoryCustom {

    List<Role> getHighestRole(Long id);

    List<String> getListRoleByUsername(String username);

    List<String> getListRoleByUserId(Long id);
}
