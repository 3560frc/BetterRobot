/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  
  WPI_TalonSRX shooter1, shooter2, shooterRight, shooterLeft;
  DoubleSolenoid lift;
  //Compressor compressor;

  public Shooter() {
    shooter1 = new WPI_TalonSRX(Constants.portShooter1);
    shooter2 = new WPI_TalonSRX(Constants.portShooter2);
    shooterRight = new WPI_TalonSRX(Constants.portShooterRight);
    shooterLeft = new WPI_TalonSRX(Constants.portShooterLeft);
    //compressor = new Compressor();
    //compressor.start();
    lift = new DoubleSolenoid(0, 1);
  }

  public void setIntake(double speed){
    shooter1.set(speed);
    shooter2.set(-speed);
  }

  public void liftShooter(){
    lift.set(Value.kForward);
  }

  public void dropShooter(){
    lift.set(Value.kReverse);
    Timer.delay(0.25);
    lift.set(Value.kOff);
  }

  public void setFlywheel(double speed){
    shooterRight.set(-speed);
    shooterLeft.set(-speed);
  }

  public void stop(){
    shooterRight.stopMotor();
    shooterLeft.stopMotor();
    shooter1.stopMotor();
    shooter2.stopMotor();
    lift.set(Value.kOff);
  }

  public void stopIntake(){
    shooter1.stopMotor();
    shooter2.stopMotor();
  }

  public void stopFlywheel(){
    shooterRight.stopMotor();
    shooterLeft.stopMotor();
  }

  @Override
  public void periodic() {
    //compressor.start();
  }
}
