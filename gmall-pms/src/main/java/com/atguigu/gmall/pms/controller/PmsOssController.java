package com.atguigu.gmall.pms.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.config.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("pms/oss")
@RestController
@EnableConfigurationProperties(OssProperties.class)
public class PmsOssController {
   /*
       //注意：出错检查 子账户权限、地域节点地址、桶名、桶的跨域配置
        String accessId = "LTAI5t8HdDodx9QQtPZNocEj"; // 请填写您的AccessKeyId。
        String accessKey = "7KY1dlLULYZirUyAtOa97gURCCn7Rr"; // 请填写您的AccessKeySecret。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com"; // 请填写您的 endpoint。
        String bucket = "gta-gmall"; // 请填写您的 bucketname 。
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        //String callbackUrl = "http://88.88.88.88:8888";
        // 图片目录，每天一个目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dir = sdf.format(new Date()); // 用户上传文件时指定的前缀。
    */
   @Autowired
   OssProperties ossProperties;
    String accessId;
    String accessKey;
    String endpoint;
    String bucket;
    String host;
    @PostConstruct  //jdk提供的 在bean的构造器调用后立即执行的初始化方法
    public void init(){
        accessId = ossProperties.getAccessId();
        accessKey = ossProperties.getAccessKey();
        endpoint = ossProperties.getEndpoint();
        bucket = ossProperties.getBucket();
        host = ossProperties.getSchema()+bucket+"."+endpoint;
    }


    @GetMapping("policy")
    public ResponseVo policy() throws UnsupportedEncodingException {
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        String dir = new DateTime().toString("yyyy-MM-dd/");
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        long expireTime = 30;
        //代表 获取的授权token的过期时间  ：  300s
        long expireEndTime = System.currentTimeMillis() + expireTime * 10000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return ResponseVo.ok(respMap);
    }
}
