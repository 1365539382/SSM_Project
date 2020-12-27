package nuc.zy.service;

import nuc.zy.entity.Role;
import nuc.zy.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    void addRoleToUser(String userId, String[] roles);
}
