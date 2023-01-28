// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spinny;

public class SpinnyCounterClockwise extends CommandBase {
  private Spinny spinny;
  /** Creates a new SpinnyCounterClockwise. */
  public SpinnyCounterClockwise() {
    spinny = Spinny.getInstance();
    addRequirements(spinny);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    spinny.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    spinny.counterclockwise();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinny.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
