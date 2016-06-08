package l_wl.cn.gongjiaosys.news.model;

import android.content.Context;
import android.support.v4.content.ContentResolverCompat;

/**
 * Created by l_wl on 2016/6/5.
 */
public interface NewsModel {

    void loadNews(Context context,int type, int page,String lasttime,NewsModelImpl.OnLoadNewsListListener listener);

}
