package org.wheeler.robotics.MiniBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by lucien on 8/27/15.
 */
public class MiniBotDrive extends OpMode{
    float leftStickX;
    float leftStickY;
    float rightStickX;
    float rightStickY;
    boolean slowButton;
    boolean stopButton;
    double powerMultiplier;
    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init(){
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void stopMotors(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void loop(){
        leftStickX=gamepad1.left_stick_x;
        leftStickY=gamepad1.left_stick_y;
        rightStickX=gamepad1.right_stick_x;
        rightStickY=gamepad1.right_stick_y;
        slowButton=gamepad1.right_bumper;
        stopButton=gamepad1.left_bumper;
        if(!slowButton){
            powerMultiplier=1;
        }
        else{
            powerMultiplier=0.1;
        }

        if (stopButton) stopMotors();
        else {
            leftMotor.setPower(leftStickY * powerMultiplier);
            telemetry.addData("left: ", leftStickY);
            rightMotor.setPower(rightStickY * powerMultiplier);
            telemetry.addData("right: ", rightStickY * -1);
        }
    }
}
