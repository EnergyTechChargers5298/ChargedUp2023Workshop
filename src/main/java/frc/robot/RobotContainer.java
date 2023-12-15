
//Adam K Branch Code 

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.osgi.OpenCVInterface;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.AutoPath;
import frc.robot.Constants.SwerveConstants;
import frc.robot.commands.basic.ArmReach;
import frc.robot.commands.basic.ArmReset;
import frc.robot.commands.basic.ArmRetract;
import frc.robot.commands.basic.ClawGrab;
import frc.robot.commands.basic.ClawHigh;
import frc.robot.commands.basic.ClawLow;
import frc.robot.commands.basic.ClawRelease;
import frc.robot.commands.basic.FrisbeeClockwise;
import frc.robot.commands.basic.FrisbeeCounterClockwise;
import frc.robot.commands.basic.IntakeChomp;
import frc.robot.commands.basic.IntakeEat;
import frc.robot.commands.basic.IntakeLift;
import frc.robot.commands.basic.IntakeSpit;
import frc.robot.commands.basic.WristReset;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.closed.ArmSet;
import frc.robot.commands.closed.WristSet;
import frc.robot.commands.closed.ArmSet.ArmPosition;
import frc.robot.commands.closed.WristSet.WristPosition;
import frc.robot.commands.complex.AutoUno;
import frc.robot.commands.complex.*;
import frc.robot.commands.complex.ComplexEat;
import frc.robot.commands.complex.Wrarm;
import frc.robot.commands.complex.Wrarm.ComboPosition;
import frc.robot.commands.drive.SwerveDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.DPad;
import frc.robot.utils.TriggerButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
 public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private static final XboxController driveController = new XboxController(Ports.DRIVER);
  private static final XboxController operatorController = new XboxController(Ports.OPERATOR);

  private final Drivetrain drivetrain;

  public Command pathUno;
  public Command PathDos;
  public Command PathTres;
  public Command PathCuatro;

  public final SwerveAutoBuilder autonbuilder;

  SendableChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    drivetrain = Drivetrain.getInstance();

      autonbuilder = new SwerveAutoBuilder(
      drivetrain::getPose2d, 
      drivetrain::resetOdometry, 
      drivetrain.getKinematics(),
      SwerveConstants.translationPID, 
      SwerveConstants.rotationPID, 
      drivetrain::setModuleState, 
      AutoPath.eventMap, 
      drivetrain);

      autoChooser = new SendableChooser<Command>();

      pathUno = autonbuilder.fullAuto(AutoPath.ConeLeft);
      PathDos = autonbuilder.fullAuto(AutoPath.ConeRight);
      PathTres = autonbuilder.fullAuto(AutoPath.ConeLeft);
      PathCuatro = autonbuilder.fullAuto(AutoPath.ConeRight);

    configureBindings();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    Drivetrain.getInstance().setDefaultCommand(new SwerveDrive(
      () -> -driveController.getRawAxis(1),
      () -> driveController.getRawAxis(0),
      () -> driveController.getRawAxis(4)
    ));

    new JoystickButton(driveController,Button.kB.value).whileTrue(new IntakeEat());
    new JoystickButton(driveController,Button.kA.value).whileTrue(new IntakeSpit());
    new JoystickButton(driveController,Button.kX.value).whileTrue(new IntakeChomp());
    new JoystickButton(driveController,Button.kY.value).whileTrue(new IntakeLift());

    //new TriggerButton(driveController, XboxController.Axis.kLeftTrigger).whileTrue(new ComplexEat());

    new JoystickButton(operatorController,Button.kB.value).whileTrue(new ClawGrab());
    new JoystickButton(operatorController,Button.kX.value).whileTrue(new ClawRelease());
    new JoystickButton(operatorController,Button.kY.value).whileTrue(new ArmReach());
    new JoystickButton(operatorController,Button.kA.value).whileTrue(new ArmRetract());

    new JoystickButton(operatorController,Button.kLeftBumper.value).whileTrue(new ArmReset());
    new JoystickButton(operatorController,Button.kRightBumper.value).whileTrue(new WristReset());

    //new JoystickButton(operatorController, Button.kRightBumper.value).whileTrue(new WristSet(WristPosition.TOP));
    // new DPad(operatorController, 0).whileTrue(new ArmSet(ArmPosition.TOP));
    // new DPad(operatorController, 90).whileTrue(new ArmSet(ArmPosition.MID));
    // new DPad(operatorController, 270).whileTrue(new ArmSet(ArmPosition.HPS));
    // new DPad(operatorController, 180).whileTrue(new ArmSet(ArmPosition.RETRACTED));
    // new DPad(operatorController, 0).whileTrue(new WristSet(WristPosition.TOP));
    // new DPad(operatorController, 90).whileTrue(new WristSet(WristPosition.MID));
    // new DPad(operatorController, 270).whileTrue(new WristSet(WristPosition.HPS));
    // new DPad(operatorController, 180).whileTrue(new WristSet(WristPosition.RETRACTED));
    new DPad(operatorController, 0).whileTrue(new Wrarm(ComboPosition.TOP));
    new DPad(operatorController, 90).whileTrue(new Wrarm(ComboPosition.HPS));
    new DPad(operatorController, 270).whileTrue(new Wrarm(ComboPosition.MID));
    new DPad(operatorController, 180).whileTrue(new Wrarm(ComboPosition.RETRACTED));



    new TriggerButton(operatorController, XboxController.Axis.kLeftTrigger).whileTrue(new ClawLow());
    new TriggerButton(operatorController, XboxController.Axis.kRightTrigger).whileTrue(new ClawHigh());
  
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command m_autonomousCommand() {
    //Simple path without holonomic rotation. Stationary start/end. Max velocity of 4 m/s and max accel of 3 m/s^2
    // PathPlannerTrajectory traj1 = PathPlanner.generatePath(
    //   new PathConstraints(1, 1), 
    //   new PathPoint(new Translation2d(0.0, 0.0), Rotation2d.fromDegrees(0)), // position, heading
    //   new PathPoint(new Translation2d(2.0, 0.0), Rotation2d.fromDegrees(150)) // position, heading
    // );

    //return Drivetrain.getInstance().followTrajectoryCommand(traj1, true);
    return new AutoUno();
    //return new AutoJ();
  }
}
