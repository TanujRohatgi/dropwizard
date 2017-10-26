package io.dropwizard.logging.json.layout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonStacktraceLayout extends JsonLayout {

    private static final String STACKTRACE_ATTR_NAME = "stacktrace";

    private final boolean includeStacktrace;

    public JsonStacktraceLayout(boolean includeStacktrace) {
        this.includeStacktrace = includeStacktrace;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map toJsonMap(ILoggingEvent event) {
        final Map<String, Object> map = super.toJsonMap(event);
        if (includeStacktrace) {
            Optional.ofNullable(event.getThrowableProxy())
                .map(IThrowableProxy::getStackTraceElementProxyArray)
                .ifPresent(stackTraces -> map.put(STACKTRACE_ATTR_NAME, Arrays.stream(stackTraces)
                    .map(StackTraceElementProxy::getSTEAsString)
                    .collect(Collectors.joining(System.lineSeparator()))));

        }
        return map;
    }
}
