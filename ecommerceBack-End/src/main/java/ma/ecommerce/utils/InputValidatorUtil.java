package ma.ecommerce.utils;

public class InputValidatorUtil {
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
    public static boolean isValidPhone(String phoneNumber) {
        String regex = "^0\\d{9,14}$";
        return phoneNumber.matches(regex);
    }
    public static boolean isValidName(String name) {
        String regex =  "^[a-zA-Z\\s]+$";
        return name.matches(regex);
    }
}
