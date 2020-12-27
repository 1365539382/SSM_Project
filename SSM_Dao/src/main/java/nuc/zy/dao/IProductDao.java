package nuc.zy.dao;

import nuc.zy.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {
    @Select("select * from product")
    public List<Product> findAll() ;

//    @Insert("insert into product(PRODUCTNUM,PRODUCTNAME,CITYNAME,DEPARTURETIME,PRODUCTPRICE,PRODUCTDESC,PRODUCTSTATUS) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void addProduct(Product product);


    @Select("select * from product where id = #{id}")
    public Product findById(String id) ;

    @Delete("delete from product where id = #{id}")
    void deleteProductById(String id);

    @Delete("delete from orders where productid=#{id}")
    void deleteOrderById(String id);

    @Delete("delete from order_traveller where orderid in (select id from orders where productid=#{id})")
    void deleteOrder_TravellerById(String id);

    @Delete("delete from traveller where id in (select travellerid from order_traveller where orderid in (select id from orders where productid=#{id}))")
    void deleteTravellerById(String id);

    @Update("update product set productstatus=#{status} where id=#{id}")
    void updateById(@Param("status")Integer status, @Param("id")String id);
}
