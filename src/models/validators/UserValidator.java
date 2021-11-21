package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;


public class UserValidator {
 // バリデーションを実行する
    public static List<String> validate(User m, EntityManager em) {
        List<String> errors = new ArrayList<String>();

        String address_error = validateAddress(m.getAddress());
        if(!address_error.equals("")) {
            errors.add(address_error);
        }

        // ☆同じメールアドレスが登録されている場合はエラー
        String address = m.getAddress();

        long user_count = (long) em.createNamedQuery("getUserCountByAddress", Long.class)
                .setParameter("address", address)
                .getSingleResult();
        if (user_count > 0) {
            errors.add("メールアドレスが重複しています。");
        }

        String password_error = validatePassword(m.getPassword());
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        String height_error = validateHeight(m.getHeight());
        if(!height_error.equals("")) {
            errors.add(height_error);
        }

        String weight_error = validateWeight(m.getWeight());
        if(!weight_error.equals("")) {
            errors.add(weight_error);
        }

        return errors;
    }

    // メールアドレスの必須入力チェック
    private static String validateAddress(String address) {
        if(address == null || address.equals("")) {
            return "メールアドレスを入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password) {
        if(password == null || password.equals("")) {
            return "パスワードを入力してください。";
        }

        return "";
    }
    // 身長の必須入力チェック
    private static String validateHeight(String height) {
        if(height == null || height.equals("")) {
            return "身長を入力してください。";
        }

        return "";
    }
    // 体重の必須入力チェック
    private static String validateWeight(String weight) {
        if(weight == null || weight.equals("")) {
            return "体重を入力してください。";
        }

        return "";
    }

}
