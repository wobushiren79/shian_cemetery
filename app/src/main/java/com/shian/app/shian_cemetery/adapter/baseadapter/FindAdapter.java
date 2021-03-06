package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.WebActivity;
import com.shian.app.shian_cemetery.appenum.SystemTypeEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpmodel.SiftListData;
import com.shian.app.shian_cemetery.http.phpparams.HpFindSaveParams;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;

import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/11.
 */

public class FindAdapter extends BaseAdapter {
    Context context;
    List<SiftListData> listDatas;

    public FindAdapter(Context context, List<SiftListData> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
    }

    public void setData(List<SiftListData> listDatas) {
        this.listDatas = listDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_find_items, null);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvCollection = (TextView) convertView.findViewById(R.id.tv_collection);
            holder.tvPraise = (TextView) convertView.findViewById(R.id.tv_praise);
            holder.ivCollection = (ImageView) convertView.findViewById(R.id.iv_collection);
            holder.ivPraise = (ImageView) convertView.findViewById(R.id.iv_praise);
            holder.llCollection = (LinearLayout) convertView.findViewById(R.id.ll_collection);
            holder.llPraise = (LinearLayout) convertView.findViewById(R.id.ll_praise);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final SiftListData data = listDatas.get(position);
        ImageLoader.getInstance().displayImage(BaseURL.PHP_BaseUrl + data.getImg(), holder.ivPic);
        holder.tvTitle.setText(data.getTitle());
        holder.tvTime.setText(data.getTime());
        holder.tvCollection.setText(data.getCollectionNum() + "");
        holder.tvPraise.setText(data.getPraiseNum() + "");

        holder.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra(IntentName.INTENT_URL, BaseURL.siftsPHPURL + "?id=" + data.getId());
                intent.putExtra("isCollection", true);
                intent.putExtra("shareData", data);
                context.startActivity(intent);
            }
        });

        if (data.getIsCollection() == 0) {
            holder.ivCollection.setImageResource(R.drawable.zhy_find_collection_1);
            holder.llCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setIsCollection(1);
                    int collectionNum = data.getCollectionNum();
                    data.setCollectionNum(++collectionNum);
                    holder.ivCollection.setOnClickListener(null);
                    setData(2, data.getId());
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.ivCollection.setImageResource(R.drawable.zhy_find_collection_2);
            holder.llCollection.setOnClickListener(null);
        }

        if (data.getIsPraise() == 0) {
            holder.ivPraise.setImageResource(R.drawable.zhy_find_praise_1);
            holder.llPraise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setIsPraise(1);
                    holder.ivPraise.setImageResource(R.drawable.zhy_find_praise_2);
                    int praiseNum = data.getPraiseNum();
                    data.setPraiseNum(++praiseNum);
                    holder.ivPraise.setOnClickListener(null);
                    setData(1, data.getId());
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.ivPraise.setImageResource(R.drawable.zhy_find_praise_2);
            holder.llPraise.setOnClickListener(null);
        }
        return convertView;
    }


    /**
     * @param type （1.为点赞   2.为收藏）
     */
    private void setData(int type, int siftID) {
        if (AppData.systemLoginInfo == null || AppData.systemLoginInfo.getUserId() == null) {
            ToastUtils.showShortToast(context, "数据错误，请重新登录");
            Utils.jumpLogin(context);
            return;
        }
        HpFindSaveParams params = new HpFindSaveParams();
        params.setType(type);
        params.setUserid(AppData.systemLoginInfo.getUserId());
        params.setSiftid(siftID);
        params.setUserType(SystemTypeEnum.cemetery.getCode());
        MHttpManagerFactory.getPHPManager().setSiftData(context, params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    class ViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        TextView tvTime;

        TextView tvCollection;
        TextView tvPraise;
        ImageView ivCollection;
        ImageView ivPraise;

        LinearLayout llCollection;
        LinearLayout llPraise;
    }
}
