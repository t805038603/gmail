package com.atguigu.gmall.sms.mapper;

import com.atguigu.gmall.sms.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author gangge
 * @email gangge@atguigu.com
 * @date 2022-09-07 18:21:36
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponEntity> {
	
}
