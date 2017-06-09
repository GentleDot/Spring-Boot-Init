package net.gentledot.scott.service;

import net.gentledot.vo.DeptVO;

import java.util.List;

/**
 * Created by Sang on 2017-05-08.
 */
public interface DeptService {
    public List<DeptVO> selectDeptList(DeptVO vo);
    public int deptListCount(DeptVO vo);
    public DeptVO selectDept(DeptVO vo);
    public int addDept(DeptVO vo);
    public int updateDept(DeptVO vo);
    public int deleteDept(DeptVO vo);
}
