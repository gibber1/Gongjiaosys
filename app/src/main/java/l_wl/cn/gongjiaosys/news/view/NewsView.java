package l_wl.cn.gongjiaosys.news.view;

import java.util.List;

import l_wl.cn.gongjiaosys.bean.news;

/**
 * Created by l_wl on 2016/6/5.
 */

public interface NewsView {

    void showProgress();

    void addNews(List<news> newsList);

    void hideProgress();

    void showLoadFailMsg(String msg);
}

