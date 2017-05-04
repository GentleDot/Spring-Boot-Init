package net.gentledot.scott.service;

import net.gentledot.vo.EmpVO;

import java.util.List;

/**
 * Created by Sang on 2017-05-02.
 */
public interface EmpService {
    public List<EmpVO> selectEmpList(EmpVO vo);
}
