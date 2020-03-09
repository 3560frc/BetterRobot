/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GrappleLift extends SubsystemBase {
  
  PWMTalonSRX liftMotor;

  public GrappleLift() {
    liftMotor = new PWMTalonSRX(Constants.portPWMLift);
  }

  public void setLift(double speed){
    liftMotor.set(speed);
  }

  public void pullLift(){
    setLift(-1);
  }

  public void stop(){
    liftMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
