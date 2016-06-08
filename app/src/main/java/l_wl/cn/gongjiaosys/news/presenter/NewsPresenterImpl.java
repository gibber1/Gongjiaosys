package l_wl.cn.gongjiaosys.news.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import l_wl.cn.gongjiaosys.bean.news;
import l_wl.cn.gongjiaosys.news.model.NewsModel;
import l_wl.cn.gongjiaosys.news.model.NewsModelImpl;
import l_wl.cn.gongjiaosys.news.view.NewsView;

/**
 * Created by l_wl on 2016/6/5.
 */
public class NewsPresenterImpl implements NewsPresenter,NewsModelImpl.OnLoadNewsListListener{

    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(Context context,int type, int page,String lasttime) {
        if(page == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(context,type,page,lasttime,this);
        Log.i("lwl","presenterload");
    }

    @Override
    public void onSuccess(List<news> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg(msg);
    }
}
