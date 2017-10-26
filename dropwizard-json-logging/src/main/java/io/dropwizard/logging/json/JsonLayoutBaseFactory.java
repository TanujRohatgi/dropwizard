package io.dropwizard.logging.json;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.jackson.JacksonJsonFormatter;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.json.layout.JsonStacktraceLayout;

import java.util.TimeZone;

@JsonTypeName("json")
public class JsonLayoutBaseFactory extends AbstractJsonLayoutBaseFactory<ILoggingEvent> {

    private boolean prettyPrint;
    private boolean includeStackTrace = true;

    @JsonProperty
    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    @JsonProperty
    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }


    @JsonProperty
    public boolean isIncludeStackTrace() {
        return includeStackTrace;
    }

    @JsonProperty
    public void setIncludeStackTrace(boolean includeStackTrace) {
        this.includeStackTrace = includeStackTrace;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LayoutBase<ILoggingEvent> build(LoggerContext context, TimeZone timeZone) {
        final JsonStacktraceLayout jsonLayout = new JsonStacktraceLayout(includeStackTrace);
        final JacksonJsonFormatter jsonFormatter = new JacksonJsonFormatter();
        jsonFormatter.setPrettyPrint(prettyPrint);
        jsonLayout.setJsonFormatter(jsonFormatter);
        jsonLayout.setContext(context);
        jsonLayout.setTimestampFormat(getTimestampFormat());
        jsonLayout.setTimestampFormatTimezoneId(timeZone.getID());
        jsonLayout.setAppendLineSeparator(true);
        return jsonLayout;
    }

}
