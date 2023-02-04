// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class SwerveTurnConstants {
    public static final double ANGLE_THRESHOLD = Units.degreesToRadians(2);
    
    public static final double FL_OFFSET = 4.126;
    public static final double FR_OFFSET = 4.157;
    public static final double BL_OFFSET = 5.305;
    public static final double BR_OFFSET = 1.375;

    public static final double TOP_ANGULAR_SPEED = 2 * 2 * Math.PI;

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

    public static final double TURN_PID_MIN_INPUT = 0; // radians
    public static final double TURN_PID_MAX_INPUT = 2 * Math.PI; // radians

    public static final double TURN_PID_MIN_OUTPUT = -1; // radians
    public static final double TURN_PID_MAX_OUTPUT = 1; // radians

    public static final double P = 0.13;
    public static final double I = 0.0;
    public static final double D = 0.0;
    public static final double F = 0.0;

    public static final  boolean TURN_INVERSION = true;
  } 

  public static class SwerveDriveConstants {
    public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);
    public static final double GEER_RATTIOLI = 6.55;
    public static final double TOP_SPEED = Units.feetToMeters(9.6);
  }

  public static class RobotConstants {
    public static final double TRACK_WIDTH = Units.inchesToMeters(23);
    public static final double WHEEL_BASE = Units.inchesToMeters(23);


  }
}
