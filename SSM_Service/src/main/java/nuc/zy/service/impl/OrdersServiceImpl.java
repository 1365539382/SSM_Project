package nuc.zy.service.impl;

import com.github.pagehelper.PageHelper;
import nuc.zy.dao.IOrdersDao;
import nuc.zy.entity.Orders;
import nuc.zy.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao ;

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }

    @Override
    public void deleteOrder(String id) {
        ordersDao.delete_Order_Traveller(id) ;
        ordersDao.deleteOrder(id) ;
    }

    @Override
    public List<Orders> findAll(int page ,int size) {

        //ordersDao.findAll()必须再使用前使用
        PageHelper.startPage(page,size) ;
        return ordersDao.findAll();
    }
}
