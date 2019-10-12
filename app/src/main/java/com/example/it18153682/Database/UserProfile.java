package com.example.it18153682.Database;

import android.provider.BaseColumns;

public class UserProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COL1 = "userName";
        public static final String COL2 = "dateOfBirth";
        public static final String COL3 = "Gender";
        public static final String COL4 = "Password";

    }
}
