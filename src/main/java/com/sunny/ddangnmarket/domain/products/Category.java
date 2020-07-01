package com.sunny.ddangnmarket.domain.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@RequiredArgsConstructor
public enum Category {

    SPORTS_N_LEISURE("스포츠/레저"),
    DIGITAL_PRODUCTS("다지털/가전"),
    INTERIOR("가구/인테리어"),
    BABY("유아동/유아도서"),
    LIFE_N_FOOD("생활/가공식품"),
    CLOTHING("옷"),
    GAME_N_HOBBY("게임/취미"),
    BEAUTY("뷰티/미용"),
    PET("반려동물용품"),
    BOOKS_N_TICKET_N_ALBUM("도서/티켓/음반"),
    ETC("기타"),
    BUY("삽니다");

    private static final Map<String, Category> ENUM_MAP;

    private final String text;

    static {
        Map<String, Category> map = new ConcurrentHashMap<String, Category>();
        for (Category instance : Category.values()) {
            map.put(instance.text, instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Category get(String name) {
        return ENUM_MAP.get(name);
    }
}
