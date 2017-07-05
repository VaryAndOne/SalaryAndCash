package com.vary.salaryandcash.mvp.view;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import java.util.List;
/**
 * Created by
 *
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 *
 * on 2017-06-03.
 */

public interface MainView extends BaseView{
    void onSalaryLoaded(List<Salary> salaries);

    void onShowDialog(String s);

    void onHideDialog();

    void onShowToast(String s);

    void onClearItems();

    void onAccountLoaded(AccountResponse response);
}
