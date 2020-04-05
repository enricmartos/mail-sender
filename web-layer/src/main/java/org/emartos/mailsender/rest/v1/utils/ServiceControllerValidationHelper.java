package org.emartos.mailsender.rest.v1.utils;


import org.emartos.mailsender.v1.exceptions.BadRequestException;

public class ServiceControllerValidationHelper {
    private final String serviceControllerName;

    public ServiceControllerValidationHelper(String serviceControllerName) {
        this.serviceControllerName = serviceControllerName;
    }

    public ServiceControllerValidationHelper checkNotNull(Object value, String parameterName) throws BadRequestException {
        if (value == null) {
            throw new BadRequestException(String.format("%s field %s can't be null", serviceControllerName, parameterName));
        }
        return this;
    }

    public ServiceControllerValidationHelper checkNotNullOrEmpty(String value, String parameterName) throws BadRequestException {
        checkNotNull(value, parameterName);
        if (value.isEmpty()) {
            throw new BadRequestException(String.format("%s field %s can't be empty", serviceControllerName, parameterName));
        }
        return this;
    }

}
