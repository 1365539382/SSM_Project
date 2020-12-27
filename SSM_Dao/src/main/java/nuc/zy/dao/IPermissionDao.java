package nuc.zy.dao;

import nuc.zy.entity.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) ;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission")
    List<Permission> findAll();

    @Select("select * from permission where id in (select permissionId from role_permission where roleid=#{id})")
    List<Permission> findById(String id);

    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id = true,property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "nuc.zy.dao.IRoleDao.Find_Role_Permission"))
    })
    Permission findPermissionById(String id);

    @Delete("delete from permission where id=#{id}")
    void deletePermissionById(String id) ;

    @Delete("delete from role_permission where permissionid=#{id}")
    void delete_role_permission(String id) ;

}
