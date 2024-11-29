package org.example.config;

import cn.hutool.core.lang.UUID;

public class TraceIdConfig {
    public static String buildTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
