/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.AutonCommand;
import frc.robot.commands.ColourWheel;
import frc.robot.commands.IntakeStuff;
import frc.robot.commands.OutTake;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.StopAll;
import frc.robot.commands.TankDrive;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final Chassis chassis = new Chassis();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final XboxController controller = new XboxController(Constants.portXbox);
  //private final ShootBall shootBall = new ShootBall(shooter, chassis, NetworkTableInstance.getDefault().getTable("greenVision"));
  private final IntakeStuff intakeStuff = new IntakeStuff(shooter, intake);
  private final OutTake outTake = new OutTake(shooter, intake);
  private final ManualShoot manualShoot = new ManualShoot(shooter);
  private final ColourWheel colourWheel = new ColourWheel(shooter);
  private final AutonCommand autonCommand = new AutonCommand(shooter, chassis);
  private final SolenoidsMoving solenoidsMoving = new SolenoidsMoving(shooter);
  private final StopAll stopAll = new StopAll(shooter, intake, chassis);
  private final GrappleLift grappleLift = new GrappleLift();
  private final GrappleCommand grappleCommand = new GrappleCommand(grappleLift);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    chassis.setDefaultCommand(
      new TankDrive(chassis, controller)
    );
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kA.value).whenPressed(grappleCommand);
    new JoystickButton(controller, Button.kBumperLeft.value).toggleWhenPressed(intakeStuff);
    new JoystickButton(controller, Button.kY.value).toggleWhenPressed(outTake);
    new JoystickButton(controller, Button.kBumperRight.value).toggleWhenPressed(manualShoot);
    new JoystickButton(controller, Button.kX.value).whileHeld(colourWheel);
    new JoystickButton(controller, Button.kB.value).toggleWhenPressed(solenoidsMoving);
    new JoystickButton(controller, Button.kBack.value).toggleWhenPressed(stopAll);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommand;
  }
}
