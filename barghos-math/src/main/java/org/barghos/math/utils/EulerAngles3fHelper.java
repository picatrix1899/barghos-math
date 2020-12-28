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
import org.barghos.math.quat.Quatf;
import org.barghos.math.quat.pool.QuatfPool;
import org.barghos.math.utils.api.EulerAngles3fR;
import org.barghos.math.utils.api.EulerRotationOrder3;

/**
 * @author picatrix1899
 *
 */
public class EulerAngles3fHelper
{
	public Quatf getPitchRotation(EulerAngles3fR angles, LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getRight(), angles.getPitchDeg(), res);
	}

	public Quatf getYawRotation(EulerAngles3fR angles, LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getUp(), angles.getYawDeg(), res);
	}

	public Quatf getRollRotation(EulerAngles3fR angles, LinearSystem3 system, Quatf res)
	{
		if(res == null) res = new Quatf();

		return Quatf.getFromAxis(system.getForward(), angles.getRollDeg(), res);
	}

		public Quatf getRotation(EulerAngles3fR angles, LinearSystem3 system, Quatf res)
	{	
		if(res == null) res = new Quatf();

		Quatf q1 = QuatfPool.get();
		Quatf q2 = QuatfPool.get();
		Quatf q3 = QuatfPool.get();

		switch(BarghosMath.DEFAULT_EULER_ROTATION_ORDER)
		{
			case PITCH_YAW_ROLL:
				res.set(getRollRotation(angles, system, q3).mul(getYawRotation(angles, system, q2).mul(getPitchRotation(angles, system, q1))));
			case PITCH_ROLL_YAW:
				res.set(getYawRotation(angles, system, q3).mul(getRollRotation(angles,  system, q2).mul(getPitchRotation(angles, system, q1))));
			case YAW_PITCH_ROLL:
				res.set(getRollRotation(angles, system, q3).mul(getPitchRotation(angles, system, q2).mul(getYawRotation(angles, system, q1))));
			case YAW_ROLL_PITCH:
				res.set(getPitchRotation(angles, system, q3).mul(getRollRotation(angles, system, q2).mul(getYawRotation(angles, system, q1))));
			case ROLL_PITCH_YAW:
				res.set(getYawRotation(angles, system, q3).mul(getPitchRotation(angles, system, q2).mul(getRollRotation(angles, system, q1))));
			case ROLL_YAW_PITCH:
				res.set(getPitchRotation(angles, system, q3).mul(getYawRotation(angles, system, q2).mul(getRollRotation(angles, system, q1))));
		}
		
		QuatfPool.store(q1, q2, q3);

		return res;
	}

	
	public Quatf getRotation(EulerAngles3fR angles, LinearSystem3 system, EulerRotationOrder3 order, Quatf res)
	{	
		if(res == null) res = new Quatf();

		Quatf q1 = QuatfPool.get();
		Quatf q2 = QuatfPool.get();
		Quatf q3 = QuatfPool.get();

		switch(order)
		{
			case PITCH_YAW_ROLL:
				res.set(getRollRotation(angles, system, q3).mul(getYawRotation(angles, system, q2).mul(getPitchRotation(angles, system, q1))));
			case PITCH_ROLL_YAW:
				res.set(getYawRotation(angles, system, q3).mul(getRollRotation(angles, system, q2).mul(getPitchRotation(angles, system, q1))));
			case YAW_PITCH_ROLL:
				res.set(getRollRotation(angles, system, q3).mul(getPitchRotation(angles, system, q2).mul(getYawRotation(angles, system, q1))));
			case YAW_ROLL_PITCH:
				res.set(getPitchRotation(angles, system, q3).mul(getRollRotation(angles, system, q2).mul(getYawRotation(angles, system, q1))));
			case ROLL_PITCH_YAW:
				res.set(getYawRotation(angles, system, q3).mul(getPitchRotation(angles, system, q2).mul(getRollRotation(angles, system, q1))));
			case ROLL_YAW_PITCH:
				res.set(getPitchRotation(angles, system, q3).mul(getYawRotation(angles, system, q2).mul(getRollRotation(angles, system, q1))));
		}
		
		QuatfPool.store(q1, q2, q3);

		return res;
	}
}
