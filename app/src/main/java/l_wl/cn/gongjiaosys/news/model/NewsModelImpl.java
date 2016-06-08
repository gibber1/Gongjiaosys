package l_wl.cn.gongjiaosys.news.model;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;
import l_wl.cn.gongjiaosys.bean.news;

/**
 * Created by l_wl on 2016/6/5.
 */
public class NewsModelImpl implements NewsModel {

    private int limit = 10;		// 每页的数据是10条

    @Override
    public void loadNews(Context context, int type, int page, String lastTime,final OnLoadNewsListListener listener) {
        BmobQuery<news> query = new BmobQuery<news>();
        query.order("-createdAt");
        query.addWhereEqualTo("type", type+"");
        if(page>0){
            // 处理时间查询
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 只查询小于等于最后一个item发表时间的数据
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
        }
        else{
            page=0;
            query.setSkip(page);

            news test=new news();
            test.setType(type+"");
            test.setDigest("刷新测试");
            test.setTitle("刷新测试");
            test.save(context);
        }
        query.setLimit(limit);
        query.findObjects(context, new FindListener<news>() {
            @Override
            public void onSuccess(List<news> list) {
                if(list.size()>0){
                    listener.onSuccess(list);
                }
                else {
                    listener.onFailure("没有更多数据", null);
                }
                Log.i("lwl", "modelload_success_listsize:"+list.size());
            }

            @Override
            public void onError(int i, String s) {
                listener.onFailure("加载数据失败："+s, null);
                Log.i("lwl", "modelload_error" + s);
            }
        });
    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<news> list);

        void onFailure(String msg, Exception e);
    }

}
