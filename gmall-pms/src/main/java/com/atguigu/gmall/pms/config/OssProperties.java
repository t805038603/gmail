package com.atguigu.gmall.pms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
// ConfigurationProperties如果爆红  表示当前类没有被扫描到注入到容器中
//@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    String accessId; //: "LTAI5t8HdDodx9QQtPZNocEj"
    String accessKey; //: 7KY1dlLULYZirUyAtOa97gURCCn7Rr
    String endpoint; //: "oss-cn-hangzhou.aliyuncs.com"
    String bucket; //: "gta-gmall"
    String schema; //: "https://"
}
