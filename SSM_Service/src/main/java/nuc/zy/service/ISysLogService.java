package nuc.zy.service;

import nuc.zy.entity.SysLog;

import java.util.List;

public interface ISysLogService {
    List<SysLog> findAll();
    void save(SysLog sysLog) ;
}
