package net.gentledot.scott.service.impl;

import net.gentledot.persistence.DeptDAO;
import net.gentledot.scott.service.DeptService;
import net.gentledot.utils.Paging;
import net.gentledot.vo.DeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> selectDeptListWithPaging(String deptNo, int pageSize, int pageNo, int pageScope) {
        Map<String, Object> returnMap = new HashMap<>();

        DeptVO vo = new DeptVO();
        vo.setDeptNo(deptNo);
        vo.setPageSize(pageSize);
        vo.setPageNo(pageNo);

        List<DeptVO> resultList = deptDao.selectDeptList(vo);
        int totalCount = deptDao.deptListCount(vo);

        Paging paging = new Paging(pageSize, pageNo, pageScope, totalCount);
        paging.setPaging();

        returnMap.put("resultList", resultList);
        returnMap.put("paging", paging);

        return returnMap;
    }
}
