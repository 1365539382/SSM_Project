package nuc.zy.controller;

import com.github.pagehelper.PageInfo;
import nuc.zy.entity.Product;
import nuc.zy.service.IProductService;
import nuc.zy.util.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@SuppressWarnings("unchecked")
@Transactional
@RequestMapping("/product")
public class ProductControllerImpl {

    @Autowired
    private IProductService productService ;

    //查询所有产品
//    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView() ;
        List<Product> products = productService.findAll(page,size);
//        System.out.println(products+"sss");
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo) ;
        mv.setViewName("product-list");
        return mv;
    }




//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class,new DateStringEditor());
//    }


    @RequestMapping("/deleteProductById.do")
    public String deleteById(String  ids) {
        String[] id = ids.split(",");
        for (int i=0;i<id.length ;i++){
            System.out.println("111111111111111111111111111111"+id[i]);
            productService.deleteProductById(id[i]) ;
        }
        return  "redirect:findAll.do" ;
    }


    @RequestMapping("/updateById.do")
    public String updateById(String  id) {
        Product product = productService.findById(id);
        String productStatusStr = product.getProductStatusStr();
        if (productStatusStr.equals("开启")) {
            productService.updateById(id,0) ;
        }
        if (productStatusStr.equals("关闭")) {
            productService.updateById(id,1) ;
        }
        return "redirect:findAll.do";
    }

    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) {
//        System.out.println(product.toString());
        productService.addProduct(product);
        return "redirect:findAll.do";
    }

}
