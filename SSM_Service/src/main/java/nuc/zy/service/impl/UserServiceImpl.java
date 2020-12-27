package nuc.zy.service.impl;

import nuc.zy.dao.IUserDao;
import nuc.zy.entity.Role;
import nuc.zy.entity.UserInfo;
import nuc.zy.service.IUserService;
import nuc.zy.util.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles())) ;
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles())) ;
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles())) ;
        return user;
    }

    //作用返回角色的描述信息  上述User
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>() ;
        for (Role role:roles) {
        list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())) ;
        }
        return list ;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll() ;
    }

    @Override
    public void addRoleToUser(String userId, String[] roles) {
        for (String role:roles){
            userDao.addRoleToUser(userId,role) ;
        }
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return userDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void save(UserInfo userInfo) {
//        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword())) ;
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword())) ;
        userDao.save(userInfo) ;
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id) ;
    }
}
