package com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.user;

import com.javacodegeeks.patterns.proxypattern.remoteproxy.validator.StoreValidator;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public class UserDataValidator {
    List<StoreValidator> validators = new ArrayList<>();
}
