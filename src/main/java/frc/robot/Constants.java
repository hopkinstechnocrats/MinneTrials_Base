// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //ID's for ports so that we know what we're communicating with
    public static final int rightLeaderCANID = 7;
    public static final int rightFollowerCANID = 8;
    public static final int leftLeaderCANID = 6;
    public static final int leftFollowerCANID = 5;
    //TODO find CANID of conveyer motor
    public static final int conveyerMotorID = 11;
    public static final int XboxControllerPort = 0;

    //set maximum %output of motors
    public static final double maxMotorOutput = -0.55;
    public static final double conveyorSpeed = 0.1;


}
