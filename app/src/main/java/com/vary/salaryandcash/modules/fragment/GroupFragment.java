package com.vary.salaryandcash.modules.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.vary.salaryandcash.R;
import com.vary.salaryandcash.base.BaseSupportFragment;
import com.vary.salaryandcash.modules.adapter.PostAdapter;
import com.vary.salaryandcash.mvp.model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017-06-09.
 */

public class GroupFragment extends BaseSupportFragment {

    private RecyclerView mRvPostLister;
    private PostAdapter mPostAdapter;

    private List<Post> mPostList;
    private String[] IMG_URL_LIST = {
            "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg",
            "https://pic4.zhimg.com/fc04224598878080115ba387846eabc3_xll.jpg",
            "https://pic3.zhimg.com/d1750bd47b514ad62af9497bbe5bb17e_xll.jpg",
            "https://pic4.zhimg.com/da52c865cb6a472c3624a78490d9a3b7_xll.jpg",
            "https://pic3.zhimg.com/0c149770fc2e16f4a89e6fc479272946_xll.jpg",
            "https://pic1.zhimg.com/76903410e4831571e19a10f39717988c_xll.png",
            "https://pic3.zhimg.com/33c6cf59163b3f17ca0c091a5c0d9272_xll.jpg",
            "https://pic4.zhimg.com/52e093cbf96fd0d027136baf9b5cdcb3_xll.png",
            "https://pic3.zhimg.com/f6dc1c1cecd7ba8f4c61c7c31847773e_xll.jpg",
    };

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_app, container, false);
        TextView app_title = (TextView) view.findViewById(R.id.app_title);
        app_title.setText("群组");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRvPostLister = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRvPostLister.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mPostList = new ArrayList<>();
        List<String> imgUrls = new ArrayList<>();
        imgUrls.addAll(Arrays.asList(IMG_URL_LIST));
        for (int i = 0; i < 180; i++) {
            Post post = new Post("看图，字不重要。想看大图？抱歉我还没做这个 ( •̀ .̫ •́ )", imgUrls);
            mPostList.add(post);
        }

        mPostAdapter = new PostAdapter(getActivity(), mPostList, NineGridImageView.STYLE_GRID);
        mRvPostLister.setAdapter(mPostAdapter);
    }
}