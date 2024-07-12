package com.test.iasapi.commons;

public interface UseCase<Input, Output>{
    Output execute(Input input);
}
