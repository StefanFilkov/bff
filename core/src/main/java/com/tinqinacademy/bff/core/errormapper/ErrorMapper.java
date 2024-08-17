package com.tinqinacademy.bff.core.errormapper;


import com.tinqinacademy.bff.api.errors.Errors;

public interface ErrorMapper {
     Errors mapErrors(Throwable throwable);
}
