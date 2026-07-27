package com.bookit.bookit.security;

/**
 * In den SecurityConstants definieren wir statische Werte, welche zum Beispiel zum Token gehören.
 * Dinge wie Ablaufdatum, Header-String (wo erwarten wir das Token später) usw.
 */
public class SecurityConstants {
    public static final long EXPIRATION_TIME = 86400000L; // 1 Day in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}