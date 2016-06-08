package l_wl.cn.gongjiaosys.news.presenter;

import android.content.Context;

/**
 * Created by l_wl on 2016/6/5.
 */
public interface NewsPresenter {

    void loadNews(Context context,int type, int page,String lasttime);

}
