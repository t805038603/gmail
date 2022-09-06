package com.atguigu.gmall.search.fegin;

import com.atguigu.gmall.wms.api.GmallwmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("wms-service")
public interface GmWmsClient extends GmallwmsApi {
}
