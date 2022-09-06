package com.atguigu.gmall.search.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchParamVo {
    /*
      search?keyword=小米   查询关键字
      &brandId=1,3     品牌列表
      &cid=225      分类id
      &props=5:高通-麒麟
      &props=6:骁龙865-硅谷1000   检索属性和值列表(props:  值由 检索属性的id和选中的检索属性的值组成   )
                遍历props集合，每一个prop 都使用:分隔字符串， 再使用-分隔值前面分隔后的第二部分
      &sort=1  排序字段：0-默认，得分降序；1-按价格升序；2-按价格降序；3-按创建时间降序；4-按销量降序
      &priceFrom=1000 价格起始值
      &priceTo=6000  价格结束值
      &pageNum=1   页码
      &store=true  是否有库存
     */
    private String keyword;
    private List<Long> brandId;
    private Long cid;
    private List<String> props;
    private Integer sort = 0;
    private Double priceFrom;
    private Double priceTo;
    private Integer pageNum = 1;
    private Boolean store;

}
