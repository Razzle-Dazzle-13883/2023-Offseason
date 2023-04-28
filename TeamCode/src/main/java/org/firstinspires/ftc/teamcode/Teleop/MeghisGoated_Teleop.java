package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MeghisPogONG") //tells the name of the program on the phone
public class MeghisGoated_Teleop extends OpMode{

    //Defining motors
    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront = null;

    @Override
    // what it does when we initialize
    public void init() {

        //telling the robot which variable corresponds to the name of the motor
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {

        // the equation for tank drive and their motors
        double x = gamepad1.left_stick_y;
        double y = gamepad1.right_stick_y;

        leftBack.setPower(x);
        leftFront.setPower(x);
        rightBack.setPower(y);
        rightFront.setPower(y);

    }

}


