package com.luxoft.unit;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:test.properties")
public interface ConfigProps extends Config{
    @Config.Key("data.age")
    @DefaultValue("10")
    int age();
    @Config.Key("data.response")
    @DefaultValue("no")
    String response();

}
