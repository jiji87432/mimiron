package cn.mimiron.core.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 *
 * @author zhangxd
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}