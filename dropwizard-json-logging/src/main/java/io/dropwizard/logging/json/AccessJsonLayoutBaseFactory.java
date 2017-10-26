package io.dropwizard.logging.json;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.json.layout.LogbackAccessRequestJsonLayout;
import io.dropwizard.logging.layout.LayoutFactory;

import java.util.TimeZone;

@JsonTypeName("access-json")
public class AccessJsonLayoutBaseFactory extends AbstractJsonLayoutBaseFactory<IAccessEvent> {

    @Override
    public LayoutBase<IAccessEvent> build(LoggerContext context, TimeZone timeZone) {
        return new LogbackAccessRequestJsonLayout(context, timeZone, getTimestampFormat());
    }
}
