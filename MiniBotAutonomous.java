package org.wheeler.robotics.MiniBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by lucien on 8/27/15.
 */
public class MiniBotAutonomous extends OpMode{
    DcMotor leftMotor;
    DcMotor rightMotor;
    double leftFactor;
    double rightFactor;
    double leftSpeed;
    double rightSpeed;
    double sideDistance;
    double frontDistance;
    double sideOffAmount=0.02;
    double nominalDistance = 0.01;
    double constantSpeed = 0.05;
    double maxSpeed=0.75;
    double minSpeed=0.03;
    OpticalDistanceSensor sideDistanceSensor;
    OpticalDistanceSensor frontDistanceSensor;
    public void init(){
        leftMotor=hardwareMap.dcMotor.get("left");
        rightMotor=hardwareMap.dcMotor.get("right");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        sideDistanceSensor=hardwareMap.opticalDistanceSensor.get("side");
        frontDistanceSensor=hardwareMap.opticalDistanceSensor.get("front");
    }
    public void loop(){
        sideDistance=sideDistanceSensor.getLightDetected()-sideOffAmount;
        frontDistance=frontDistanceSensor.getLightDetected();
        telemetry.addData("Side",sideDistance);
        telemetry.addData("Front",frontDistance);
        leftFactor =sideDistance/nominalDistance;
        rightFactor =1/leftFactor;
        leftSpeed=leftFactor*constantSpeed;
        rightSpeed=rightFactor*constantSpeed;
        if (leftSpeed>maxSpeed){
            leftSpeed=maxSpeed;
        }
        else if (leftSpeed<minSpeed){
            leftSpeed=minSpeed;
        }
        if (rightSpeed>maxSpeed){
            rightSpeed=maxSpeed;
        }
        else if (rightSpeed<minSpeed){
            rightSpeed=minSpeed;
        }
        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);
        telemetry.addData("Left: ", leftSpeed);
        telemetry.addData("Left Factor: ", leftFactor);
        telemetry.addData("Right: ", rightSpeed);
        telemetry.addData("Right Factor: ", rightFactor);
    }


}
