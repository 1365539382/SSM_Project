package nuc.zy.service.impl;

import nuc.zy.dao.IPermissionDao;
import nuc.zy.entity.Permission;
import nuc.zy.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao ;

    @Override
    public void deleteById(String id) {
        permissionDao.delete_role_permission(id);
        permissionDao.deletePermissionById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public Permission findPermissionById(String id) {
        return permissionDao.findPermissionById(id);
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission) ;
    }


}

