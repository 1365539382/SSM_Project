package nuc.zy.service;

import nuc.zy.entity.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    Permission findPermissionById(String id);
    void deleteById(String id);
}
