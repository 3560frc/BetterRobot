/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Chassis extends SubsystemBase {
  
  WPI_TalonSRX rightBack;
  WPI_VictorSPX rightFront, leftFront, leftBack;

  public Chassis() {
    rightBack = new WPI_TalonSRX(Constants.portRightBack);
    leftFront = new WPI_VictorSPX(Constants.portLeftFront);
    leftBack = new WPI_VictorSPX(Constants.portLeftBack);
    rightFront = new WPI_VictorSPX(Constants.portRightFront);
  }

  /**
   * @param speed The speed that the motors should move at
   */
  public void driveBoth(double speed, double time){
    rightBack.set(-1 * speed);
    leftBack.set(-1 * speed);
    rightFront.set(speed);
    leftFront.set(speed);
    Timer.delay(time);
  }

  /**
   * @param speed The speed that the motors should move at
   */
  public void driveRight(double speed, double time){
    rightBack.set(-1 * speed);
    rightFront.set(speed);
    Timer.delay(time);
  }

  /**
   * @param speed The speed that the motors should move at
   */
  public void driveLeft(double speed, double time){
    leftBack.set(-1 * speed);
    leftFront.set(speed);
    Timer.delay(time);
  }

  public void drive(double lSpeed, double rSpeed){
    leftBack.set(-1*lSpeed);
    leftFront.set(-1*lSpeed);
    rightBack.set(rSpeed);
    rightFront.set(rSpeed);
  }

  /**
   * @param speed The speed that the motors should move at
   */
  public void spinLeft(double speed, double time){
    driveLeft(speed, time);
    driveRight(-1 * speed, time);
    Timer.delay(time);
  }

  /**
   * @param speed The speed that the motors should move at
   */
  public void spinRight(double speed, double time){
    driveRight(speed, time);
    driveLeft(-1 * speed, time);
    Timer.delay(time);
  }

  public void stop(){
    rightBack.stopMotor();
    rightFront.stopMotor();
    leftBack.stopMotor();
    leftFront.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
