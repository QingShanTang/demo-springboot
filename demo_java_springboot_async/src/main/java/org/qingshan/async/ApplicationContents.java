package org.qingshan.async;


public interface ApplicationContents {

//    Future是异步方法执行过程，在执行的过程中自然会有状态的转换：
//
//      1）一个FutureTask新建出来，state就是NEW状态；COMPETING和INTERRUPTING用的进行时，表示瞬时状态，存在时间极短(为什么要设立这种状态？？？不解)；NORMAL代表顺利完成；EXCEPTIONAL代表执行过程出现异常；CANCELED代表执行过程被取消；INTERRUPTED被中断
//
//      2）执行过程顺利完成：NEW -> COMPLETING -> NORMAL
//
//      3）执行过程出现异常：NEW -> COMPLETING -> EXCEPTIONAL
//
//      4）执行过程被取消：NEW -> CANCELLED
//
//      5）执行过程中，线程中断：NEW -> INTERRUPTING -> INTERRUPTED

    interface state {
        int NEW = 0;
        int COMPLETING = 1;
        int NORMAL = 2;
        int EXCEPTIONAL = 3;
        int CANCELLED = 4;
        int INTERRUPTING = 5;
        int INTERRUPTED = 6;
    }

}
