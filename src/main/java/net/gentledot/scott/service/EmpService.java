package net.gentledot.scott.service;

import net.gentledot.vo.EmpVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Sang on 2017-05-02.
 */
public interface EmpService {
    public Map<String, Object> selectEmpList(String empNo, int pageNo, int pageSize);
    public EmpVO selectEmp(EmpVO vo);
    public int addEmp(EmpVO vo);
    public int updateEmp(EmpVO vo);
    public int deleteEmp(EmpVO vo);
}
