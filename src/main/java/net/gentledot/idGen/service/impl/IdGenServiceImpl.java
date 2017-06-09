package net.gentledot.idGen.service.impl;

import net.gentledot.idGen.service.IdGenService;
import net.gentledot.persistence.IdGenDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Sang on 2017-05-08.
 */
@Service("idGenService")
public class IdGenServiceImpl implements IdGenService{

    @Resource(name="idGenDao")
    IdGenDAO idGenDao;

    @Override
    public String getNextId(String tableName) {
        String nextId = idGenDao.getNextId(tableName);
        idGenDao.updateNextId(tableName);
        return nextId;
    }
}
