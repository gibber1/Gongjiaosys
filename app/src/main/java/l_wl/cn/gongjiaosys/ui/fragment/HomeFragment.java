package l_wl.cn.gongjiaosys.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import l_wl.cn.gongjiaosys.R;
import l_wl.cn.gongjiaosys.adapter.HomeAdapter;
import l_wl.cn.gongjiaosys.helper.OnItemClickListener;
import l_wl.cn.gongjiaosys.model.Article;
import l_wl.cn.gongjiaosys.ui.BaseMainFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;



public class HomeFragment extends BaseMainFragment implements Toolbar.OnMenuItemClickListener {
    private String[] mTitles = new String[]{
            "航拍“摩托大军”返乡高峰 如蚂蚁搬家（组图）",
            "苹果因漏电召回部分电源插头",
            "IS宣称对叙利亚爆炸案负责"
    };

    private String[] mContents = new String[]{
            "1月30日，距离春节还有不到十天，“摩托大军”返乡高峰到来。航拍广西梧州市东出口服务站附近的骑行返乡人员，如同蚂蚁搬家一般。",
            "昨天记者了解到，苹果公司在其官网发出交流电源插头转换器更换计划，召回部分可能存在漏电风险的电源插头。",
            "极端组织“伊斯兰国”31日在社交媒体上宣称，该组织制造了当天在叙利亚首都大马士革发生的连环爆炸案。"
    };

    private Toolbar mToolbar;

    private FloatingActionButton mFab;
    private RecyclerView mRecy;
    private HomeAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimation() {
        // 默认不改变
//         return super.onCreateFragmentAnimation();
        // 在进入和离开时 设定无动画
        return new DefaultNoAnimator();
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mRecy = (RecyclerView) view.findViewById(R.id.recy);

        mToolbar.setTitle(R.string.home);
        initToolbarNav(mToolbar, true);


        mAdapter = new HomeAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });

        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int index = (int) (Math.random() * 3);
            Article article = new Article(mTitles[index], mContents[index]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onNewBundle(Bundle args) {
        super.onNewBundle(args);

        Toast.makeText(_mActivity, args.getString("from"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
