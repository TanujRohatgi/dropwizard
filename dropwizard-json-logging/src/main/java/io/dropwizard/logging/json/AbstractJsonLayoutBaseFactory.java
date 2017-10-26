package io.dropwizard.logging.json;

import ch.qos.logback.core.spi.DeferredProcessingAware;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logging.layout.DiscoverableLayoutFactory;

/**
 * <table>
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>{@code prettyPrint}</td>
 * <td>{@code false}</td>
 * <td>Whether jackson json printing should beautify the output for human readability</td>
 * </tr>
 * <tr>
 * <td>{@code includeStackTrace}</td>
 * <td>true</td>
 * <td>
 * whether to include the stacktrace along with the exception and other details
 * </td>
 * </tr>
 * <tr>
 * <td>{@code timestampFormat}</td>
 * <td>{@code None}</td>
 * <td>The formatter string to use to format timestamps. Defaults to the time elapsed since unix epoch</td>
 * </tr>
 * </table>
 */
public abstract class AbstractJsonLayoutBaseFactory<E extends DeferredProcessingAware>
    implements DiscoverableLayoutFactory<E> {

    private String timestampFormat;

    @JsonProperty
    public String getTimestampFormat() {
        return timestampFormat;
    }

    @JsonProperty
    public void setTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }
}
