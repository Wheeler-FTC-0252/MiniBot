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
    double nominalDistance = 0.03;
    double constantSpeed = 0.15;
    OpticalDistanceSensor sideDistanceSensor;
    OpticalDistanceSensor frontDistanceSensor;
    public void init(){
        leftMotor=hardwareMap.dcMotor.get("leftMotor");
        rightMotor=hardwareMap.dcMotor.get("rightMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        sideDistanceSensor=hardwareMap.opticalDistanceSensor.get("sideDistance");
        frontDistanceSensor=hardwareMap.opticalDistanceSensor.get("frontDistance");
    }
    public void loop(){
        sideDistance=sideDistanceSensor.getLightDetected();
        frontDistance=frontDistanceSensor.getLightDetected();
        telemetry.addData("Side",sideDistance);
        telemetry.addData("Front",frontDistance);
        leftFactor =sideDistance/nominalDistance;
        rightFactor =1/leftFactor;
        leftSpeed=leftFactor*constantSpeed;
        rightSpeed=rightFactor*constantSpeed;
        if (leftSpeed>1){
            leftSpeed=1;
        }
        else if (leftSpeed<-1){
            leftSpeed=-1;
        }
        if (rightSpeed>1){
            rightSpeed=1;
        }
        else if (rightSpeed<-1){
            rightSpeed=-1;
        }
        leftMotor.setPower(leftSpeed);
        rightMotor.setPower(rightSpeed);
        telemetry.addData("Left: ", leftSpeed);
        telemetry.addData("Right: ", rightSpeed);
    }


}
