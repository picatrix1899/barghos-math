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

import org.barghos.core.tuple3.api.Tup3fR;

import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.matrix.api.Mat4fR;
import org.barghos.math.utils.api.EulerAngles3f;
import org.barghos.math.utils.api.EulerAngles3fR;
import org.barghos.math.utils.api.EulerAngles3fW;

public class EulerAnglesDeg3f implements EulerAngles3f
{
	private float pitch;
	private float yaw;
	private float roll;
	
	public EulerAnglesDeg3f()
	{
		setDeg(0.0f, 0.0f, 0.0f);
	}
	
	public EulerAnglesDeg3f(EulerAngles3fR e)
	{
		set(e);
	}
	
	public EulerAnglesDeg3f(Tup3fR e)
	{
		set(e);
	}
	
	public EulerAnglesDeg3f(Mat3fR m)
	{
		set(m);
	}
	
	public EulerAnglesDeg3f(Mat4fR m)
	{
		set(m);
	}
	
	public EulerAnglesDeg3f(float pitch, float yaw, float roll)
	{
		setDeg(pitch, yaw, roll);
	}
	
	public EulerAnglesDeg3f set(EulerAngles3fR e)
	{
		return setDeg(e.getPitchDeg(), e.getYawDeg(), e.getRollDeg());
	}
	
	public EulerAnglesDeg3f set(Tup3fR e)
	{
		return setRad(e.getX(), e.getY(), e.getZ());
	}
	
	public EulerAnglesDeg3f set(Mat3fR m)
	{
		float sy = Maths.sqrt(m.getCell(0, 0) * m.getCell(0,  0) + m.getCell(0, 1) * m.getCell(0, 1));
		
		if(!Maths.isZero(sy, Maths.SMALL_NUMBER_E6))
		{
			this.pitch = Maths.atan2(m.getCell(1, 2), m.getCell(2, 2)) * Maths.RAD_TO_DEGf;
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = Maths.atan2(m.getCell(0, 1), m.getCell(0, 0)) * Maths.RAD_TO_DEGf;
		}
		else
		{
			this.pitch = Maths.atan2(-m.getCell(2, 1), m.getCell(1, 1)) * Maths.RAD_TO_DEGf;
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = 0.0f * Maths.RAD_TO_DEGf;
		}

		return this;
	}
	
	public EulerAnglesDeg3f set(Mat4fR m)
	{
		float sy = Maths.sqrt(m.getCell(0, 0) * m.getCell(0,  0) + m.getCell(0, 1) * m.getCell(0, 1));
		
		if(!Maths.isZero(sy, Maths.SMALL_NUMBER_E6))
		{
			System.out.println("First " + sy);
			this.pitch = Maths.atan2(m.getCell(2, 1), m.getCell(2, 2)) * Maths.RAD_TO_DEGf;
			this.yaw = Maths.atan2(m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = Maths.atan2(m.getCell(0, 1), -m.getCell(0, 0)) * Maths.RAD_TO_DEGf;
		}
		else
		{
			System.out.println("Second");
			this.pitch = Maths.atan2(m.getCell(1, 2), m.getCell(1, 1)) * Maths.RAD_TO_DEGf;
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy) * Maths.RAD_TO_DEGf;
			this.roll = 0.0f * Maths.RAD_TO_DEGf;
		}

		return this;
	}
	
	public EulerAnglesDeg3f setDeg(float pitch, float yaw, float roll)
	{
		return setPitchDeg(pitch).setYawDeg(yaw).setRollDeg(roll);
	}

	public EulerAnglesDeg3f setPitchDeg(float pitch)
	{
		this.pitch = pitch;
		
		return this;
	}
	
	public EulerAnglesDeg3f setYawDeg(float yaw)
	{
		this.yaw = yaw;
		
		return this;
	}
	
	public EulerAnglesDeg3f setRollDeg(float roll)
	{
		this.roll = roll;
		
		return this;
	}
	
	public EulerAnglesDeg3f setRad(float pitch, float yaw, float roll)
	{
		return setPitchRad(pitch).setYawRad(yaw).setRollRad(roll);
	}

	public EulerAnglesDeg3f setPitchRad(float pitch)
	{
		this.pitch = pitch * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public EulerAnglesDeg3f setYawRad(float yaw)
	{
		this.yaw = yaw * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public EulerAnglesDeg3f setRollRad(float roll)
	{
		this.roll = roll * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public float getPitchDeg()
	{
		return this.pitch;
	}
	
	public float getYawDeg()
	{
		return this.yaw;
	}

	public float getRollDeg()
	{
		return this.roll;
	}

	public float getPitchRad()
	{
		return this.pitch * Maths.DEG_TO_RADf;
	}
	
	public float getYawRad()
	{
		return this.yaw * Maths.DEG_TO_RADf;
	}

	public float getRollRad()
	{
		return this.roll * Maths.DEG_TO_RADf;
	}
	
	public EulerAnglesDeg3f advance(EulerAngles3fR angles)
	{
		return advanceDeg(angles.getPitchDeg(), angles.getYawDeg(), angles.getRollDeg());
	}
	
	public EulerAnglesDeg3f advance(Tup3fR t)
	{
		return advanceRad(t.getX(), t.getY(), t.getZ());
	}
	
	public EulerAnglesDeg3f advanceDeg(float pitch, float yaw, float roll)
	{
		return advancePitchDeg(pitch).advanceYawDeg(yaw).advanceRollDeg(roll);
	}
	
	public EulerAnglesDeg3f advancePitchDeg(float pitch)
	{
		this.pitch += pitch;
		
		return this;
	}
	
	public EulerAnglesDeg3f advanceYawDeg(float yaw)
	{
		this.yaw += yaw;
		
		return this;
	}
	
	public EulerAnglesDeg3f advanceRollDeg(float roll)
	{
		this.roll += roll;
		
		return this;
	}
	
	public EulerAnglesDeg3f advanceRad(float pitch, float yaw, float roll)
	{
		return advancePitchRad(pitch).advanceYawRad(yaw).advanceRollRad(roll);
	}
	
	public EulerAnglesDeg3f advancePitchRad(float pitch)
	{
		this.pitch += pitch * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public EulerAnglesDeg3f advanceYawRad(float yaw)
	{
		this.yaw += yaw * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public EulerAnglesDeg3f advanceRollRad(float roll)
	{
		this.roll += roll * Maths.RAD_TO_DEGf;
		
		return this;
	}
	
	public EulerAnglesDeg3f invert()
	{
		return invert(this);
	}
	
	public <T extends EulerAngles3fW> T invert(T res)
	{
		res.setDeg(-this.pitch, -this.yaw, -this.roll);
		return res;
	}
	
	public EulerAnglesDeg3f invertN()
	{
		return new EulerAnglesDeg3f(-this.pitch, -this.yaw, -this.roll);
	}
	
	public EulerAnglesDeg3f clone()
	{
		return new EulerAnglesDeg3f(this);
	}
	
	public String toString()
	{
		return "eulerAnglesDeg3f(pitch=" + this.pitch + "f, yaw=" + this.yaw + "f, roll=" + this.roll + "f)";
	}
}
