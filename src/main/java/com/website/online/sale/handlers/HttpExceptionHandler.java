package com.website.online.sale.handlers;

import com.website.online.sale.base.BaseResponse;
import com.website.online.sale.base.ResponseBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR = "Có lỗi xảy ra, vui lòng thử lại sau";
    public static final String TIMEOUT_PROCESS_ERROR = "Timeout";
    public static final String UN_AUTHENTICATION = "UnAuthentication";
    public static final String ACCESS_DENIED = "AccessDenied";

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException() {
        BaseResponse<Object> responseDto = ResponseBuilder.error(HttpStatus.UNAUTHORIZED.value(),
                ACCESS_DENIED);
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        BaseResponse<Object> responseDto = ResponseBuilder.error(HttpStatus.UNAUTHORIZED.value()
                , UN_AUTHENTICATION);
        log.error("", e);
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String errorMessage = "Cần gửi lên thông tin '" + ex.getParameterName() + "'.";
        log.debug("handleMissingServletRequestParameter " + ex.getMessage());
        return ResponseEntity.badRequest().body(ResponseBuilder.error(400, errorMessage));
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            @NonNull HttpHeaders headers,
//            @NonNull HttpStatusCode status,
//            @NonNull WebRequest request) {
//        if (ex.hasErrors()) {
//            return ResponseEntity.unprocessableEntity().body(ResponseBuilder.error(422, getErrorWithErrorCode(ex.getAllErrors().getFirst().getDefaultMessage())));
//        }
//        return ResponseEntity.unprocessableEntity().body(ResponseBuilder.error(422,getErrorWithErrorCode("Request không hợp lệ")));
//    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        return ResponseEntity.badRequest().body(ResponseBuilder.error(400, getErrorWithErrorCode(ex.getMessage())));
    }

//    @ExceptionHandler({ConstraintViolationException.class})
//    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//
//        Set<String> messages = new HashSet<>(constraintViolations.stream()
//            .map(constraintViolation -> String.format(" %s", constraintViolation.getMessage()))
//            .toList());
//
//        log.debug("ConstraintViolationException " + messages);
//        return ResponseEntity.badRequest().body(ResponseBuilder.error(400, messages.toString()));
//    }
//
//    @ExceptionHandler({TimeOutProcess.class})
//    protected ResponseEntity<Object> handleTimeOut(TimeOutProcess e) {
//        BaseResponse<Object> responseDto = ResponseBuilder.error(99, getErrorWithErrorCode(TIMEOUT_PROCESS_ERROR));
//        log.error("", e);
//        return new ResponseEntity<>(responseDto, HttpStatus.REQUEST_TIMEOUT);
//    }

    @ExceptionHandler({CustomBadRequestException.class})
    protected ResponseEntity<Object> handleCustomBadRequest(CustomBadRequestException e) {
        BaseResponse<Object> responseDto = ResponseBuilder.error(400, getErrorWithErrorCode(e.getMessage()));
        log.error("", e);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    protected ResponseEntity<Object> handleServerError(Throwable e) {
        BaseResponse<Object> responseDto = ResponseBuilder.error(99, getErrorWithErrorCode(DEFAULT_ERROR));
        log.error("", e);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    protected ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException e) {
        BaseResponse<Object> responseDto = ResponseBuilder.error(99, getErrorWithErrorCode(e.getMessage()));
        log.error("", e);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SQLGrammarException.class})
    protected ResponseEntity<Object> handleSQLGrammarException(SQLGrammarException e) {
        BaseResponse<Object> responseDto = ResponseBuilder.error(100, "Đã có lỗi xảy ra vs CSDL");
        log.error("SQLGrammarException caught", e);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    public static String getErrorWithErrorCode(String message) {
//        String requestId = MDC.get(LoggingKey.REQUEST_ID_KEY);
        return String.format("%s.", message);
    }
}
