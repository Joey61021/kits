package com.kits.plugin.cooldowns;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class Cooldown {

    public long     timestamp;
    public TimeSpan timeSpan;
}
