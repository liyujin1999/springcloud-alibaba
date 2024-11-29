package org.example.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.example.cons.Constant;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDC.get(Constant.TRACE_ID);
        log.info("feign 调用拦截获取 traceId: {}", traceId);
        if (traceId != null) {
            requestTemplate.header(Constant.TRACE_ID, traceId);
        } else {
            String newTraceId = TraceIdConfig.buildTraceId();
            requestTemplate.header(Constant.TRACE_ID, newTraceId);
        }
    }
}
