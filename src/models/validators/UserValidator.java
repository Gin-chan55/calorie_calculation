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

        String password_error = validateContent(m.getPassword());
        if(!password_error.equals("")) {
            errors.add(password_error);
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

    // メッセージの必須入力チェック
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "メッセージを入力してください。";
        }

        return "";
    }
}
