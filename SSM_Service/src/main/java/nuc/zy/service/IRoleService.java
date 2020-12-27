package nuc.zy.service;

import nuc.zy.entity.Permission;
import nuc.zy.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteById(String id);

    List<Permission> findUserByIdAndAllRole(String id);

    void addPermissionToRole(String id, String[] permissionId);
}
