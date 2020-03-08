/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class AutonCommand extends CommandBase {
  
  
  private final Shooter shooter;
  private final Chassis chassis;

  public AutonCommand(Shooter s, Chassis c) {
    shooter = s;
    chassis = c;
    addRequirements(shooter);
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    chassis.driveBoth(-0.5, 0.8);
    Timer.delay(3);
    chassis.driveBoth(0.1, 0.5);
    chassis.stop();
    shooter.dropShooter();
    shooter.setIntake(0.7);
    shooter.setFlywheel(0.7);
    Timer.delay(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shootBall.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
