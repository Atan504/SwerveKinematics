import edu.greenblitz.robot.subsystems.swerve.Module;
import edu.wpi.first.math.geometry.Translation2d;
import utils.PIDObject;

import java.lang.module.ModuleFinder;

public class RobotMap {
	public static class Robot {
		public static class Joystick {
			public static final int MAIN = 0;
			public static final int SECOND = 1;
		}


		public static class swerve {
			public static final PIDObject angPID = new PIDObject();
			public static final PIDObject linPID = new PIDObject();

			public static final Translation2d[] modulesLocations = {
					new Translation2d(-1,-1),
					new Translation2d(-1,-1),
					new Translation2d(-1,-1),
					new Translation2d(-1,-1)};

			public static Module[] modules = {
					new Module(frontRight.linMotorID,frontRight.angMotorID,frontRight.magEncoderID, swerve.linPID,swerve.angPID,modulesLocations[0]),
					new Module(frontRight.linMotorID,frontRight.angMotorID,frontRight.magEncoderID, swerve.linPID,swerve.angPID,modulesLocations[1]),
					new Module(frontRight.linMotorID,frontRight.angMotorID,frontRight.magEncoderID, swerve.linPID,swerve.angPID,modulesLocations[2]),
					new Module(frontRight.linMotorID,frontRight.angMotorID,frontRight.magEncoderID, swerve.linPID,swerve.angPID,modulesLocations[3]),


		};

			public static class frontRight {
				public static final int linMotorID = -1;
				public static final int angMotorID = -1;
				public static final int magEncoderID = -1;

			}
			public static class fronLeft {
				public static final int linMotorID = -1;
				public static final int angMotorID = -1;
				public static final int magEncoderID = -1;

			}
			public static class backRight {
				public static final int linMotorID = -1;
				public static final int angMotorID = -1;
				public static final int magEncoderID = -1;

			}
			public static class BackLeft {
				public static final int linMotorID = -1;
				public static final int angMotorID = -1;
				public static final int magEncoderID = -1;

			}
		}

	}
}