package com.company.sikayet.audit;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    private final LogService logService;

    public void info(String message) {
        log.info(message);
    }

    public LogService logService() {
        return logService;
    }
}
