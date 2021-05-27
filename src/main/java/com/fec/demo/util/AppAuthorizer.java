package com.fec.demo.util;

import org.springframework.security.core.Authentication;

public interface AppAuthorizer {
    boolean authorize(Authentication authentication, String action, Object callerObj);
}