package com.kits.plugin.services.message;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message {

    GENERIC_NO_CONSOLE("Generic.No-Console"),

    CMD_KIT_SELECTED("Commands.ClaimKit.Messages.Selected"),
    CMD_KIT_ON_COOLDOWN("Commands.ClaimKit.Messages.Cooldown"),
    CMD_KIT_NO_PERMISSION("Commands.ClaimKit.Messages.No-Permission"),
    CMD_KIT_ENTER_KIT("Commands.ClaimKit.Messages.Enter-Kit"),
    CMD_KIT_NOT_A_KIT("Commands.ClaimKit.Messages.Not-A-Kit");


    @NonNull @Getter
    private final String path;
}
