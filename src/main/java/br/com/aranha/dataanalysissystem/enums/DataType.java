package br.com.aranha.dataanalysissystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum DataType {
    SALESMAN("001"),
    CUSTOMER("002"),
    SALES("003"),
    INVALID("-1");

    private final String lineTypeId;

    public static DataType getByLineTypeId(String lineTypeId) {
        return Stream.of(values())
                .filter(id -> id.lineTypeId.equalsIgnoreCase(lineTypeId))
                .findFirst()
                .orElse(INVALID);
    }
}
