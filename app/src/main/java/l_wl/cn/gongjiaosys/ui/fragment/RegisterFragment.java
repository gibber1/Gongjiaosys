package l_wl.cn.gongjiaosys.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import l_wl.cn.gongjiaosys.R;
import l_wl.cn.gongjiaosys.bean.User;
import l_wl.cn.gongjiaosys.ui.BaseBackFragment;


/**
 * Created by YoKeyword on 16/2/14.
 */
public class RegisterFragment extends BaseBackFragment {
    private EditText mEtAccount, mEtPassword, mEtPasswordConfirm;
    private Button mBtnRegister;
    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.OnLoginSuccessListener) {
            mOnLoginSuccessListener = (LoginFragment.OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mEtAccount = (EditText) view.findViewById(R.id.et_account);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mEtPasswordConfirm = (EditText) view.findViewById(R.id.et_password_confirm);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register);

        showSoftInput(mEtAccount);

        toolbar.setTitle(R.string.register);
        initToolbarNav(toolbar);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strAccount = mEtAccount.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                String strPasswordConfirm = mEtPasswordConfirm.getText().toString();
                if (TextUtils.isEmpty(strAccount.trim())) {
                    Toast.makeText(_mActivity, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strPassword.trim()) || TextUtils.isEmpty(strPasswordConfirm.trim())) {
                    Toast.makeText(_mActivity, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!strPassword.equals(strPasswordConfirm)) {
                    Toast.makeText(_mActivity, "两次密码不一致!", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user=new User();
                user.setUsername(strAccount);
                user.setPassword(strPassword);
                user.signUp(_mActivity, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        // 注册成功
                        mOnLoginSuccessListener.onLoginSuccess(strAccount);
                        popTo(LoginFragment.class, true);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(_mActivity,"注册失败："+s,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void onHidden() {
        super.onHidden();
        hideSoftInput();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }
}
