/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ShootBall extends CommandBase {
  
  private final Shooter shooter;
  private final Chassis chassis;
  private final NetworkTable table;
  double x, y, w, h;

  public ShootBall(Shooter s, Chassis c, NetworkTable t) {
    shooter = s;
    chassis = c;
    table = t;
    addRequirements(shooter);
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.liftShooter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = table.getEntry("CentreX").getDouble(-1);
    y = table.getEntry("CentreY").getDouble(-1);
    w = table.getEntry("width").getDouble(-1);
    h = table.getEntry("height").getDouble(-1);
    System.out.println(x + " :X");
    System.out.println(y + ":Y ");
    System.out.println(w + " :width");
    System.out.println(h + " :height");
    if (w > 10 && h > 10){
      if (x < 310){
        chassis.spinRight(0.15, 0.5);
        System.out.println(x);
      }
      if (x > 330){
        chassis.spinLeft(0.15, 0.5);
        System.out.println(x);
      }
      if (y < 230){
        chassis.driveBoth(-0.15, 0.5);
        System.out.println(y);
      }
      if (y > 250){
        chassis.driveBoth(0.15, 0.5);
        System.out.println(y);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.dropShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !((x > 310 && x < 330) || (y > 230 && y < 250));
  }
}
