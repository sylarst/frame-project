package com.sub.login_module.select;

import android.widget.ImageView;

import com.age.mac.baselibrary.base.quickadapter.BaseMultiItemQuickAdapter;
import com.age.mac.baselibrary.base.quickadapter.BaseViewHolder;
import com.age.mac.baselibrary.base.quickadapter.entity.MultiItemEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sub.login_module.Constants;
import com.sub.login_module.R;

import java.util.List;

/**
 * Created by mac on 2019/8/5.
 */

public class Select2Adapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public Select2Adapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(Constants.SELECT_IMAGE, R.layout.select_item0);
        addItemType(Constants.SELECT_TEXT, R.layout.select_item1);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()){
            case Constants.SELECT_IMAGE:
                bindImage(helper,item);
                break;
            case Constants.SELECT_TEXT:
                bindText(helper,item);
                break;
        }
    }

    private void bindImage(BaseViewHolder helper, MultiItemEntity item){
        SpinnearBean bean= (SpinnearBean) item;
        ImageView imageView = helper.getView(R.id.image);
        Glide.with(imageView.getContext()).load(R.drawable.ic_launcher).into(imageView);
    }

    private void bindText(BaseViewHolder helper, MultiItemEntity item){
        SpinnearBean bean= (SpinnearBean) item;
        helper.setText(R.id.text,bean.getParaName());
    }

}
