package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vary.salaryandcash.R;
import com.vary.salaryandcash.app.SalaryApplication;
import com.vary.salaryandcash.di.components.DaggerSalaryComponent;
import com.vary.salaryandcash.di.module.SalaryModule;
import com.vary.salaryandcash.modules.adapter.ShopAdapter;
import com.vary.salaryandcash.mvp.model.Salary;
import com.vary.salaryandcash.mvp.presenter.SalaryPresenter;
import com.vary.salaryandcash.mvp.view.MainView;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017-04-21.
 */

public class RightFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener, MainView {
    @Inject
    protected SalaryPresenter mPresenter;

    private TextView currentItemPrice;
    private DiscreteScrollView itemPicker;
    private ShopAdapter shopAdapter;

    public static RightFragment getInstance(int position){
        RightFragment myFragment = new RightFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_shop, container, false);
        DaggerSalaryComponent.builder()
                .applicationComponent(((SalaryApplication) (getActivity().getApplication())).getApplicationComponent())
                .salaryModule(new SalaryModule(this))
                .build().inject(this);
        mPresenter.getSalaries();
        //   textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //         textView.setText("The page Selected is "+bundle.getInt("position"));
        }
        currentItemPrice = (TextView) layout.findViewById(R.id.item_price);
        itemPicker = (DiscreteScrollView) layout.findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.setOnItemChangedListener(this);
        shopAdapter = new ShopAdapter();
        itemPicker.setAdapter(shopAdapter);
 //       itemPicker.setAdapter(new ShopAdapter(data));
//        //    itemPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

//        onItemChanged(data.get(0));
        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                showUnsupportedSnackBar();
                break;
        }
    }

    private void onItemChanged(Salary item) {
        currentItemPrice.setText(item.getId());
    }

    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
//        onItemChanged(data.get(position));
    }

    private void showUnsupportedSnackBar() {
  //      Snackbar.make(itemPicker, R.string.msg_unsupported_op, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSalaryLoaded(List<Salary> salaries) {
        shopAdapter.addItems(salaries);
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
        shopAdapter.clearDatas();
    }
}
