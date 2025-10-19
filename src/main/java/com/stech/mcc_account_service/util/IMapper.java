package com.stech.mcc_account_service.util;

public interface IMapper<T> {

    T getDto();

    void setData(T t);
}
