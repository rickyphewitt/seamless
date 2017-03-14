package com.mini.emby.services.logging;

import mediabrowser.model.logging.ILogger;

public class Logger implements ILogger {

	private Class clazz;
	
	public Logger(Class clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void Debug(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Error(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ErrorException(String arg0, Exception arg1, Object... arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Fatal(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FatalException(String arg0, Exception arg1, Object... arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Info(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Warn(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}

}
