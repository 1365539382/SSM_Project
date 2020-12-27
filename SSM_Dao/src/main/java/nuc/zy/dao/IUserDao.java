package nuc.zy.dao;

import nuc.zy.entity.Role;
import nuc.zy.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "nuc.zy.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) ;

    @Select("select * from users")
    public List<UserInfo> findAll();

    @Select("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);


    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "nuc.zy.dao.IRoleDao.findRoleByUserId"))

    })
    public UserInfo findById(String id);


    @Select("select * from role where id not in (select roleid from users_role where userid=#{id})")
    List<Role> findUserByIdAndAllRole(String id);

    @Insert("insert into users_role(userid,roleid) values(#{userId},#{role})")
    void addRoleToUser(@Param("userId") String userId, @Param("role") String role);

//    UserInfo findByUsername();



}
