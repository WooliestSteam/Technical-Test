package com.example.demo.scheduledtasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PingTask {

	// ===========================================
	// Public Methods
	// ===========================================

	@Scheduled(fixedRate = 5000)
	public void savePingCallToFile() throws IOException {
		
		InetAddress inet = InetAddress.getByName("127.0.0.1");
		
		String pingOutcome = getPingOutcome(inet);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("pingCallOutput.txt", true));
		bufferedWriter.write(pingOutcome);
		bufferedWriter.newLine();
		bufferedWriter.close();
	}

	private String getPingOutcome(InetAddress inet) throws IOException {

		String pingOutcome;

		if (inet.isReachable(5000)) {
			pingOutcome = new Date() + "localhost is reachable";
		} else {
			pingOutcome = new Date() + "localhost is NOT reachable";
		}
		return pingOutcome;
	}

}
