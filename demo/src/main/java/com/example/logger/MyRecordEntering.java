package com.example.logger;

public record MyRecordEntering (Object[] params, Object thisObject, boolean isStatic){} // mi sembra si possa fare a meno di isStatic assegnando null al parametro thisObject 
// stesso commento per MyRecordExiting
