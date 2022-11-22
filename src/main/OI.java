package edu.greenblitz.robot;


import utils.SmartJoystick;

public class OI {
	private static final IOModes IOMode = IOModes.REAL; //decides which set of controls to init.
	private static OI instance;
	private final SmartJoystick mainJoystick;
	private final SmartJoystick secondJoystick;

	private OI() {
		mainJoystick = new SmartJoystick(RobotMap.Robot.Joystick.MAIN, 0.2);
		secondJoystick = new SmartJoystick(RobotMap.Robot.Joystick.SECOND, 0.2);
		switch (IOMode) {
			case DEBUG:
				initDebugButtons();
				break;
			case REAL:
				initRealButtons();
				break;

		}
	}

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}



	private void initDebugButtons() {
	}

	private void initRealButtons() {
	}

	public SmartJoystick getMainJoystick() {
		return mainJoystick;
	}

	public SmartJoystick getSecondJoystick() {
		return secondJoystick;
	}

	private enum IOModes {
		DEBUG, REAL,
	}
	
}
