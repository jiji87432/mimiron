package cn.zhangxd.mimiron.core.config.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import cn.zhangxd.mimiron.core.config.MimironProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnClass(Graphite.class)
public class GraphiteRegistry {

    private final Logger log = LoggerFactory.getLogger(GraphiteRegistry.class);

    private final MimironProperties mimironProperties;

    public GraphiteRegistry(MetricRegistry metricRegistry, MimironProperties mimironProperties) {
        this.mimironProperties = mimironProperties;
        if (this.
                mimironProperties.getMetrics().getGraphite().isEnabled()) {
            log.info("Initializing Metrics Graphite reporting");
            String graphiteHost = mimironProperties.getMetrics().getGraphite().getHost();
            Integer graphitePort = mimironProperties.getMetrics().getGraphite().getPort();
            String graphitePrefix = mimironProperties.getMetrics().getGraphite().getPrefix();
            Graphite graphite = new Graphite(new InetSocketAddress(graphiteHost, graphitePort));
            GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .prefixedWith(graphitePrefix)
                .build(graphite);
            graphiteReporter.start(1, TimeUnit.MINUTES);
        }
    }
}
