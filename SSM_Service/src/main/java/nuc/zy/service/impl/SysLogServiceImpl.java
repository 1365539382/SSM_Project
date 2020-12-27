package nuc.zy.service.impl;

import nuc.zy.dao.ISysLogDao;
import nuc.zy.entity.SysLog;
import nuc.zy.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    ISysLogDao sysLogDao ;

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }


    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
