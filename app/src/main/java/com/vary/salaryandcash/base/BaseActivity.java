package com.vary.salaryandcash.base;



        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.CallSuper;
        import android.support.annotation.Nullable;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;


        import com.vary.salaryandcash.app.SalaryApplication;
        import com.vary.salaryandcash.di.components.ApplicationComponent;
        import com.vary.salaryandcash.modules.widget.ViewServer;

        import butterknife.ButterKnife;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/24/2016
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ViewServer.get(this).addWindow(this);
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    protected void resolveDaggerDependency() {}

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((SalaryApplication) getApplication()).getApplicationComponent();
    }

    protected abstract int getContentView();

    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}