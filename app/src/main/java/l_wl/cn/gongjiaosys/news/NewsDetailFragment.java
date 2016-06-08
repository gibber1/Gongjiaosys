package l_wl.cn.gongjiaosys.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import l_wl.cn.gongjiaosys.R;
import l_wl.cn.gongjiaosys.bean.news;
import l_wl.cn.gongjiaosys.ui.BaseBackFragment;
import l_wl.cn.gongjiaosys.utils.ImageLoaderUtils;

/**
 * Created by l_wl on 2016/6/7.
 */
public class NewsDetailFragment extends BaseBackFragment {
    private news mNews;
    private HtmlTextView mTVNewsContent;

    public static NewsDetailFragment newInstance(news NewsBean) {
        Bundle args = new Bundle();
        NewsDetailFragment fragment = new NewsDetailFragment();
        args.putSerializable("NewsBean", NewsBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNews = (news) getArguments().get("NewsBean");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, null);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTVNewsContent = (HtmlTextView) view.findViewById(R.id.htNewsContent);

        initToolbarNav(toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getTitle());

        String imgurl=null;
        if(mNews.getImg()!=null){
            imgurl=mNews.getImg().getFileUrl(_mActivity);
        }
        ImageLoaderUtils.display(_mActivity, (ImageView) view.findViewById(R.id.ivImage),imgurl);
        if(mNews.getBody()!=null){
            mTVNewsContent.setHtmlFromString(mNews.getBody(), new HtmlTextView.LocalImageGetter());
        }
        else{
            mTVNewsContent.setHtmlFromString("<h>获取详情出错", new HtmlTextView.LocalImageGetter());
        }
        return view;
    }
}
