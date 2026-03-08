package frc.hawklib.hid;

import edu.wpi.first.math.MathUtil;

public class XboxController extends edu.wpi.first.wpilibj.XboxController {
    private double mAxisDeadzoneThreshold = 0.10;
    private double mTriggerButtonThreshold = 0.50;

    private boolean mYAxisInverted = false;
    
    public XboxController(int port) { super(port); }

	public void configAxisDeadzone(double value) { mAxisDeadzoneThreshold = value; }
	public void configTriggerThreshold(double value) { mTriggerButtonThreshold = value; }

	public void configYAxisInverted(boolean isInverted) { mYAxisInverted = isInverted; }

    @Override public double getRawAxis(int axis) { return MathUtil.applyDeadband(super.getRawAxis(axis), mAxisDeadzoneThreshold); }

    @Override public double getLeftY() { return mYAxisInverted ? -super.getLeftY() : super.getLeftY(); }
    @Override public double getRightY() { return mYAxisInverted ? -super.getRightY() : super.getRightY(); }

	public boolean getLeftTrigger() { return getLeftTriggerAxis() >= mTriggerButtonThreshold; }
	public boolean getRightTrigger() { return getRightTriggerAxis() >= mTriggerButtonThreshold; }
	public double getTriggerAxis() { return getRightTriggerAxis() - getLeftTriggerAxis(); }
}
