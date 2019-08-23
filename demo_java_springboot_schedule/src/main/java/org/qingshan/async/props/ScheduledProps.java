package org.qingshan.async.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scheduled")
@Data
public class ScheduledProps {
    private String enabled;
    private String job1Enabled;
    private String job1Timer;
    private String job2Enabled;
    private String job2Timer;
}
