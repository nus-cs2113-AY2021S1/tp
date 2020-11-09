package com.scrumptious.test;

import com.scrumptious.Scrumptious;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

/**
 * This class is for testing text-ui. It will 
 */
public class TextUiTest {
    public static void main(String[] args) {
        Instant fakeDateTime = Instant.parse("2020-10-10T00:00:00.000Z");
        Scrumptious.setClock(Clock.fixed(fakeDateTime, ZoneOffset.systemDefault()));
        Scrumptious.main(new String[0]);
    }
}
