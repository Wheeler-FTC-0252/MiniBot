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
    DcMotor leftMotor;
    DcMotor rightMotor;
    public void init(){
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
    }

    public void loop(){
        leftStickX=gamepad1.left_stick_x;
        leftStickY=gamepad1.left_stick_y;
        rightStickX=gamepad1.right_stick_x;
        rightStickY=gamepad1.right_stick_y;
        leftMotor.setPower(leftStickY);
        rightMotor.setPower(rightStickY);
    }
}
