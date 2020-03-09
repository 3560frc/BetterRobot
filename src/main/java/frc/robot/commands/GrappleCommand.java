/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GrappleLift;

public class GrappleCommand extends CommandBase {
  
  GrappleLift lift;

  public GrappleCommand(GrappleLift g) {
    lift = g;
    addRequirements(lift);
  }

  @Override
  public void initialize() {
    lift.pullLift();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lift.pullLift();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lift.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
