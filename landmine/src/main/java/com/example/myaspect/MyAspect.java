package com.example.myaspect;

import android.support.v4.app.Fragment;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class MyAspect {
    private static final String TAG = "Activity information";
    private static final String app = "com.example.hp.chuangyiapp";

    //BaseActivity+保证只运行一次
    //getTarget()可以拿到TargetActivity
    @Before("execution(* com.example.hp.chuangyiapp.base.BaseActivity+.onCreate(..))")
    public void onActivityCreateBefore(JoinPoint joinPoint) throws Throwable {
        String target = joinPoint.getTarget().toString().substring(app.length());
        Log.e(TAG,target + "#" + joinPoint.getSignature().getName());
    }

    @Pointcut("execution(void onHiddenChanged(boolean)) && within(android.support.v4.app.Fragment) && target(fragment) && args(hidden)")
    public void onHiddenChanged(Fragment fragment, boolean hidden) {
        
    }
    @Pointcut("execution(void setUserVisibleHint(..)) && within(android.support.v4.app.Fragment) && target(fragment) && args(visible)")
    public void setUserVisibleHint(Fragment fragment, boolean visible) {

    }
    @Pointcut("execution(void onResume()) && within(android.support.v4.app.Fragment) && target(fragment)")
    public void onResume(Fragment fragment) {

    }
    @Pointcut("execution(void onPause()) && within(android.support.v4.app.Fragment) && target(fragment)")
    public void onPause(Fragment fragment) {

    }



}
