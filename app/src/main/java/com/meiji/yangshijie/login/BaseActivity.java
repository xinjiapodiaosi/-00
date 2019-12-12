package com.meiji.yangshijie.login;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.meiji.Interface.NetworkCallk;
import com.meiji.yangshijie.login.Receiver.NetworkConnectChangedReceiver;
import com.meiji.utils.DialogUtil;
import com.meiji.utils.ToastUtils;
import com.meiji.utils.Variable;

import static com.meiji.utils.IsBeginSoundEffectUtils.CloseMusic;
import static com.meiji.utils.IsBeginSoundEffectUtils.IsMusic;

/**
  *  描述：ACTIVITY的所有基类
  *  时间：2018/7/31 10:47
  **/

public abstract class BaseActivity extends Activity {
    private static Context context;
    private NetworkConnectChangedReceiver networkConnectChangedReceiver = new NetworkConnectChangedReceiver();//
    private static DialogUtil dialogUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制横屏

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        super.onCreate(savedInstanceState);
        setContentView(setView());
        if (Variable.isstarat){
            //初始化所有配置
            //           IntentFilter filter = new IntentFilter();
//            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
//            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
//            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//            registerReceiver(networkConnectChangedReceiver, filter);
//            Variable.isout=false;
        }
        this.context=this;
        Variable.isstarat=false;
        initViews();
        initData();


        init();

    }

/**
  *  描述：这个界面要进行登录状态的判断、、、、、、、判断用户是否一直处于登录状态。。。。。。。若果不是则强制弹窗告知用户
  *  时间：2018/7/31 13:45
  **/


/**
  *  描述：网络状态的判断   在较差的时候自动重连
  *  时间：2018/7/31 13:46
  **/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Variable.isNetwork=false;//是否有网络
//        Variable.isOnline=false;//是否已登录
//        Variable.isstarat=true;//是否加载第一个Activity
//        Variable.isout=false;
            //unregisterReceiver(networkConnectChangedReceiver);

    }

    @Override
    protected void onPause() {
     //   Log.i(TAG, "onPause: ");

       CloseMusic(this);


        super.onPause();
    }

    @Override
    protected void onStop() { super.onStop(); }

/**
  *  描述：初始化
  *  时间：2018/7/31 10:55
  **/
    private void init(){



    }
    protected abstract int setView();
    protected abstract void initViews();
    protected abstract void initData();

    protected  abstract  void  Error(String s);


    /**
      *  描述：网络环境监测
      *  时间：2018/8/1 14:30
      **/
    public void initNetwork(final Context context){
        SystemService.Isnet(context.getApplicationContext(), new NetworkCallk() {
            //网络没有问题
            @Override
            public void Onnormal(String s) {
                Variable.isNetwork=true;
                // ToastUtils.showToast(getResources().getString(R.string.netok),getApplicationContext());

            }
            //网络有问题
            @Override
            public void Onerror(String s) {
                Variable.isNetwork=false;
                Error("网络错误");
                ToastUtils.showToast(context.getResources().getString(R.string.neterror),context.getApplicationContext());
            }
        });
    }
    public static void NetworkErrorException(){

        if (dialogUtil==null){
            dialogUtil=new DialogUtil(context);
        }
        dialogUtil.CreateNetworkDialog();
        dialogUtil.ShowNetworkDialog();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            DialogUtil dialogUtil=new DialogUtil(this);
            dialogUtil.CreateExitDialog();
            dialogUtil.ShowExitDialog();
        }

        return false;
    }
    @Override
    protected void onRestart() {
       // Log.i(TAG, "onRestart: ");
       if (dialogUtil!=null){
         dialogUtil.DismissDialg();
       }
        super.onRestart();
       IsMusic(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    
}
