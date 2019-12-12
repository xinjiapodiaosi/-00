package com.meiji.ysj.youxidating.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meiji.yangshijie.login.R;
import com.meiji.yangshijie.login.view.BaesDialog;
import com.meiji.utils.IsBeginSoundEffectUtils;
import com.meiji.utils.ToastUtils;

/**
  *  描述：充值提现
  *  时间：2018/8/9 15:40
  **/
public class RechargePutForwardDialog extends BaesDialog implements View.OnClickListener {

    private ImageView btRechargeXx;
    private TextView tvRechargeJingbi;
    private ImageView btRechargeTixian;
    private EditText edRechargeEd;
    private TextView tvRechargeWeixing;
    private TextView tvRechargeZhifubao;
    private TextView tvRechargeYinghangka;

    public static Activity activity;


    private Context context;
    public RechargePutForwardDialog(@NonNull Context context) {
        super(context);
        this.context=context;

    }

    public RechargePutForwardDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RechargePutForwardDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int setView() {
        return R.layout.rechargeputforward_dialog;
    }

    @Override
    protected void init() {

        btRechargeXx = (ImageView) findViewById(R.id.bt_recharge_xx);//关闭
        tvRechargeJingbi = (TextView) findViewById(R.id.tv_recharge_jingbi);//金币
        btRechargeTixian = (ImageView) findViewById(R.id.bt_recharge_tixian);//提现
        edRechargeEd = (EditText) findViewById(R.id.ed_recharge_ed);//输入的充值金额
        edRechargeEd.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);//只能输入数字和小数

        tvRechargeWeixing = (TextView) findViewById(R.id.tv_recharge_weixing);//微信充值
        tvRechargeZhifubao = (TextView) findViewById(R.id.tv_recharge_zhifubao);//支付宝充值
        tvRechargeYinghangka = (TextView) findViewById(R.id.tv_recharge_yinghangka);//银行卡充值

        btRechargeXx.setOnClickListener(this);
        btRechargeTixian.setOnClickListener(this);
        tvRechargeWeixing.setOnClickListener(this);
        tvRechargeZhifubao.setOnClickListener(this);
        tvRechargeYinghangka.setOnClickListener(this);


    }

    public static void Show(Context context, Activity activity){
        RechargePutForwardDialog.activity=activity;
        RechargePutForwardDialog rechargePutForwardDialog=new RechargePutForwardDialog(context);
        rechargePutForwardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        rechargePutForwardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        rechargePutForwardDialog.show();
    }

    @Override
    public void onClick(View view) {
        IsBeginSoundEffectUtils.Begin(context);
        switch (view.getId()){
            case R.id.bt_recharge_xx://关闭
                this.dismiss();
                break;
            case R.id.bt_recharge_tixian://提现
                ToastUtils.showToast(context,"请先完善个人信息！");
                PutForwardDialog.Show(context);
                break;
            case R.id.tv_recharge_weixing://微信充值
                ToastUtils.showToast(context,"暂未开通！");
                break;
            case R.id.tv_recharge_zhifubao://支付宝充值
                ToastUtils.showToast(context,"暂未开通！");
                break;
            case R.id.tv_recharge_yinghangka://银行卡充值
                ToastUtils.showToast(context,"暂未开通！");
                break;
        }
    }
}
