package com.cheng.erik.john.filter;

import com.netflix.discovery.util.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * @ClassName ：TokenFilter
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/30 9:57
 * @Description: 过滤器 token.
 */
public class TokenFilter extends ZuulFilter {

    private final Logger logger =  LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        //可以在请求被路由之前调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        //filter执行顺序，通过数字制定，优先级为0
        //数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，此处为true，说明需要过滤。
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURI().toString());

        //获取请求参数
        String token = request.getParameter("token");

        if(StringUtils.isNotBlank(token)) {
            //对请求其进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
