package net.gentledot.scott.service.impl;

import net.gentledot.persistence.EmpDAO;
import net.gentledot.scott.service.EmpService;
import net.gentledot.utils.Paging;
import net.gentledot.vo.EmpVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sang on 2017-05-02.
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Resource(name = "empDao")
    EmpDAO empDao;

    @Override
    public Map<String, Object> selectEmpList(String empNo, int pageNo, int pageSize) {
        Map<String, Object> resultMap = new HashMap<>();

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);
        vo.setPageNo(pageNo);
        vo.setPageSize(pageSize);

        List<EmpVO> resultList = empDao.selectEmpList(vo);
        resultMap.put("resultList", resultList);

        int totalCnt = empDao.empListCount(vo);
        Paging paging = new Paging(pageSize, pageNo, 6, totalCnt );
        paging.setPaging();

        resultMap.put("paging", paging);

        return resultMap;
    }

    @Override
    public EmpVO selectEmp(EmpVO vo) {
        return empDao.selectEmp(vo);
    }

    @Override
    public int addEmp(EmpVO vo) {
        return empDao.addEmp(vo);
    }

    @Override
    public int updateEmp(EmpVO vo) {
        return empDao.updateEmp(vo);
    }

    @Override
    public int deleteEmp(EmpVO vo) {
        return empDao.deleteEmp(vo);
    }
}
