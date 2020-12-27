package nuc.zy.dao;

import nuc.zy.entity.Permission;
import nuc.zy.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "roleName" ,column = "roleName"),
            @Result(property = "roleDesc" ,column = "roleDesc"),
            @Result(property = "permissions" ,column = "id",javaType = java.util.List.class,many = @Many(select = "nuc.zy.dao.IPermissionDao.findPermissionByRoleId"))

    })
    public List<Role> findRoleByUserId(String userId) ;

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);



    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions" ,javaType = java.util.List.class,many = @Many(select="nuc.zy.dao.IPermissionDao.findById"))
    })
    Role findById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteById(String id);

    @Delete("delete from role_permission where roleid=#{id}")
    void delete_role_permission(String id);

    @Delete("delete from users_role where roleid=#{id}")
    void delete_users_role(String id);

    @Select("select * from role where id in (select roleid from role_permission where id=#{id})")
    List<Role> Find_Role_Permission (String id) ;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleid=#{id})")
    List<Permission> findUserByIdAndAllRole(String id);

    @Insert("insert into role_permission(roleid,permissionid) values(#{id},#{permission})")
    void addPermissionToRole(@Param("id") String id, @Param("permission") String permission);
}
