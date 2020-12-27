package nuc.zy.controller;


import nuc.zy.entity.Role;
import nuc.zy.entity.UserInfo;
import nuc.zy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService ;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView() ;
        List<UserInfo> userList = userService.findAll() ;
        mv.addObject("userList",userList) ;
        mv.setViewName("user-list");
        return mv ;
    }

    //用户添加
//    @PreAuthorize("authentication.principal.username=='tom'")
    @RequestMapping("save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo) ;
        return "redirect:findAll.do" ;
    }

    //查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id) ;
        mv.addObject("user",userInfo) ;
        mv.setViewName("user-show1");
        return mv ;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = userService.findUserByIdAndAllRole(id) ;
        UserInfo userInfo = userService.findById(id);
        mv.addObject("roleList",roleList) ;
        mv.addObject("userInfo",userInfo) ;
        mv.setViewName("user-role-add");
        return mv ;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(value = "userId" ,required = true) String userId,@RequestParam("ids") String[] roles) {
        userService.addRoleToUser(userId,roles) ;
        return "redirect:findAll.do" ;
    }

}
