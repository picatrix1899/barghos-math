/*
MIT License

Copyright (c) 2019 picatrix1899

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.barghos.math.utils;

import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.quat.Quatf;
import org.barghos.math.quat.pool.QuatfPool;
import org.barghos.math.utils.api.EulerRotationOrder3;

public class EulerAngles3f
{
	private float pitch;
	private float yaw;
	private float roll;
	
	public EulerAngles3f()
	{
		set(0.0f, 0.0f, 0.0f);
	}
	
	public EulerAngles3f(EulerAngles3f e)
	{
		set(e);
	}
	
	public EulerAngles3f(float pitch, float yaw, float roll)
	{
		set(pitch, yaw, roll);
	}
	
	public EulerAngles3f(Mat3fR m)
	{
		set(m);
	}
	
	public EulerAngles3f set(float pitch, float yaw, float roll)
	{
		return setPitch(pitch).setYaw(yaw).setRoll(roll);
	}
	
	public EulerAngles3f set(EulerAngles3f e)
	{
		return set(e.getPitch(), e.getYaw(), e.getRoll());
	}
	
	public EulerAngles3f set(Mat3fR m)
	{
		float sy = Maths.sqrt(m.getCell(0, 0) * m.getCell(0,  0) + m.getCell(0, 1) * m.getCell(0, 1));
		
		if(!Maths.isZero(sy, Maths.SMALL_NUMBER_E6))
		{
			this.yaw = Maths.atan2(m.getCell(1, 2), m.getCell(2, 2)) * Maths.RAD_TO_DEGf;
			this.pitch = Maths.atan2(-m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = Maths.atan2(m.getCell(0, 1), m.getCell(0, 0)) * Maths.RAD_TO_DEGf;
		}
		else
		{
			this.yaw = Maths.atan2(-m.getCell(2, 1), m.getCell(1, 1)) * Maths.RAD_TO_DEGf;
			this.pitch = Maths.atan2(-m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = 0.0f * Maths.RAD_TO_DEGf;
		}

		return this;
	}
	
	public EulerAngles3f setPitch(float pitch)
	{
		this.pitch = pitch;
		
		return this;
	}
	
	public EulerAngles3f setYaw(float yaw)
	{
		this.yaw = yaw;
		
		return this;
	}
	
	public EulerAngles3f setRoll(float roll)
	{
		this.roll = roll;
		
		return this;
	}

	public float getPitch()
	{
		return this.pitch;
	}
	
	public float getYaw()
	{
		return this.yaw;
	}

	public float getRoll()
	{
		return this.roll;
	}

	public Quatf getPitchRotation(LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getRight(), this.pitch, res);
	}

	public Quatf getYawRotation(LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getUp(), this.yaw, res);
	}

	public Quatf getRollRotation(LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getForward(), this.roll, res);
	}

		public Quatf getRotation(LinearSystem3 system, Quatf res)
	{	
		if(res == null) res = new Quatf();

		Quatf q1 = QuatfPool.get();
		Quatf q2 = QuatfPool.get();
		Quatf q3 = QuatfPool.get();

		switch(BarghosMath.DEFAULT_EULER_ROTATION_ORDER)
		{
			case PITCH_YAW_ROLL:
				res.set(getRollRotation(system, q3).mul(getYawRotation(system, q2).mul(getPitchRotation(system, q1))));
			case PITCH_ROLL_YAW:
				res.set(getYawRotation(system, q3).mul(getRollRotation(system, q2).mul(getPitchRotation(system, q1))));
			case YAW_PITCH_ROLL:
				res.set(getRollRotation(system, q3).mul(getPitchRotation(system, q2).mul(getYawRotation(system, q1))));
			case YAW_ROLL_PITCH:
				res.set(getPitchRotation(system, q3).mul(getRollRotation(system, q2).mul(getYawRotation(system, q1))));
			case ROLL_PITCH_YAW:
				res.set(getYawRotation(system, q3).mul(getPitchRotation(system, q2).mul(getRollRotation(system, q1))));
			case ROLL_YAW_PITCH:
				res.set(getPitchRotation(system, q3).mul(getYawRotation(system, q2).mul(getRollRotation(system, q1))));
		}
		
		QuatfPool.store(q1, q2, q3);

		return res;
	}

	
	public Quatf getRotation(LinearSystem3 system, EulerRotationOrder3 order, Quatf res)
	{	
		if(res == null) res = new Quatf();

		Quatf q1 = QuatfPool.get();
		Quatf q2 = QuatfPool.get();
		Quatf q3 = QuatfPool.get();

		switch(order)
		{
			case PITCH_YAW_ROLL:
				res.set(getRollRotation(system, q3).mul(getYawRotation(system, q2).mul(getPitchRotation(system, q1))));
			case PITCH_ROLL_YAW:
				res.set(getYawRotation(system, q3).mul(getRollRotation(system, q2).mul(getPitchRotation(system, q1))));
			case YAW_PITCH_ROLL:
				res.set(getRollRotation(system, q3).mul(getPitchRotation(system, q2).mul(getYawRotation(system, q1))));
			case YAW_ROLL_PITCH:
				res.set(getPitchRotation(system, q3).mul(getRollRotation(system, q2).mul(getYawRotation(system, q1))));
			case ROLL_PITCH_YAW:
				res.set(getYawRotation(system, q3).mul(getPitchRotation(system, q2).mul(getRollRotation(system, q1))));
			case ROLL_YAW_PITCH:
				res.set(getPitchRotation(system, q3).mul(getYawRotation(system, q2).mul(getRollRotation(system, q1))));
		}
		
		QuatfPool.store(q1, q2, q3);

		return res;
	}

	public void rotate(float pitch, float yaw, float roll)
	{
		this.pitch += pitch;
		this.yaw += yaw;
		this.roll += roll;
	}
	
	public Mat3f toRotationMatrix3f()
	{
		return toRotationMatrix3f(new Mat3f());
	}
	
	public Mat3f toRotationMatrix3f(Mat3f res)
	{
		return res.initRotation3D(this);
	}
	
	public String toString()
	{
		return "eulerAngles3f(pitch=" + this.pitch + "f, yaw=" + this.yaw + "f, roll=" + this.roll + "f)";
	}
}
