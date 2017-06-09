package net.gentledot.scott.service.impl;

import net.gentledot.persistence.DeptDAO;
import net.gentledot.scott.service.DeptService;
import net.gentledot.vo.DeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sang on 2017-05-08.
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Resource(name = "deptDao")
    DeptDAO deptDao;

    @Override
    public List<DeptVO> selectDeptList(DeptVO vo) {
        return deptDao.selectDeptList(vo);
    }

    @Override
    public int deptListCount(DeptVO vo) {
        return deptDao.deptListCount(vo);
    }

    @Override
    public DeptVO selectDept(DeptVO vo) {
        return deptDao.selectDept(vo);
    }

    @Override
    public int addDept(DeptVO vo) {
        return deptDao.addDept(vo);
    }

    @Override
    public int updateDept(DeptVO vo) {
        return deptDao.updateDept(vo);
    }

    @Override
    public int deleteDept(DeptVO vo) {
        return deptDao.deleteDept(vo);
    }
}
