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

public class EulerAnglesRad3f implements EulerAngles3f
{
	private float pitch;
	private float yaw;
	private float roll;
	
	public EulerAnglesRad3f()
	{
		setRad(0.0f, 0.0f, 0.0f);
	}
	
	public EulerAnglesRad3f(EulerAngles3fR e)
	{
		set(e);
	}
	
	public EulerAnglesRad3f(Tup3fR e)
	{
		set(e);
	}
	
	public EulerAnglesRad3f(Mat3fR m)
	{
		set(m);
	}
	
	public EulerAnglesRad3f(Mat4fR m)
	{
		set(m);
	}
	
	public EulerAnglesRad3f(float pitch, float yaw, float roll)
	{
		setRad(pitch, yaw, roll);
	}
	
	public EulerAnglesRad3f set(EulerAngles3fR e)
	{
		return setRad(e.getPitchRad(), e.getYawRad(), e.getRollRad());
	}
	
	public EulerAnglesRad3f set(Tup3fR e)
	{
		return setRad(e.getX(), e.getY(), e.getZ());
	}
	
	public EulerAnglesRad3f set(Mat3fR m)
	{
		float sy = Maths.sqrt(m.getCell(0, 0) * m.getCell(0,  0) + m.getCell(0, 1) * m.getCell(0, 1));
		
		if(!Maths.isZero(sy, Maths.SMALL_NUMBER_E6))
		{
			this.pitch = Maths.atan2(m.getCell(1, 2), m.getCell(2, 2));
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy);
			this.roll = Maths.atan2(m.getCell(0, 1), m.getCell(0, 0));
		}
		else
		{
			this.pitch = Maths.atan2(-m.getCell(2, 1), m.getCell(1, 1));
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy);
			this.roll = 0.0f;
		}

		return this;
	}
	
	public EulerAnglesRad3f set(Mat4fR m)
	{
		float sy = Maths.sqrt(m.getCell(0, 0) * m.getCell(0,  0) + m.getCell(0, 1) * m.getCell(0, 1));
		
		if(!Maths.isZero(sy, Maths.SMALL_NUMBER_E6))
		{
			this.pitch = Maths.atan2(m.getCell(1, 2), m.getCell(2, 2));
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy);
			this.roll = Maths.atan2(m.getCell(0, 1), m.getCell(0, 0));
		}
		else
		{
			this.pitch = Maths.atan2(-m.getCell(2, 1), m.getCell(1, 1));
			this.yaw = Maths.atan2(-m.getCell(0, 2), sy);
			this.roll = 0.0f * Maths.RAD_TO_DEGf;
		}

		return this;
	}
	
	public EulerAnglesRad3f setDeg(float pitch, float yaw, float roll)
	{
		return setPitchDeg(pitch).setYawDeg(yaw).setRollDeg(roll);
	}

	public EulerAnglesRad3f setPitchDeg(float pitch)
	{
		this.pitch = pitch * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f setYawDeg(float yaw)
	{
		this.yaw = yaw * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f setRollDeg(float roll)
	{
		this.roll = roll * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f setRad(float pitch, float yaw, float roll)
	{
		return setPitchRad(pitch).setYawRad(yaw).setRollRad(roll);
	}

	public EulerAnglesRad3f setPitchRad(float pitch)
	{
		this.pitch = pitch;
		
		return this;
	}
	
	public EulerAnglesRad3f setYawRad(float yaw)
	{
		this.yaw = yaw;
		
		return this;
	}
	
	public EulerAnglesRad3f setRollRad(float roll)
	{
		this.roll = roll;
		
		return this;
	}
	
	public float getPitchDeg()
	{
		return this.pitch * Maths.RAD_TO_DEGf;
	}
	
	public float getYawDeg()
	{
		return this.yaw * Maths.RAD_TO_DEGf;
	}

	public float getRollDeg()
	{
		return this.roll * Maths.RAD_TO_DEGf;
	}

	public float getPitchRad()
	{
		return this.pitch;
	}
	
	public float getYawRad()
	{
		return this.yaw;
	}

	public float getRollRad()
	{
		return this.roll;
	}
	
	public EulerAnglesRad3f advance(EulerAngles3fR angles)
	{
		return advanceRad(angles.getPitchRad(), angles.getYawRad(), angles.getRollRad());
	}
	
	public EulerAnglesRad3f advance(Tup3fR t)
	{
		return advanceRad(t.getX(), t.getY(), t.getZ());
	}
	
	public EulerAnglesRad3f advanceDeg(float pitch, float yaw, float roll)
	{
		return advancePitchDeg(pitch).advanceYawDeg(yaw).advanceRollDeg(roll);
	}
	
	public EulerAnglesRad3f advancePitchDeg(float pitch)
	{
		this.pitch += pitch * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f advanceYawDeg(float yaw)
	{
		this.yaw += yaw * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f advanceRollDeg(float roll)
	{
		this.roll += roll * Maths.DEG_TO_RADf;
		
		return this;
	}
	
	public EulerAnglesRad3f advanceRad(float pitch, float yaw, float roll)
	{
		return advancePitchRad(pitch).advanceYawRad(yaw).advanceRollRad(roll);
	}
	
	public EulerAnglesRad3f advancePitchRad(float pitch)
	{
		this.pitch += pitch;
		
		return this;
	}
	
	public EulerAnglesRad3f advanceYawRad(float yaw)
	{
		this.yaw += yaw;
		
		return this;
	}
	
	public EulerAnglesRad3f advanceRollRad(float roll)
	{
		this.roll += roll;
		
		return this;
	}
	
	public EulerAnglesRad3f invert()
	{
		return invert(this);
	}
	
	public <T extends EulerAngles3fW> T invert(T res)
	{
		res.setRad(-this.pitch, -this.yaw, -this.roll);
		return res;
	}
	
	public EulerAnglesRad3f invertN()
	{
		return new EulerAnglesRad3f(-this.pitch, -this.yaw, -this.roll);
	}
	
	public EulerAnglesRad3f clone()
	{
		return new EulerAnglesRad3f(this);
	}
	
	public String toString()
	{
		return "eulerAnglesRad3f(pitch=" + this.pitch + "f, yaw=" + this.yaw + "f, roll=" + this.roll + "f)";
	}
}
