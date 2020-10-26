package com.meituan.pay.finsecurity.controller;

import com.google.common.collect.Maps;
import com.meituan.inf.xmdlog.XMDLogFormat;
import com.meituan.pay.common.framework.AppContextHolder;
import com.meituan.pay.common.framework.ResponseVo;
import com.meituan.pay.common.framework.ResponseVoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
public class HttpStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusController.class);

    @RequestMapping("/api403")
    public ResponseVo build403Error(HttpServletRequest request){
        return ResponseVoFactory.buildErrorResVo(null, 1001, "抱歉,尚无权限访问!");

    }
    @RequestMapping("/api404")
    public ResponseVo build404Error(HttpServletRequest request){
        return ResponseVoFactory.buildErrorResVo(null, 1002, "请检查请求路径!");
    }
    @RequestMapping("/api500")
    public ResponseVo build500Error(HttpServletRequest request, Exception ex){
        String uri = request.getRequestURI();
        Map<String, String[]> param = Maps.newHashMap(request.getParameterMap());
        LOGGER.error("{} uri:{}, param:{}",
                XMDLogFormat.build().putTag("userId", String.valueOf(AppContextHolder.getUserId())),
                uri, param, ex);
        return ResponseVoFactory.buildErrorResVo(null, 1003, "服务器开小差咯!");
    }
}
