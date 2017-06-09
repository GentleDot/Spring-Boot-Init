package net.gentledot.persistence;

import net.gentledot.vo.DeptVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sang on 2017-05-08.
 */

@Component
@Repository("deptDao")
public class DeptDAO {
    private SqlSession sqlSession;

    DeptDAO(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public List<DeptVO> selectDeptList(DeptVO vo){
        return sqlSession.selectList("selectDeptList", vo);
    }

    public int deptListCount(DeptVO vo){
        return sqlSession.selectOne("deptListCount", vo);
    }

    public DeptVO selectDept(DeptVO vo){
        return sqlSession.selectOne("selectDept", vo);
    }

    public int addDept(DeptVO vo){
        return sqlSession.insert("addDept", vo);
    }

    public int updateDept(DeptVO vo){
        return sqlSession.update("updateDept", vo);
    }

    public int deleteDept(DeptVO vo){
        return sqlSession.delete("deleteDept", vo);
    }
}
