package com.example.checkrooms_app

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.StringReader


class VideoActivity : AppCompatActivity(),SurfaceHolder.Callback,
MediaPlayer.OnPreparedListener,
MediaPlayer.OnCompletionListener,
MediaPlayer.OnErrorListener,
MediaPlayer.OnInfoListener, View.OnClickListener,
MediaPlayer.OnSeekCompleteListener,
MediaPlayer.OnVideoSizeChangedListener,
SeekBar.OnSeekBarChangeListener,
View.OnTouchListener {

    lateinit var playOrPauseIv: ImageView;
    lateinit var  videoSuf: SurfaceView;
    lateinit var mPlayer: MediaPlayer ;
    lateinit var  mSeekBar:SeekBar;
    lateinit var  path:String ;
    lateinit var  rootViewRl: RelativeLayout;
    lateinit var  controlLl: LinearLayout;
    lateinit var  startTime:TextView;
    lateinit var endTime: TextView;
    lateinit var  forwardButton:ImageView;
    lateinit var backwardButton:ImageView;
     var isShow:Boolean = false;

//    public static final int UPDATE_TIME = 0x0001;
        public val UPDATE_TIME:Int = 0x0001;
//    public static final int HIDE_CONTROL = 0x0002;
    public val  HIDE_CONTROL:Int = 0x0002;

//    Handler();

    /**
     * 也就是说 val 是只读，而 var 可读可修改
    把val看做常量，var看做变量就很好理解了
     */
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UPDATE_TIME -> {
                    updateTime()
                    sendEmptyMessageDelayed(UPDATE_TIME, 500)
                }
                HIDE_CONTROL -> hideControl()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        initViews()
        initData()
        initSurfaceView()
        initPlayer()
        initEvent()
    }



    private fun initData() {
        checkReadExternalStoragePermission();
//        /storage/emulated/0/02 flutter入门到精通课程/20 Flutter移动应用：国际化/4 安装与配置 Flutter 应用的国际化_【itjc8.com】【www.52download.cn】.mp4
        path = Environment.getExternalStorageDirectory().getPath() + "/a1.mp4";//这里写上你的视频地址
//        path = "/storage/emulated/0/02 flutter入门到精通课程/20 Flutter移动应用：国际化/4 安装与配置 Flutter 应用的国际化_【itjc8.com】【www.52download.cn】.mp4";//这里写上你的视频地址
    }

    private fun initEvent() {
        playOrPauseIv.setOnClickListener(this);
        rootViewRl.setOnClickListener(this);
        rootViewRl.setOnTouchListener(this);
        forwardButton.setOnClickListener(this);
        backwardButton.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }
    private fun initSurfaceView() {
        videoSuf = findViewById(R.id.surfaceView) as SurfaceView;

        videoSuf.setZOrderOnTop(false);
        videoSuf.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        videoSuf.getHolder().addCallback(this);
    }

    private fun initPlayer() {
        mPlayer = MediaPlayer();
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        mPlayer.setOnInfoListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnVideoSizeChangedListener(this);
        try {
            //使用手机本地视频
            mPlayer.setDataSource(path);
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    private fun initViews() {
        startTime = findViewById(R.id.tv_start_time) as TextView

        playOrPauseIv = findViewById(R.id.playOrPause) as ImageView
        endTime = findViewById(R.id.tv_end_time) as TextView
        mSeekBar = findViewById(R.id.tv_progess) as SeekBar
        rootViewRl = findViewById(R.id.root_rl) as RelativeLayout
        controlLl = findViewById(R.id.control_ll) as LinearLayout
        forwardButton = findViewById(R.id.tv_forward) as ImageView
        backwardButton = findViewById(R.id.tv_backward) as ImageView
    }

     override fun surfaceCreated(p0: SurfaceHolder?) {
//         TODO("Not yet implemented")
     }



    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }


    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun onPrepared(mp: MediaPlayer) {
//        startTime.setText(FormatTimeUtil.formatLongToTimeStr(mp.currentPosition))
//        endTime.setText(FormatTimeUtil.formatLongToTimeStr(mp.duration))
        mSeekBar.max = mp.duration
        mSeekBar.progress = mp.currentPosition
    }




    override public fun onCompletion(mp: MediaPlayer) {

    }

    override public fun onError(mp: MediaPlayer, what: Int, extra: Int):Boolean  {
        return false;
    }


    override public fun onInfo(mp: MediaPlayer, what: Int, extra: Int):Boolean {
        return false;
    }
    private fun play() {
        if (mPlayer == null) {
            return;
        }
        Log.i("playPath", path);
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mHandler.removeMessages(UPDATE_TIME);
            mHandler.removeMessages(HIDE_CONTROL);
            playOrPauseIv.setVisibility(View.VISIBLE);
            playOrPauseIv.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mPlayer.start();
            mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 500);
            mHandler.sendEmptyMessageDelayed(HIDE_CONTROL, 5000);
            playOrPauseIv.setVisibility(View.INVISIBLE);
            playOrPauseIv.setImageResource(android.R.drawable.ic_media_pause);
        }
    }
    override public fun onSeekComplete(mp: MediaPlayer) {
        //TODO
    }


    override public fun onVideoSizeChanged(mp: MediaPlayer, width: Int, height: Int) {

    }

    public fun checkReadExternalStoragePermission()   {
        if (Build.VERSION.SDK_INT>=23)       {
            var request:Int=ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (request!= PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.NFC),123);
                return;//
            }
            else
            {
                //权限同意，不需要处理,去掉用拍照的方法               Toast.makeText(this,"权限同意",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //低于23 不需要特殊处理，去掉用拍照的方法
        }
    }

    override public fun onRequestPermissionsResult( requestCode:Int, @NonNull  permissions:Array<String>, grantResults:IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {            //当然权限多了，建议使用Switch，不必纠结于此
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override public fun onClick(v: View) {
        when(v.getId()) {
            R.id.tv_backward ->
                backWard();

            R.id.tv_forward ->
                forWard();

            R.id.playOrPause ->
                play();

            R.id.root_rl ->
                showControl();

        }
    }
    /**
     * 更新播放时间
     */
    fun  updateTime() {



//        startTime.setText(
//            FormatTimeUtil.formatLongToTimeStr(
//                mPlayer.getCurrentPosition()
//            )
//        );
//
//        startTime.setText(
//            FormatTimeUtil.formatLongToTimeStr(
//                mPlayer.getCurrentPosition()
//            )
//        );
        mSeekBar.setProgress(mPlayer.getCurrentPosition());
    }

    /**
     * 隐藏进度条
     */
    fun  hideControl() {
        isShow = false;
        mHandler.removeMessages(UPDATE_TIME);
        controlLl.animate().setDuration(300).translationY(controlLl.getHeight().toFloat());
    }
    /**
     * 显示进度条
     */
    private fun showControl() {
        if (isShow) {
            play();
        }
        isShow = true;
        mHandler.removeMessages(HIDE_CONTROL);
        mHandler.sendEmptyMessage(UPDATE_TIME);
        mHandler.sendEmptyMessageDelayed(HIDE_CONTROL, 5000);
        controlLl.animate().setDuration(300).translationY(0f);
    }
    /**
     * 设置快进10秒方法
     */
    private fun forWard(){
        if(mPlayer != null){
            var position:Int = mPlayer.getCurrentPosition();
            mPlayer.seekTo(position + 10000);
        }
    }

    /**
     * 设置快退10秒的方法
     */
    public fun backWard(){
        if(mPlayer != null){
            var position:Int = mPlayer.getCurrentPosition();
            if(position > 10000){
                position-=10000;
            }else{
                position = 0;
            }
            mPlayer.seekTo(position);
        }
    }

    //OnSeekBarChangeListener

    override public fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
        if(mPlayer != null && b){
            mPlayer.seekTo(progress);
        }
    }


    override public fun onStartTrackingTouch(seekBar: SeekBar) {
    }


    override public fun onStopTrackingTouch(seekBar: SeekBar) {

    }

     override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//         TODO("Not yet implemented")

         return true
     }
//     }
 }

