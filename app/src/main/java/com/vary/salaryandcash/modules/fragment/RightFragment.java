package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.base.BaseSupportFragmentVertical;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.SalaryAdapter;
import com.vary.salaryandcash.modules.holder.GroupHolder;
import com.vary.salaryandcash.modules.holder.MainHolder;
import com.vary.salaryandcash.modules.widget.ReDiscreteScrollView;
import com.vary.salaryandcash.mvp.model.AccountResponse;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.view.MainView;
import com.vary.salaryandcash.utilities.ColorUtils;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

public class RightFragment extends BaseSupportFragmentVertical implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener, MainView {
    private TextView currentItemPrice;
    private ReDiscreteScrollView itemPicker;
    private CircleImageView personHeard;
    private static RightFragment myFragment ;
    public static synchronized RightFragment getInstance(int position){
        if (myFragment == null){
            synchronized (RightFragment.class){
                if (myFragment == null){
                    myFragment = new RightFragment();
                }
            }
        }
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    protected void initView() {
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        currentItemPrice = (TextView) mView.findViewById(R.id.item_price);
        personHeard = (CircleImageView) mView.findViewById(R.id.iv_person);
        itemPicker = (ReDiscreteScrollView) mView.findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.setOnItemChangedListener(this);
    }

    @Override
    public int getBaseView() {
        return R.layout.fragment_shop;
    }

    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.getSalaries();
        mCakeAdapter = new SalaryAdapter(getLayoutInflater(savedInstanceState)) {
            @Override
            public int getView() {
                return R.layout.item_shop_card;
            }

            @Override
            public GroupHolder getHolder() {
                GroupHolder mainHolder = new GroupHolder(mCakeAdapter.mView);
                return mainHolder;
            }
        };
        itemPicker.setAdapter(mCakeAdapter);
//        //    itemPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                showUnsupportedSnackBar();
                break;
        }
    }
    int customizedColor = ColorUtils.CustomizedColors()[ColorUtils.getInstance().nextInt(ColorUtils.CustomizedColors().length)];
    private void onItemChanged(Salary item) {
        currentItemPrice.setText(item.getPreviewDescription()+ ".00");
//        personHeard.set(item.getPreviewDescription()+ ".00");
        Glide.with(getActivity()).load(item.getMicroVideo())
                .placeholder(customizedColor)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(personHeard);

    }

    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        onItemChanged(mSalaries.get(position));
    }

    private void showUnsupportedSnackBar() {
  //      Snackbar.make(itemPicker, R.string.msg_unsupported_op, Snackbar.LENGTH_SHORT).show();
    }

    List<Salary> mSalaries;
    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        mSalaries = salaries;
        mCakeAdapter.addCakes(mSalaries);
        if (mSalaries.size()>0){
            currentItemPrice.setVisibility(View.VISIBLE);
            personHeard.setVisibility(View.VISIBLE);
            onItemChanged(mSalaries.get(0));
        }
        itemPicker.scrollToPosition(1);

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
        mCakeAdapter.clearCakes();
    }

    @Override
    public void onAccountLoaded(AccountResponse response) {

    }
}
