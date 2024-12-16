package com.website.online.sale.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
public class AsyncUtils {
    public static <T> T getFutureData(Future<T> future) {
        return AsyncUtils.getFutureData(future, null);
    }

    @SneakyThrows
    public static <T> T getFutureData(Future<T> future, T defaultData) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            log.error("", e);
            throw e;
        } catch (ExecutionException e1) {
            log.error("", e1);
        }
        return defaultData;
    }

    public static boolean shutDownIfTimeOut(ExecutorService virtualExecutor) {
        return shutDownIfTimeOut(virtualExecutor, 10, TimeUnit.SECONDS);
    }

    @SneakyThrows
    public static boolean shutDownIfTimeOut(ExecutorService virtualExecutor, Integer timeout, TimeUnit timeUnit) {
        virtualExecutor.shutdown();
        boolean timeOut = !virtualExecutor.awaitTermination(timeout, timeUnit);
        if (timeOut) {
            virtualExecutor.shutdownNow();
        }
        return !timeOut;
    }


}
