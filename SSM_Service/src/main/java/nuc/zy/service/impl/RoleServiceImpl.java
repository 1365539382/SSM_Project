package nuc.zy.service.impl;

import nuc.zy.dao.IRoleDao;
import nuc.zy.entity.Permission;
import nuc.zy.entity.Role;
import nuc.zy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao ;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
       roleDao.save(role) ;
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findUserByIdAndAllRole(String id) {
        return roleDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addPermissionToRole(String id, String[] permissionId) {
        for (String permission:permissionId){
            roleDao.addPermissionToRole(id,permission);
        }
    }

    @Override
    public void deleteById(String id) {
        roleDao.delete_role_permission(id);
        roleDao.delete_users_role(id);
        roleDao.deleteById(id) ;
    }
}
