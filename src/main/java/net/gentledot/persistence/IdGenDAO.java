package net.gentledot.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Sang on 2017-05-08.
 */

@Component
@Repository("idGenDao")
public class IdGenDAO {

    private SqlSession sqlSession;

    public IdGenDAO(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public String getNextId(String tableName){
        return sqlSession.selectOne("getNextId", tableName);
    }

    public int updateNextId(String tableName){
        return sqlSession.update("updateNextId", tableName);
    }
}
