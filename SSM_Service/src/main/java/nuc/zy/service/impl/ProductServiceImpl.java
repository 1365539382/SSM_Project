package nuc.zy.service.impl;

import com.github.pagehelper.PageHelper;
import nuc.zy.dao.IProductDao;
import nuc.zy.entity.Product;
import nuc.zy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao ;

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product) ;
    }

    public List<Product> findAll(int page ,int size) {

        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void deleteProductById(String id) {
        productDao.deleteOrder_TravellerById(id);
//        productDao.deleteTravellerById(id);
        productDao.deleteOrderById(id);
        productDao.deleteProductById(id) ;
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id) ;
    }

    @Override
    public void updateById(String id, Integer status) {
        productDao.updateById(status,id) ;
    }
}
