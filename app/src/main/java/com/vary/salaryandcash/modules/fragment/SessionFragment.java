package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SharedElementCallback;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.MainActivity;

import butterknife.ButterKnife;

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

public class SessionFragment extends BaseSupportFragment {
    public static SessionFragment myFragment;
    public static synchronized SessionFragment getInstance(String chatId){
        if (myFragment == null){
            synchronized (SessionFragment.class){
                if (myFragment == null){
                    myFragment = new SessionFragment();
                }
            }
        }
        Bundle args = new Bundle();
        args.putString(EaseConstant.EXTRA_USER_ID,chatId);
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        myFragment.setArguments(args);
        return myFragment;
    }

    public static EaseChatFragment easeChatFragment;
    public static synchronized EaseChatFragment getEaseChatFragment(){
        if (easeChatFragment == null){
            synchronized (EaseChatFragment.class){
                if (easeChatFragment == null){
                    easeChatFragment = new EaseChatFragment();
                }
            }
        }
        easeChatFragment.setArguments(myFragment.getArguments());
        return easeChatFragment;
    }
    private  EaseChatFragment chatFragment;
    @Override
    protected void initView() {
        app_title = (TextView) mView.findViewById(R.id.app_title);
        app_title.setText("聊天");
        chatFragment = getEaseChatFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_session;
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        ButterKnife.unbind(this);
        ((MainActivity) getActivity()).unregisterMyOnTouchListener(onTouchListener);
        getActivity().getSupportFragmentManager().beginTransaction().remove(chatFragment).commit();
    }

}
