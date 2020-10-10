package org.yxs.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 网关过滤器
 * 路由与过滤是Zuul的两大核心功能，路由功能负责将外部请求转发到具体的服务实例上去，是实现统一访问入口的基础，过滤功能负责对请求过程进行额外的处理，是请求校验过滤及服务聚合的基础。
 * @Author: y-xs
 * @Date: 2020/10/10 13:22
 */
@Component
@Slf4j
public class PreLogFilter extends ZuulFilter {
    /**
     * @Description: 过滤器类型 有pre、routing、post、error四种。
     * pre：在请求被路由到目标服务前执行，比如权限校验、打印日志等功能；
     * routing：在请求被路由到目标服务时执行，这是使用Apache HttpClient或Netflix Ribbon构建和发送原始HTTP请求的地方；
     * post：在请求被路由到目标服务后执行，比如给目标服务的响应添加头信息，收集统计数据等功能；
     * error：请求在其他阶段发生错误时执行。
     * @Author: y-xs
     * @Date: 2020/10/10 13:24
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Description: 过滤器执行顺序，数值越小优先级越高。
     * @Author: y-xs
     * @Date: 2020/10/10 13:23
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * @Description: 是否进行过滤，返回true会执行过滤。
     * @Author: y-xs
     * @Date: 2020/10/10 13:25
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Description: 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。
     * @Author: y-xs
     * @Date: 2020/10/10 13:25
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("Remote host:{},method:{},uri:{}", host, method, uri);
        return null;
    }
}
