package nuc.zy.dao;

import nuc.zy.entity.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    @Select("select * from member where id=#{id}")
    public Member findById(String id) ;

}
