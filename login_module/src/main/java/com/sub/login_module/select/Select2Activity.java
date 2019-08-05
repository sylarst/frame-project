package com.sub.login_module.select;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.age.mac.baselibrary.base.quickadapter.BaseQuickAdapter;
import com.sub.login_module.R;

import net.caixiaomi.smartrefresh.layout.SmartRefreshLayout;
import net.caixiaomi.smartrefresh.layout.api.RefreshLayout;
import net.caixiaomi.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2019/8/5.
 */

public class Select2Activity extends Activity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener{

    RecyclerView mListView;
    Select2Adapter mAdapter;
    private View headerView;
    SmartRefreshLayout mRefresh;
    private int mPageNum = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select2_layout);
        mListView=findViewById(R.id.list);
        mRefresh=findViewById(R.id.refresh);
        headerView=View.inflate(this,R.layout.header_view,null);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Select2Adapter(null);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this, mListView);
        mAdapter.addHeaderView(headerView);
        mAdapter.setEnableLoadMore(true);
        mListView.setAdapter(mAdapter);
        loadData();
    }

    private void loadData(){
        mRefresh.finishRefresh();
        List<SpinnearBean>list=new ArrayList<>();
        for(int i=0;i<10;i++){
            SpinnearBean bran=new SpinnearBean();
            bran.setParaName("堵塞"+i);
            bran.setType(i%2);
            list.add(bran);
        }

        if (list != null && !list.isEmpty()) {
            if (mPageNum == 1) {
                mAdapter.getData().clear();
            }
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
            mAdapter.notifyDataSetChanged();
            mAdapter.setEnableLoadMore(mPageNum < 10);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPageNum++;
        loadData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNum=1;
        loadData();
    }
}
