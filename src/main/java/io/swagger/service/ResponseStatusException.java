package io.swagger.service;

import org.springframework.http.HttpStatus;

public class ResponseStatusException extends RuntimeException {
        HttpStatus status;
        java.lang.String reason;
        java.lang.Throwable cause;

    public ResponseStatusException() {
    }

    public ResponseStatusException(HttpStatus status) {
    }

    public ResponseStatusException(HttpStatus status, java.lang.String reason)
        {
            this.status = status;
            this.reason = reason;
        }

        ResponseStatusException(
                HttpStatus status,
                java.lang.String reason,
                java.lang.Throwable cause
        ) {
        this.status = status;
        this.reason = reason;
        this.cause = cause;
    }

        public HttpStatus getStatus () {
        return status;
    }

        public void setStatus (HttpStatus status){
        this.status = status;
    }

        public String getReason () {
        return reason;
    }

        public void setReason (String reason){
        this.reason = reason;
    }

        public Throwable getCause () {
        return cause;
    }

        public void setCause (Throwable cause){
        this.cause = cause;
    }
}
