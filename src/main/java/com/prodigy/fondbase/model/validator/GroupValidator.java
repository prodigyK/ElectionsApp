package com.prodigy.fondbase.model.validator;

import com.prodigy.fondbase.model.security.Group;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

public class GroupValidator implements org.springframework.validation.Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Group.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "group.name.empty");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "group.description.empty");
    }
}
