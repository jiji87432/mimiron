package cn.mimiron.core.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 *
 * @author zhangxd
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
