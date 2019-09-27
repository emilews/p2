package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user;

import java.util.List;

public interface UserValidator<T> {
    List<String> validate(T info);
}
