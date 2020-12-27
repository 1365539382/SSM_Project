package nuc.zy.controller;

import nuc.zy.entity.Permission;
import nuc.zy.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService ;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll() ;
        mv.setViewName("permission-list");
        mv.addObject("permissionList",permissionList) ;
        return mv ;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission) ;
        return "redirect:findAll.do" ;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Permission permissionList = permissionService.findPermissionById(id) ;
        mv.setViewName("permission-show");
        mv.addObject("permission",permissionList) ;
        return mv ;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(String id) {
        permissionService.deleteById(id) ;
        return "redirect:findAll.do" ;
    }

}
