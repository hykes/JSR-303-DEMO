package com.github.hykes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {

    WAIT(0, "待处理") {
        @Override
        public Integer id() {
            return 1;
        }
    },

    APPROVE_ING(1, "审核中") {
        @Override
        public Integer id() {
            return 1;
        }
    },

    APPROVE_SUCCESS(4, "已审核") {
        @Override
        public Integer id() {
            return 4;
        }
    };

    private int value;
    private String description;


    public abstract Integer id();

    public Integer code() {
        return value;
    }

    /**
     * 获取枚举
     * @param value 枚举值
     * @return Optional
     */
    public static Optional<ItemStatus> fromInt(Integer value) {
        return Arrays.stream(values())
                .filter(e -> Objects.equals(value, e.value)).findFirst();
    }
}
