package com.example.record.loginfo;

import java.util.List;

public interface LogInfoRecordInterface {
    String event();

    Object target();

    List<Object[]>  args();

    String name();
}
