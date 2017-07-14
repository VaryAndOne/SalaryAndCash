package com.vary.salaryandcash.modules.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;

import butterknife.Bind;
import butterknife.ButterKnife;
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

public class LoginFragment extends BaseSupportFragmentVertical {
    @Bind(R.id.ec_edit_username)
    EditText mUsernameEdit;
    @Bind(R.id.ec_edit_password)
    EditText mPasswordEdit;
    @Bind(R.id.ec_btn_sign_in)
    TextView mSignInBtn;
    // 注册按钮
    private TextView mSignUpBtn;


    public static LoginFragment myFragment;

    public static synchronized LoginFragment getInstance(){
        if (myFragment == null){
            synchronized (LoginFragment.class){
                if (myFragment == null){
                    myFragment = new LoginFragment();
                }
            }
        }
        return myFragment;
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_login;
    }
    @Override
    protected void initView() {
        mView.findViewById(R.id.navigate_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mUsernameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager im = (InputMethodManager) SalaryApplication.appContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        mPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager im = (InputMethodManager) SalaryApplication.appContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        mSignUpBtn = (TextView) mView.findViewById(R.id.ec_btn_sign_up);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    /**
     * 注册方法
     */
    private void signUp() {
        // 注册是耗时过程，所以要显示一个dialog来提示下用户
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("注册中，请稍后...");
        mDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String username = mUsernameEdit.getText().toString().trim();
                    String password = mPasswordEdit.getText().toString().trim();
                    EMClient.getInstance().createAccount(username, password);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if (!ECLoginActivity.this.isFinishing()) {
//                                mDialog.dismiss();
//                            }
                            Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if (!ECLoginActivity.this.isFinishing()) {
//                                mDialog.dismiss();
//                            }
                            /**
                             * 关于错误码可以参考官方api详细说明
                             * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                             */
                            int errorCode = e.getErrorCode();
                            String message = e.getMessage();
                            Log.d("lzan13", String.format("sign up - errorCode:%d, errorMsg:%s", errorCode, e.getMessage()));
//                            switch (errorCode) {
//                                // 网络错误
//                                case EMError.NETWORK_ERROR:
//                                    Toast.makeText(ECLoginActivity.this, "网络错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                                // 用户已存在
//                                case EMError.USER_ALREADY_EXIST:
//                                    Toast.makeText(ECLoginActivity.this, "用户已存在 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                                // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
//                                case EMError.USER_ILLEGAL_ARGUMENT:
//                                    Toast.makeText(ECLoginActivity.this, "参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                                // 服务器未知错误
//                                case EMError.SERVER_UNKNOWN_ERROR:
//                                    Toast.makeText(ECLoginActivity.this, "服务器未知错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                                case EMError.USER_REG_FAILED:
//                                    Toast.makeText(ECLoginActivity.this, "账户注册失败 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                                default:
//                                    Toast.makeText(ECLoginActivity.this, "ml_sign_up_failed code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
//                                    break;
//                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    // 弹出框
    private ProgressDialog mDialog;
    /**
     * 登录方法
     */
    private void signIn() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("正在登陆，请稍后...");
        mDialog.show();
        final String username = mUsernameEdit.getText().toString().trim();
        String password = mPasswordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名和密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        EMClient.getInstance().login(username, password, new EMCallBack() {
            /**
             * 登陆成功的回调
             */
            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        // 加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();
                        // 加载所有群组到内存，如果使用了群组的话
                        // EMClient.getInstance().groupManager().loadAllGroups();
                        // 登录成功跳转界面
                        SharedPreferences.Editor editor  = SalaryApplication.getSharedPreferences().edit();
                        editor.putString("username", username);
                        editor.commit();
                        pop();
                    }
                });
            }

            /**
             * 登陆错误的回调
             * @param i
             * @param s
             */
            @Override
            public void onError(final int i, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        Log.d("lzan13", "登录失败 Error code:" + i + ", message:" + s);
                        /**
                         * 关于错误码可以参考官方api详细说明
                         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                         */
//                        switch (i) {
//                            // 网络异常 2
//                            case EMError.NETWORK_ERROR:
//                                Toast.makeText(ECLoginActivity.this, "网络错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 无效的用户名 101
//                            case EMError.INVALID_USER_NAME:
//                                Toast.makeText(ECLoginActivity.this, "无效的用户名 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 无效的密码 102
//                            case EMError.INVALID_PASSWORD:
//                                Toast.makeText(ECLoginActivity.this, "无效的密码 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 用户认证失败，用户名或密码错误 202
//                            case EMError.USER_AUTHENTICATION_FAILED:
//                                Toast.makeText(ECLoginActivity.this, "用户认证失败，用户名或密码错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 用户不存在 204
//                            case EMError.USER_NOT_FOUND:
//                                Toast.makeText(ECLoginActivity.this, "用户不存在 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 无法访问到服务器 300
//                            case EMError.SERVER_NOT_REACHABLE:
//                                Toast.makeText(ECLoginActivity.this, "无法访问到服务器 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 等待服务器响应超时 301
//                            case EMError.SERVER_TIMEOUT:
//                                Toast.makeText(ECLoginActivity.this, "等待服务器响应超时 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 服务器繁忙 302
//                            case EMError.SERVER_BUSY:
//                                Toast.makeText(ECLoginActivity.this, "服务器繁忙 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            // 未知 Server 异常 303 一般断网会出现这个错误
//                            case EMError.SERVER_UNKNOWN_ERROR:
//                                Toast.makeText(ECLoginActivity.this, "未知的服务器异常 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                            default:
//                                Toast.makeText(ECLoginActivity.this, "ml_sign_in_failed code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
//                                break;
//                        }
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
