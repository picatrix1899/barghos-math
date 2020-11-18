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

package org.barghos.math;

import org.barghos.math.vector.quat.Quatf;
import org.barghos.math.vector.quat.QuatfPool;

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
	
	public EulerAngles3f set(float pitch, float yaw, float roll) { return setPitch(pitch).setYaw(yaw).setRoll(roll); }
	
	public EulerAngles3f set(EulerAngles3f e) { return set(e.getPitch(), e.getYaw(), e.getRoll()); }
	
	public EulerAngles3f setPitch(float pitch) { this.pitch = pitch; return this; }
	
	public EulerAngles3f setYaw(float yaw) { this.yaw = yaw; return this; }
	
	public EulerAngles3f setRoll(float roll) { this.roll = roll; return this; }

	public float getPitch() { return this.pitch; }
	
	public float getYaw() { return this.yaw; }

	public float getRoll() { return this.roll; }

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

		res.set(getRollRotation(system, q3).mul(getYawRotation(system, q2).mul(getPitchRotation(system, q1))));

		QuatfPool.store(q1, q2, q3);

		return res;
	}

	public void rotate(float pitch, float yaw, float roll)
	{
		this.pitch += pitch;
		this.yaw += yaw;
		this.roll += roll;
	}
	
	public String toString()
	{
		return "eulerAngles3f(pitch=" + this.pitch + "f, yaw=" + this.yaw + "f, roll=" + this.roll + "f)";
	}
}
