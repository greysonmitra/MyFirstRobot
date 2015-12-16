package bot.controller;

import lejos.hardware.lcd.*;
import lejos.utility.Delay;

import bot.model.EV3Bot;

public class BotController
{
	private String message;
	private int xPosition, yPosition;
	private long waitTime;
	
	private EV3Bot funkybot;
	
	public BotController()
	{
		this.xPosition = 27;
		this.yPosition = 54;
		this.waitTime = 4000;
		this.message = "Robots are the cooliest!";
		
		funkybot = new EV3Bot();
	}
	

	public void start()
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
		funkybot.driveRoom();
	}
	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public int getxPosition()
	{
		return xPosition;
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}

	public long getWaitTime()
	{
		return waitTime;
	}

	public void setWaitTime(long waitTime)
	{
		this.waitTime = waitTime;
	}
}
