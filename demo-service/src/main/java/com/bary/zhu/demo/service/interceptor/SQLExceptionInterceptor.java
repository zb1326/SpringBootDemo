package com.bary.zhu.demo.service.interceptor;

import com.bary.zhu.demo.common.JsonUtil;
import com.google.common.base.Stopwatch;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 监控异常sql和慢查询sql(>500ms)
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }) })
public class SQLExceptionInterceptor implements Interceptor {
    public static final Logger logger = LoggerFactory.getLogger(SQLExceptionInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        String sql = mappedStatement.getBoundSql(parameter).getSql();
        try {

            Stopwatch stopwatch = Stopwatch.createStarted();
            Object object = invocation.proceed();
            long costTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            if (costTime > 500) {
                logger.info("slow sql {} millis. sql: {}. parameter: {}", costTime, sql, toJson(parameter));
            }
            return object;
        } catch (Exception e) {
            logger.error("SQL Error: {}, SQL Parameter: {}", sql, toJson(parameter), e);
            throw e;
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JsonUtil.writeObjectAsJson(object);
    }
}
