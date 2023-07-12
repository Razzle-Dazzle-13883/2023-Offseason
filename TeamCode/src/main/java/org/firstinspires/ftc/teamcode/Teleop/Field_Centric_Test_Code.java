package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp(name="Field Centric Test")
public class Field_Centric_Test_Code extends OpMode{

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    IMU imu;

    public void init() {

        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);
    }

    public void loop() {

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

        double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = strafe * Math.cos(-robotHeading) - forward * Math.sin(-robotHeading);
        double rotY = strafe * Math.sin(-robotHeading) - forward * Math.cos(-robotHeading);

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double leftFrontPower = (rotY + rotX + rx) / denominator;
        double leftBackPower = (rotY - rotX + rx) / denominator;
        double rightFrontPower = (rotY + rotX - rx) / denominator;
        double rightBackPower = (rotY - rotX - rx) / denominator;

        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);

    }
}
