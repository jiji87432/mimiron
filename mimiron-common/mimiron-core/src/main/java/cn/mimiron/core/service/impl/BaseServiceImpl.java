package cn.mimiron.core.service.impl;

import cn.mimiron.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
