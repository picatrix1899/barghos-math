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

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.util.Nullable;
import org.barghos.math.point.Point3f;
import org.barghos.math.quat.Quatf;
import org.barghos.math.quat.pool.QuatfPool;
import org.barghos.math.utils.api.EulerAngles3fR;
import org.barghos.math.utils.api.EulerAngles3fW;
import org.barghos.math.utils.api.Transform3f;
import org.barghos.math.vec3.Vec3f;

public class VectorInterpolation
{	
	public static <T extends Tup3fW> T lerp(Tup3fR v1, Tup3fR v2, float alpha, T res)
	{
		if(v1 == null) throw new ArgumentNullException("v1");
		if(v2 == null) throw new ArgumentNullException("v2");

		res.setX(v1.getX() + alpha * (v2.getX() - v1.getX()));
		res.setY(v1.getY() + alpha * (v2.getY() - v1.getY()));
		res.setZ(v1.getZ() + alpha * (v2.getZ() - v1.getZ()));
		
		return res;
	}
	
	public static Quatf lerp(Quatf q1, Quatf q2, float alpha, @Nullable Quatf res)
	{
		if(q1 == null) throw new ArgumentNullException("q1");
		if(q2 == null) throw new ArgumentNullException("q2");
		
		if(res == null) res = new Quatf();
		
		res.setX(q1.getX() + alpha * (q2.getX() - q1.getX()));
		res.setY(q1.getY() + alpha * (q2.getY() - q1.getY()));
		res.setZ(q1.getZ() + alpha * (q2.getZ() - q1.getZ()));
		res.setW(q1.getW() + alpha * (q2.getW() - q1.getW()));
		
		return res.normal();
	}
	
	public static Quatf slerp(Quatf q1, Quatf q2, float alpha, @Nullable Quatf res)
	{
		if(q1 == null) throw new ArgumentNullException("q1");
		if(q2 == null) throw new ArgumentNullException("q2");
		
		if(res == null) res = new Quatf();
		
		q1 = q1.normal(QuatfPool.get());
		q2 = q2.normal(QuatfPool.get());
		
		float dot = q1.dot(q2);
		
		if(dot < 0)
		{
			q1 = q1.inverse();
			dot = -dot;
		}

		if(dot > 0.9995)
		{
			lerp(q1, q2, alpha, res);
		}
		else
		{
			float theta_0 = (float)Math.acos(dot);
			float theta = theta_0 * alpha;
			float sin_theta = (float)Math.sin(theta);
			float sin_theta_0 = (float)Math.sin(theta_0);
			
			float s0 = (float)Math.cos(theta) - dot * sin_theta / sin_theta_0;
			float s1 = sin_theta / sin_theta_0;
			
			res.setX(q1.getX() * s0 + q2.getX() * s1);
			res.setY(q1.getY() * s0 + q2.getY() * s1);
			res.setZ(q1.getZ() * s0 + q2.getZ() * s1);
			res.setW(q1.getW() * s0 + q2.getW() * s1);
		}
		
		QuatfPool.store(q1, q2);
		
		return res.normal();
	}
	
	public static <T extends EulerAngles3fW> T lerp(EulerAngles3fR angles1, EulerAngles3fR angles2, float alpha, T res)
	{
		float pitch = angles1.getPitchRad() + alpha * (angles2.getPitchRad() - angles1.getPitchRad());
		float yaw = angles1.getYawRad() + alpha * (angles2.getYawRad() - angles1.getYawRad());
		float roll = angles1.getRollRad() + alpha * (angles2.getRollRad() - angles1.getRollRad());
		
		res.setRad(pitch, yaw, roll);
		
		return res;
	}
	
	public static <T extends Transform3f> T lerp(Transform3f t1, Transform3f t2, float alpha, T res)
	{
		Point3f position = lerp(t1.getPosition(), t2.getPosition(), alpha, new Point3f());
		EulerAnglesDeg3f orientation = lerp(t1.getOrientation(), t2.getOrientation(), alpha, new EulerAnglesDeg3f());
		Vec3f scale = lerp(t1.getScale(), t2.getScale(), alpha, new Vec3f());
		
		res.set(position, orientation, scale);
		
		return res;
	}
}
