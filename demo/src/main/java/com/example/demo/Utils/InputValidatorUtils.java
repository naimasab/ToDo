package com.example.demo.Utils;

import com.example.demo.Entity.Status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InputValidatorUtils {
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    /// ici

        public static boolean isValidStatus(Status status) {
            return status.toString().isBlank();
        }





    public static boolean isStringEmpty(String value){
        return (value.trim().isEmpty());
    }
    public static boolean isFueDateIsValid(Date date) {
        if (date != null) {
            LocalDate dueDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return LocalDate.now().isBefore(dueDate);
        } else {
            return false;
        }
    }
}
