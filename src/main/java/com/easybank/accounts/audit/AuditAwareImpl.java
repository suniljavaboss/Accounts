package com.easybank.accounts.audit;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    @NonNull
    public  Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MSG");
    }
}
