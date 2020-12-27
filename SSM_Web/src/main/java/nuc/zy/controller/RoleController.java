package nuc.zy.controller;


import nuc.zy.entity.Permission;
import nuc.zy.entity.Role;
import nuc.zy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.PublicKey;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService ;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList) ;
        mv.setViewName("role-list");
        return mv ;
    }

    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role) ;
        return "redirect:findAll.do" ;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Role roleList = roleService.findById(id);
        mv.addObject("role",roleList) ;
        mv.setViewName("role-show");
        return mv ;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(String id) {
        roleService.deleteById(id) ;
        return "redirect:findAll.do" ;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = roleService.findUserByIdAndAllRole(id) ;
        Role role = roleService.findById(id);
        mv.addObject("role",role) ;
        mv.addObject("permissionList",permissions) ;
        mv.setViewName("role-permission-add");
        return mv ;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") String id,@RequestParam("ids") String[] permissionId) {
        roleService.addPermissionToRole(id,permissionId) ;
        return "redirect:findAll.do" ;
    }

}
