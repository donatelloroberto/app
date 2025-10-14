package com.amazon.device.ads;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadUtils {
    /* access modifiers changed from: private */
    public static ThreadRunner threadRunner = new ThreadRunner();

    public enum ExecutionStyle {
        RUN_ASAP,
        SCHEDULE
    }

    public enum ExecutionThread {
        MAIN_THREAD,
        BACKGROUND_THREAD
    }

    ThreadUtils() {
    }

    public static ThreadRunner getThreadRunner() {
        return threadRunner;
    }

    static void setThreadRunner(ThreadRunner threadRunner2) {
        threadRunner = threadRunner2;
    }

    public static boolean isOnMainThread() {
        return ThreadVerify.getInstance().isOnMainThread();
    }

    public static final <T> void executeAsyncTask(MobileAdsAsyncTask<T, ?, ?> task, T... params) {
        threadRunner.executeAsyncTask(ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD, task, params);
    }

    public static final <T> void executeAsyncTask(ThreadRunner threadRunner2, ExecutionStyle executionStyle, ExecutionThread executionThread, MobileAdsAsyncTask<T, ?, ?> task, T... params) {
        threadRunner2.executeAsyncTask(executionStyle, executionThread, task, params);
    }

    public static void scheduleRunnable(Runnable proc) {
        scheduleRunnable(proc, threadRunner);
    }

    public static void scheduleRunnable(Runnable proc, ThreadRunner threadRunner2) {
        threadRunner2.execute(proc, ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
    }

    public static void executeRunnableWithThreadCheck(Runnable proc) {
        executeRunnableWithThreadCheck(proc, threadRunner);
    }

    public static void executeRunnableWithThreadCheck(Runnable proc, ThreadRunner threadRunner2) {
        threadRunner2.execute(proc, ExecutionStyle.RUN_ASAP, ExecutionThread.BACKGROUND_THREAD);
    }

    public static void scheduleOnMainThread(Runnable proc) {
        scheduleOnMainThread(proc, threadRunner);
    }

    public static void scheduleOnMainThread(Runnable proc, ThreadRunner threadRunner2) {
        threadRunner2.execute(proc, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
    }

    public static void executeOnMainThread(Runnable proc) {
        executeOnMainThread(proc, threadRunner);
    }

    public static void executeOnMainThread(Runnable proc, ThreadRunner threadRunner2) {
        threadRunner2.execute(proc, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    public static class ThreadRunner {
        private static final String LOGTAG = ThreadRunner.class.getSimpleName();
        private final HashMap<ExecutionStyle, HashMap<ExecutionThread, RunnableExecutor>> executors;
        private final MobileAdsLogger logger;

        ThreadRunner() {
            this(new MobileAdsLoggerFactory());
            ThreadVerify threadVerify = new ThreadVerify();
            withExecutor(new ThreadPoolScheduler());
            withExecutor(new BackgroundThreadRunner(threadVerify));
            withExecutor(new MainThreadScheduler());
            withExecutor(new MainThreadRunner(threadVerify));
        }

        ThreadRunner(MobileAdsLoggerFactory loggerFactory) {
            this.executors = new HashMap<>();
            this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        }

        public ThreadRunner withExecutor(RunnableExecutor executor) {
            HashMap<ExecutionThread, RunnableExecutor> executorsForStyle = this.executors.get(executor.getExecutionStyle());
            if (executorsForStyle == null) {
                executorsForStyle = new HashMap<>();
                this.executors.put(executor.getExecutionStyle(), executorsForStyle);
            }
            executorsForStyle.put(executor.getExecutionThread(), executor);
            return this;
        }

        public void execute(Runnable proc, ExecutionStyle executionStyle, ExecutionThread executionThread) {
            HashMap<ExecutionThread, RunnableExecutor> executorsForStyle = this.executors.get(executionStyle);
            if (executorsForStyle == null) {
                this.logger.e("No executor available for %s execution style.", executionStyle);
                return;
            }
            RunnableExecutor executor = executorsForStyle.get(executionThread);
            if (executor == null) {
                this.logger.e("No executor available for %s execution style on % execution thread.", executionStyle, executionThread);
            }
            executor.execute(proc);
        }

        public <T> void executeAsyncTask(MobileAdsAsyncTask<T, ?, ?> task, T... params) {
            executeAsyncTask(ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD, task, params);
        }

        public <T> void executeAsyncTask(ExecutionStyle executionStyle, ExecutionThread executionThread, final MobileAdsAsyncTask<T, ?, ?> task, final T... params) {
            ThreadUtils.threadRunner.execute(new Runnable() {
                public void run() {
                    AndroidTargetUtils.executeAsyncTask(task, params);
                }
            }, executionStyle, executionThread);
        }
    }

    public static abstract class RunnableExecutor {
        private final ExecutionStyle executionStyle;
        /* access modifiers changed from: private */
        public final ExecutionThread executionThread;

        public abstract void execute(Runnable runnable);

        public RunnableExecutor(ExecutionStyle executionStyle2, ExecutionThread executionThread2) {
            this.executionStyle = executionStyle2;
            this.executionThread = executionThread2;
        }

        public ExecutionStyle getExecutionStyle() {
            return this.executionStyle;
        }

        public ExecutionThread getExecutionThread() {
            return this.executionThread;
        }
    }

    public static class MainThreadScheduler extends RunnableExecutor {
        public MainThreadScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
        }

        public void execute(Runnable proc) {
            new Handler(Looper.getMainLooper()).post(proc);
        }
    }

    public static class ThreadPoolScheduler extends RunnableExecutor {
        private static final int keepAliveTimeSeconds = 30;
        private static final int maxNumberThreads = 3;
        private static final int numberThreads = 1;
        private final ExecutorService executorService = new ThreadPoolExecutor(1, 3, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());

        public ThreadPoolScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
        }

        public void execute(Runnable proc) {
            this.executorService.submit(proc);
        }
    }

    public static class SingleThreadScheduler extends RunnableExecutor {
        private ExecutorService executorService = Executors.newSingleThreadExecutor();

        public SingleThreadScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
        }

        public void execute(Runnable proc) {
            this.executorService.submit(proc);
        }
    }

    public static class MainThreadRunner extends ThreadExecutor {
        public MainThreadRunner(ThreadVerify threadVerify) {
            super(threadVerify, new MainThreadScheduler());
        }
    }

    public static class BackgroundThreadRunner extends ThreadExecutor {
        public BackgroundThreadRunner(ThreadVerify threadVerify) {
            super(threadVerify, new ThreadPoolScheduler());
        }
    }

    public static class ThreadExecutor extends RunnableExecutor {
        private final RunnableExecutor threadScheduler;
        private final ThreadVerify threadVerify;

        public ThreadExecutor(ThreadVerify threadVerify2, RunnableExecutor threadScheduler2) {
            super(ExecutionStyle.RUN_ASAP, threadScheduler2.executionThread);
            this.threadVerify = threadVerify2;
            this.threadScheduler = threadScheduler2;
        }

        public void execute(Runnable proc) {
            boolean shouldSchedule;
            switch (this.threadScheduler.getExecutionThread()) {
                case MAIN_THREAD:
                    if (this.threadVerify.isOnMainThread()) {
                        shouldSchedule = false;
                        break;
                    } else {
                        shouldSchedule = true;
                        break;
                    }
                case BACKGROUND_THREAD:
                    shouldSchedule = this.threadVerify.isOnMainThread();
                    break;
                default:
                    shouldSchedule = false;
                    break;
            }
            if (shouldSchedule) {
                this.threadScheduler.execute(proc);
            } else {
                proc.run();
            }
        }
    }

    static class ThreadVerify {
        private static ThreadVerify instance = new ThreadVerify();

        ThreadVerify() {
        }

        static ThreadVerify getInstance() {
            return instance;
        }

        /* access modifiers changed from: package-private */
        public boolean isOnMainThread() {
            return Looper.getMainLooper().getThread() == Thread.currentThread();
        }
    }

    static abstract class MobileAdsAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        /* access modifiers changed from: protected */
        public abstract Result doInBackground(Params... paramsArr);

        MobileAdsAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Result result) {
            super.onPostExecute(result);
        }
    }
}
