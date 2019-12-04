package com.github.hykes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {

    WAIT(0, "待处理"),

    APPROVE_ING(1, "审核中"),

    APPROVE_SUCCESS(4, "已审核");

    private int value;
    private String description;

    /**
     * 获取枚举
     *
     * @param value 枚举值
     * @return Optional
     */
    public static Optional<ItemType> fromInt(Integer value) {
        return Arrays.stream(values())
                .filter(e -> Objects.equals(value, e.value)).findFirst();
    }
}
