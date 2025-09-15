package com.acme.auctionhouse.config;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class MetricsFilter extends OncePerRequestFilter {

    private final MeterRegistry meterRegistry;
    private final AtomicLong maxResponseTime = new AtomicLong(0);

    public MetricsFilter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Instant startTime = Instant.now();

        try {
            // Continue the filter chain
            filterChain.doFilter(request, response);
        } finally {
            // Calculate request duration
            long durationMs = Duration.between(startTime, Instant.now()).toMillis();
            int status = response.getStatus();

            // Record total requests
            meterRegistry.counter("http.requests.total").increment();

            // Record requests by status category
            if (status >= 200 && status < 300) {
                meterRegistry.counter("http.requests.2xx").increment();
            } else if (status >= 400 && status < 500) {
                meterRegistry.counter("http.requests.4xx").increment();
            } else if (status >= 500) {
                meterRegistry.counter("http.requests.5xx").increment();
            }

            // Record response time in a timer (handles average calculation automatically)
            meterRegistry.timer("http.requests.duration").record(Duration.ofMillis(durationMs));

            // Update max response time if needed
            updateMaxResponseTime(durationMs);
        }
    }

    private void updateMaxResponseTime(long currentDuration) {
        long current;
        do {
            current = maxResponseTime.get();
            if (currentDuration <= current) {
                break;
            }
        } while (!maxResponseTime.compareAndSet(current, currentDuration));

        meterRegistry.gauge("http.requests.max_duration", maxResponseTime);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }
}
