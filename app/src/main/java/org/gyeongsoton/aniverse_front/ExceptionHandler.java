package org.gyeongsoton.aniverse_front;

import androidx.annotation.NonNull;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        System.out.println("Thread Name : "+ t.getName());
        System.out.println("=================================");
        System.out.println("Exception Message : "+ e.getMessage());
        System.out.println("Exception Localized Message : "+e.getLocalizedMessage());
        System.out.println("Exception Cause : "+ e.getCause());
        System.out.println("=================================");
        e.printStackTrace(); //에러 메시지의 발생 근원지를 찾아서 단계별로 에러를 출력
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

}
