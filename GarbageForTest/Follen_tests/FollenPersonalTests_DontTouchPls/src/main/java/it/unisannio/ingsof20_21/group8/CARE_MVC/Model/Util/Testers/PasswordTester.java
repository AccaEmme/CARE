package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;

public class PasswordTester {
    public static void main(String[] args) {
        String pw = Password.getMd5("password_di_peppe");
        System.out.println(pw);
    }
}
