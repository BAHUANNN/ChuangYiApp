package com.example.myaspect;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.myaspect.annotation.MonitorForClick;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class MuxiAspect {
    private static final String TAG = "Activity information";
    private static final String packageName = "com.example.hp.chuangyiapp";

    //BaseActivity+保证只运行一次
    //getTarget()可以拿到TargetActivity
    @Before("execution(* com.example.hp.chuangyiapp.base.BaseActivity+.onCreate(..))")
    public void onActivityCreateBefore(JoinPoint joinPoint) throws Throwable {
        String target = joinPoint.getTarget().toString().substring(packageName.length());
        Log.e(TAG,target + "#" + joinPoint.getSignature().getName());
    }

   /* //网上说ViewPager会调用onHiddenChanged方法...但是并没有
    @Pointcut("execution(void onHiddenChanged(boolean)) && within(android.support.v4.app.Fragment) && target(fragment) && args(hidden)")
    public void onHiddenChanged(Fragment fragment, boolean hidden) { }
    @Before("onHiddenChanged(fragment,hidden)")
    public void beforeHiddenChanged(Fragment fragment, boolean hidden){
        Log.e(TAG,fragment.toString() + "#hidden#" + hidden);
    }

    @Pointcut("execution(void setUserVisibleHint(..)) && within(android.support.v4.app.Fragment) && target(fragment) && args(visible)")
    public void setUserVisibleHint(Fragment fragment, boolean visible) { }
    @Before("setUserVisibleHint(fragment,visible)")
    public void beforeUserVisibleHint(Fragment fragment, boolean visible){
        Log.e(TAG,fragment.toString() + "#visible#" + visible);
    }

    @Before("execution(* onResume()) && within(android.support.v4.app.Fragment) && target(fragment)")
    public void beforeResume(Fragment fragment){
        Log.e(TAG,fragment.toString() + "#resume#");
    }

    @Before("execution(* onPause()) && within(android.support.v4.app.Fragment) && target(fragment)")
    public void beforePause(Fragment fragment){
        Log.e(TAG,fragment.toString() + "#pause#");
    }
*/
    @Before("execution(* com.example.myaspect.annotation.MonitorForClick(..)) && @annotation(monitor)")
    public void afterClick(ProceedingJoinPoint joinPoint, MonitorForClick monitor){
        String event = monitor.event();
    }

}
