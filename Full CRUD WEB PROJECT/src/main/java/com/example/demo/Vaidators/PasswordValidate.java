package com.example.demo.Vaidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidate {
    public static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String passwd) {
        Matcher matcher = pattern.matcher(passwd);
        return matcher.matches();
    }
}