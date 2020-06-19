package com.sunny.ddangnmarket.domain.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@RequiredArgsConstructor
public enum Status {

    SELLING("판매중", "selling"),
    SOLD_OUT("판매완료", "soldout");

    private static final Map<String, Status> QEURY_STRING_ENUM_MAP;

    private final String text;
    private final String queryString;

    static {
        Map<String, Status> qeuryStringMap = new ConcurrentHashMap<String, Status>();
        for (Status instance : Status.values()) {
            qeuryStringMap.put(instance.queryString, instance);
        }
        QEURY_STRING_ENUM_MAP = Collections.unmodifiableMap(qeuryStringMap);
    }

    public static Status getFromQueryString(String queryString) {
        return QEURY_STRING_ENUM_MAP.get(queryString);
    }
}
