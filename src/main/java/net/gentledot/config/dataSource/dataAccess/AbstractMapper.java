package net.gentledot.config.dataSource.dataAccess;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Sang on 2017-05-01.
 */

@Mapper
public class AbstractMapper {
    private SqlSession sqlSession;

    public int insert(String queryId) {
        return sqlSession.insert(queryId);
    }

    public int insert(String queryId, Object parameterObject) {
        return sqlSession.insert(queryId, parameterObject);
    }

    public int update(String queryId) {
        return sqlSession.update(queryId);
    }

    public int update(String queryId, Object parameterObject) {
        return sqlSession.update(queryId, parameterObject);
    }

    public int delete(String queryId) {
        return sqlSession.delete(queryId);
    }

    public int delete(String queryId, Object parameterObject) {
        return sqlSession.delete(queryId, parameterObject);
    }

    public <T> T selectOne(String queryId) {
        return sqlSession.selectOne(queryId);
    }

    public <T> T selectOne(String queryId, Object parameterObject) {
        return sqlSession.selectOne(queryId, parameterObject);
    }

    public <K, V> Map<K, V> selectMap(String queryId, String mapKey) {
        return sqlSession.selectMap(queryId, mapKey);
    }

    public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey) {
        return sqlSession.selectMap(queryId, parameterObject, mapKey);
    }

    public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey, RowBounds rowBounds) {
        return sqlSession.selectMap(queryId, parameterObject, mapKey, rowBounds);
    }

    public <E> List<E> selectList(String queryId) {
        return sqlSession.selectList(queryId);
    }

    public <E> List<E> selectList(String queryId, Object parameterObject) {
        return sqlSession.selectList(queryId, parameterObject);
    }

    public <E> List<E> selectList(String queryId, Object parameterObject, RowBounds rowBounds) {
        return sqlSession.selectList(queryId, parameterObject, rowBounds);
    }
}
