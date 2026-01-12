package org.example.travel_journal_app.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CurrentUserProvider {

    public Long getCurrentUserId() {
        var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return null;

        Object v = attrs.getRequest().getAttribute("userId");
        return (v instanceof Long l) ? l : null;
    }
}
