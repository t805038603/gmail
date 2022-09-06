package com.atguigu.gmall.pms.vo;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import lombok.Data;

import java.util.List;

@Data
public class SpuAttrValueVo extends SpuAttrValueEntity {
    public void setValueSelected(List<Object> valueSelected){
        // 如果接收的集合为空，则不设置
        if (CollectionUtils.isEmpty(valueSelected)){
            return;
        }
        this.setAttrValue(org.apache.commons.lang.StringUtils.join(valueSelected, ","));
    }
}
