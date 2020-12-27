package nuc.zy.service;

import nuc.zy.entity.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page ,int size) ;

    Orders findById(String id);

    void deleteOrder(String id) ;
}
