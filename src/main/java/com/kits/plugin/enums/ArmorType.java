package com.kits.plugin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArmorType {

    BOOTS(36),
    LEGGINGS(37),
    CHESTPLATE(38),
    HELMET(39);

    private final int slot;
}
