package com.itliusir.test;

import com.itliusir.test.kill.CleanupAware;
import com.itliusir.test.kill.ShutdownManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TestApplication {

	/*private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);*/

	public static void main(String[] args) throws InterruptedException {
		/*Runtime.getRuntime().addShutdownHook(new ShutdownManager());*/
		SpringApplication.run(TestApplication.class, args);

		/*MonitorService monitorService = new MonitorService();
		ShutdownManager.regist(monitorService);
		executorService.scheduleAtFixedRate(monitorService, 0, 1000 * 1, TimeUnit.MILLISECONDS);
		TimeUnit.SECONDS.sleep(10);
		System.exit(0);*/
	}


	/*static class MonitorService implements Runnable,CleanupAware {

		@Override
		public void run() {
			// ballala
			System.out.println("我是定时任务要跑的");
		}

		@Override
		public void cleanup() {
			if (!executorService.isShutdown()) {
				System.out.println("我要被关闭啦 把啦啦啦");
				executorService.shutdown();
			}
		}
	}*/
}
