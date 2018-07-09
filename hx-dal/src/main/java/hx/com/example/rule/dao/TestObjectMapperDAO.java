package hx.com.example.rule.dao;

import hx.com.example.rule.dto.TestObjectDTO;
import hx.com.example.rule.param.TestObjectQueryParamDTO;
import hx.data.mybatis.mapper.BaseMapper;
import hx.data.mybatis.provider.BaseMapperSelectProvider;
import hx.data.mybatis.provider.LinkedQueryProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.MapperSql;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@MapperSql(type = LinkedQueryProvider.class)
public interface TestObjectMapperDAO extends BaseMapper<TestObjectDTO, TestObjectQueryParamDTO>,Mapper<TestObjectDTO> {

     List<TestObjectDTO> queryList(TestObjectQueryParamDTO param);

     List<TestObjectDTO> queryAll(TestObjectQueryParamDTO param);

     int insertObject (TestObjectQueryParamDTO param);
}