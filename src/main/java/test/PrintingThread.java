package test;

import java.util.concurrent.TimeUnit;

public class PrintingThread extends Thread{
	public void run() {
		for(int i=9; i>=0; i--) {
			System.out.println(i+"..");
			try {
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e) {
				;
			}
			
		}
	}

}
