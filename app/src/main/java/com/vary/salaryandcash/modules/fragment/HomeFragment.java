package com.vary.salaryandcash.modules.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseHolder;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.PersonAdapter;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Person;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.view.MainView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
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

public class HomeFragment extends BaseSupportFragmentVertical implements MainView {
    private PersonAdapter foodAdapter;

    public static HomeFragment myFragment;
    public static synchronized HomeFragment getInstance(){
        if (myFragment == null){
            synchronized (LeftFragment.class){
                if (myFragment == null){
                    myFragment = new HomeFragment();
                }
            }
        }
        return myFragment;
    }
    TextView UniqueID,copyText;
    ClipData clipDacta;
    @Override
    protected void initView() {
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        mPresenter.getPerson();
        rv = (RecyclerView) mView.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        foodAdapter = new PersonAdapter(this);
        View view = View.inflate(getActivity(), R.layout.item_head, null);
        UniqueID = (TextView) view.findViewById(R.id.tv_info);
        copyText = (TextView) view.findViewById(R.id.tv_copy);
        foodAdapter.setHeadHolder(view);
        rv.setAdapter(foodAdapter);
        refreshCard();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EMClient.getInstance().isLoggedInBefore()) {
                    start(LoginFragment.getInstance());
                }
            }
        });
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        final ClipboardManager clipboard = (ClipboardManager)
                SalaryApplication.appContext.getSystemService(Context.CLIPBOARD_SERVICE);

        copyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = UniqueID.getText().toString();
                clipDacta = ClipData.newPlainText("text",text);
                clipboard.setPrimaryClip(clipDacta);
            }
        });


    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (!TextUtils.isEmpty(SalaryApplication.getSharedPreferences().getString("username", "username"))) {
            UniqueID.setText(SalaryApplication.getSharedPreferences().getString("username", "唯一ID"));
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_home;
    }

    void refreshCard() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("任务",R.drawable.icon_app));
        persons.add(new Person("群组",R.drawable.ic_group_black_24dp));
        persons.add(new Person("盯紧",R.drawable.ic_flag_black_24dp));
        persons.add(new Person("清扫",R.drawable.ic_delete_sweep_black_24dp));
        persons.add(new Person("设置",R.drawable.ic_settings_black_24dp));
        persons.add(new Person("意见反馈",R.drawable.ic_announcement_black_24dp));
        persons.add(new Person("帮助",R.drawable.ic_help_outline_black_24dp));
        foodAdapter.setDataList(persons);
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {

    }

    @Override
    public void onShowDialog(String s) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String s) {

    }

    @Override
    public void onClearItems() {

    }
    public String getPassword;
    public String getProduct;
    @Override
    public void onAccountLoaded(AccountResponse response) {
//        UniqueID.setText(response.getUniqueID());
        getPassword = response.getPassWord();
        getProduct = response.getProduct();
    }
}
