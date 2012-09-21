package kr.oks.saboard.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.oks.saboard.core.constants.Constants;

public class ShellCommander {

	private String command;
	private StringBuffer output;

	public void setCommand(String command) {
		if (Constants.IS_WINDOWS) {
			this.command = "cmd /c " + command;
		} else {
			this.command = command;
		}
	}

	public String getResultString() {
		return output.toString();
	}

	public void execute(String command) {
		setCommand(command);
		execute();
	}

	public void execute() {
		output = new StringBuffer();
		Process process = null;
		BufferedReader reader = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(command);
			reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String msg;
			while ((msg = reader.readLine()) != null) {
				output.append(msg);
				output.append(Constants.LINE_SEPARATOR);
			}
			reader.close();
			reader = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			while ((msg = reader.readLine()) != null) {
				output.append(msg);
				output.append(Constants.LINE_SEPARATOR);
			}
		} catch (IOException e) {
			output.append("Runtime exception - " + e.getMessage());
		} finally {
			try {
				process.destroy();
				if (reader != null)
					reader.close();
			} catch (IOException ioe) {
			}
		}
	}
}
