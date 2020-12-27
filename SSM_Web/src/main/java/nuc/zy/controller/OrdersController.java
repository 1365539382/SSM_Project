package nuc.zy.controller;

import com.github.pagehelper.PageInfo;
import nuc.zy.entity.Orders;
import nuc.zy.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService ;

//    查询全部订单-未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() {
//        ModelAndView mv = new ModelAndView() ;
//        List<Orders> ordersList = ordersService.findAll();
////        System.out.println(ordersList);
//        mv.setViewName("orders-list");
//        mv.addObject("ordersList",ordersList) ;
//        return mv ;
//    }

//    @Secured("ROLE_ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView() ;
        List<Orders> ordersList = ordersService.findAll(page,size);
//        System.out.println(ordersList);
        PageInfo pageInfo = new PageInfo(ordersList) ;
        mv.setViewName("orders-page-list");
        mv.addObject("pageInfo",pageInfo) ;
        return mv ;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) {
        ModelAndView mv = new ModelAndView() ;
        Orders orders = ordersService.findById(id) ;
        mv.addObject("orders",orders) ;
        mv.setViewName("orders-show");
        return mv ;
    }


    @RequestMapping("/deleteOrder.do")
    public String deleteOrder(String ids) {
//        System.out.println(ids);
        String[] id = ids.split(",");
        for (int i=0;i<id.length ;i++){
            ordersService.deleteOrder(id[i]) ;
        }
        return  "redirect:findAll.do" ;
    }

}
