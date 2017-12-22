package cn.zhangxd.mimiron.core.config.metrics;

import com.codahale.metrics.MetricRegistry;
import cn.zhangxd.mimiron.core.config.MimironProperties;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@ConditionalOnClass(CollectorRegistry.class)
public class PrometheusRegistry implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(PrometheusRegistry.class);

    private final MetricRegistry metricRegistry;

    private final MimironProperties mimironProperties;

    public PrometheusRegistry(MetricRegistry metricRegistry, MimironProperties mimironProperties) {
        this.metricRegistry = metricRegistry;
        this.mimironProperties = mimironProperties;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (mimironProperties.getMetrics().getPrometheus().isEnabled()) {
            String endpoint = mimironProperties.getMetrics().getPrometheus().getEndpoint();
            log.info("Initializing Metrics Prometheus endpoint at {}", endpoint);
            CollectorRegistry collectorRegistry = new CollectorRegistry();
            collectorRegistry.register(new DropwizardExports(metricRegistry));
            servletContext
                .addServlet("prometheusMetrics", new MetricsServlet(collectorRegistry))
                .addMapping(endpoint);
        }
    }
}
