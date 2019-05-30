package com.cheng.erik.john.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ：PasswordFilter
 * @Author ：JohnErikCheng
 * @Email ：dong@19910925@126.com
 * @Date ：Created in 2019/5/30 10:25
 * @Description: 过滤器 Password
 */
public class PasswordFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(PasswordFilter.class);

    @Override
    public String filterType() {
        //请求处理完成后执行的filter
        return "post";
    }

    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //判断上一个过滤器结果为true，否则就不走下面的过滤器，直接跳过
        //后面的所有过滤器并返回上一个过滤器不通过的结果。
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("--->>> PasswordFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String username = request.getParameter("password");
        if (null != username && username.equals("123456")) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("The password cannot be empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
