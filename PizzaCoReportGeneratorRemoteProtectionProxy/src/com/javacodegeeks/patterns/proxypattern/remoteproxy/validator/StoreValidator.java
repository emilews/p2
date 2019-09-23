package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator;

import java.util.List;

public interface StoreValidator<T> {
    List<String> validate(T info);
}
