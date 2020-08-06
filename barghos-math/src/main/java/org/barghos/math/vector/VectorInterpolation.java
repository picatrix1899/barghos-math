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

package org.barghos.math.vector;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.util.Nullable;
import org.barghos.math.vector.quat.Quat;
import org.barghos.math.vector.quat.QuatPool;
import org.barghos.math.vector.vec3.Vec3;

public class VectorInterpolation
{
	public static Vec3 lerp(Vec3 v1, Vec3 v2, float alpha, @Nullable Vec3 res)
	{
		if(v1 == null) throw new ArgumentNullException("v1");
		if(v2 == null) throw new ArgumentNullException("v2");
		
		if(res == null) res = new Vec3();
		
		res.setX(v1.getX() + alpha * (v2.getX() - v1.getX()));
		res.setY(v1.getY() + alpha * (v2.getY() - v1.getY()));
		res.setZ(v1.getZ() + alpha * (v2.getZ() - v1.getZ()));
		
		return res;
	}
	
	public static Quat lerp(Quat q1, Quat q2, float alpha, @Nullable Quat res)
	{
		if(q1 == null) throw new ArgumentNullException("q1");
		if(q2 == null) throw new ArgumentNullException("q2");
		
		if(res == null) res = new Quat();
		
		res.setX(q1.getX() + alpha * (q2.getX() - q1.getX()));
		res.setY(q1.getY() + alpha * (q2.getY() - q1.getY()));
		res.setZ(q1.getZ() + alpha * (q2.getZ() - q1.getZ()));
		res.setW(q1.getW() + alpha * (q2.getW() - q1.getW()));
		
		return res.normal();
	}
	
	public static Quat slerp(Quat q1, Quat q2, float alpha, @Nullable Quat res)
	{
		if(q1 == null) throw new ArgumentNullException("q1");
		if(q2 == null) throw new ArgumentNullException("q2");
		
		if(res == null) res = new Quat();
		
		q1 = q1.normal(QuatPool.get());
		q2 = q2.normal(QuatPool.get());
		
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
		
		QuatPool.store(q1, q2);
		
		return res.normal();
	}
}
