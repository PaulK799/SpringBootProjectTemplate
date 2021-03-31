package com.paulk.demo.interceptor;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Override the {@link HandlerInterceptor} to {@link HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)} to implement request logging.
 */
@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final String SPACE = " ";
    private static final Logger ASYNCLOGGER = LoggerFactory.getLogger("jsonLogMessages");

    /**
     * Override the {@link HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)} method to implement logging of the {@link HttpServletRequest} and {@link HttpServletResponse}.
     *
     * @param request  - The {@link HttpServletRequest} to be processed.
     * @param response - The {@link HttpServletResponse} to be processed.
     * @param handler  - The {@link Object} handler being processed.
     * @param ex       - The {@link Exception} thrown.
     * @throws Exception An {@link Exception} thrown.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // Extract response without disturbing actual content.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(response);
        String input = getLogContent(httpServletRequest, httpServletResponse);
        MDC.put("logname", "logfile");
        ASYNCLOGGER.info(ClassicConstants.FINALIZE_SESSION_MARKER, input);
        httpServletResponse.copyBodyToResponse();
    }

    /**
     * Build up a {@link String} from the {@link ContentCachingRequestWrapper} and {@link ContentCachingResponseWrapper}.
     *
     * @param requestToCache  - The {@link ContentCachingRequestWrapper} to be processed.
     * @param responseToCache - The {@link ContentCachingResponseWrapper} to be processed.
     * @return A {@link String} containing the information required for logging.
     */
    private String getLogContent(ContentCachingRequestWrapper requestToCache, ContentCachingResponseWrapper responseToCache) {
        return requestToCache.getProtocol() +
                SPACE +
                responseToCache.getStatus() +
                SPACE +
                requestToCache.getMethod() +
                SPACE +
                requestToCache.getRequestURI() +
                SPACE +
                requestToCache.getRemoteAddr() +
                SPACE +
                requestToCache.getServerPort();
    }
}
