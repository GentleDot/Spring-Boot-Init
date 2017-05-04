package net.gentledot.scott.service.impl;

import net.gentledot.persistance.EmpDAO;
import net.gentledot.scott.service.EmpService;
import net.gentledot.vo.EmpVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sang on 2017-05-02.
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Resource(name = "empDao")
    EmpDAO empDao;

    @Override
    public List<EmpVO> selectEmpList(EmpVO vo) {
        return empDao.selectEmpList(vo);
    }
}
