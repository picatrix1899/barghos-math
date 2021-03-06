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

package org.barghos.math.quat;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat4f;
import org.barghos.math.quat.pool.QuatfPool;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.pool.Vec3fPool;
import org.barghos.math.vec4.api.Vec4fR;

/** A 3-Dimensional Quaternion */
public class Quatf implements Vec4fR
{
	/** The w component. */
	protected float w;
	/** The x component. */
	protected float x;
	/** The y component. */
	protected float y;
	/** The z component. */
	protected float z;

	/**
	 * The default constructor. It sets x,y and z to 0 and w to 1.
	 * It is commonly used in pools.
	 */
	public Quatf()
	{
		set(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	/**
	 * This constructor sets the components to x, y, z and w.
	 * @param w The new w component.
	 * @param x The new x component.
	 * @param y The new y component.
	 * @param z The new z component.
	 */
	public Quatf(float w, float x, float y, float z)
	{
		set(w, x, y, z);
	}
	
	/**
	 * This contructor sets the components to the values of q.
	 * @param q A Quaternion that the components are set to.
	 */
	public Quatf(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		set(q);
	}
	
	/**
	 * This constructor sets
	 * @param rot
	 */
	public Quatf(Mat4f rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		set(rot);
	}

	public static Quatf getFromAxis(Tup3fR axis, float angle) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, null); }
	
	public static Quatf getFromAxis(float ax, float ay, float az, float angle) { return getFromAxis(ax, ay, az, angle, null); }
	
	public static Quatf getFromAxis(Tup3fR axis, float angle, Quatf res) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, res); }
	
	public static Quatf getFromAxis(float ax, float ay, float az, float angle, Quatf res)
	{
		if(res == null) res = new Quatf();
		
		float halfAngle = angle * 0.5f * Maths.DEG_TO_RADf;
		float sinHalfAngle = Maths.sin(halfAngle);
		float cosHalfAngle = Maths.cos(halfAngle);
		
		float rX = ax * sinHalfAngle;
		float rY = ay * sinHalfAngle;
		float rZ = az * sinHalfAngle;
		float rW = cosHalfAngle;
		
		return res.set(rW, rX, rY, rZ).normal();
	}
	
	public static Quatf getFromVectors(Vec3f v1, Vec3f v2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v1 == null) throw new ArgumentNullException("v1");
			if(v2 == null) throw new ArgumentNullException("v2");
		}
		
		
		Vec3f a = v1.normal(Vec3fPool.get());
		Vec3f b = v2.normal(Vec3fPool.get());

		Vec3f axis = a.cross(b, Vec3fPool.get());
		axis.normal(axis);
		
		float angle = 1.0f + a.dot(b);

		Quatf out = new Quatf(angle, axis.getX(), axis.getY(), axis.getZ()).normal();
		
		Vec3fPool.store(a, b, axis);
		
		return out;
	}
	
	public static Quatf lookAt(Tup3fR source, Tup3fR dest)
	{
		Vec3f forwardVector = new Vec3f(dest).subN(source);

		Vec3f forward = BarghosMath.DEFAULT_SYSTEM.getForward();
		Vec3f up = BarghosMath.DEFAULT_SYSTEM.getUp();
		
	    float dot = forward.dot(forwardVector);

	    if (Math.abs(dot - (-1.0f)) < 0.000001f)
	    {
	        return new Quatf(Maths.PIf, up.getX(), up.getY(), up.getZ());
	    }
	    if (Math.abs(dot - (1.0f)) < 0.000001f)
	    {
	        return new Quatf();
	    }

	    float rotAngle = (float)Math.acos(dot);
	    Vec3f rotAxis = forward.cross(forwardVector);
	    rotAxis.normal();
	    return getFromAxis(rotAxis, rotAngle);
	}
	
	public float getW() { return this.w; }
	
	public float getX() { return this.x; }
	
	public float getY() { return this.y; }
	
	public float getZ() { return this.z; }
	
	public Quatf rotate(Tup3fR axis, float angle)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(axis == null) throw new ArgumentNullException("axis");
		}
		
		return rotate(angle, axis.getX(), axis.getY(), axis.getZ());
	}
	
	public Quatf rotate(float ax, float ay, float az, float angle)
	{
		return getFromAxis(angle, ax, ay, az).mul(this, this);
	}
	
	public Quatf rotate(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return q.mul(this, this);
	}
	
	public Quatf set(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return set(q.getW(), q.getX(), q.getY(), q.getZ());
	}
	
	//From Ken Shoemake's "Quaternion Calculus and Fast Animation" article
	public Quatf set(Mat4f rot) 
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		double trace = rot.m[0][0] + rot.m[1][1] + rot.m[2][2];

		if(trace > 0)
		{
			float s = 0.5f / (float)Maths.sqrt(trace + 1.0);
			this.w = 0.25f / s;
			
			
			this.x = (rot.m[1][2] - rot.m[2][1]) * s;
			this.y = (rot.m[2][0] - rot.m[0][2]) * s;
			this.z = (rot.m[0][1] - rot.m[1][0]) * s;
		}
		else
		{
			if(rot.m[0][0] > rot.m[1][1] && rot.m[0][0] > rot.m[2][2])
			{
				float s = 2.0f * (float)Maths.sqrt(1.0 + rot.m[0][0] - rot.m[1][1] - rot.m[2][2]);
				this.w = (rot.m[1][2] - rot.m[2][1]) / s;
				this.x = 0.25f * s;
				this.y = (rot.m[1][0] + rot.m[0][1]) / s;
				this.z = (rot.m[2][0] + rot.m[0][2]) / s;
			}
			else if(rot.m[1][1] > rot.m[2][2])
			{
				float s = 2.0f * (float)Maths.sqrt(1.0 + rot.m[1][1] - rot.m[0][0] - rot.m[2][2]);
				this.w = (rot.m[2][0] - rot.m[0][2]) / s;
				this.x = (rot.m[1][0] + rot.m[0][1]) / s;
				this.y = 0.25f * s;
				this.z = (rot.m[2][1] + rot.m[1][2]) / s;
			}
			else
			{
				float s = 2.0f * (float)Maths.sqrt(1.0 + rot.m[2][2] - rot.m[0][0] - rot.m[1][1]);
				this.w = (rot.m[0][1] - rot.m[1][0] ) / s;
				this.x = (rot.m[2][0] + rot.m[0][2] ) / s;
				this.y = (rot.m[1][2] + rot.m[2][1] ) / s;
				this.z = 0.25f * s;
			}
		}

		normal(this);
		
		return this;
	}
	
	public Quatf set(float w, float x, float y, float z) { return setW(w).setX(x).setY(y).setZ(z); }
	
	public Quatf setW(float w) { this.w = w; return this; }
	
	public Quatf setX(float x) { this.x = x; return this; }
	
	public Quatf setY(float y) { this.y = y; return this; }
	
	public Quatf setZ(float z) { this.z = z; return this; }

	public Quatf conjugate()
	{
		return conjugate(this);
	}
	
	public Quatf conjugate(@Nullable Quatf res)
	{
		if(res == null) res = new Quatf();

		res.set(this.w, -this.x, -this.y, -this.z);

		return res;
	}
	
	public Quatf inverse()
	{
		return inverse(this);
	}
	
	public Quatf inverse(@Nullable Quatf res)
	{
		if(res == null) res = new Quatf();

		float l = reciprocalLength();
		
		res.set(this.w * l, -this.x * l, -this.y * l, -this.z * l);
		
		return res;
	}
	
	public Quatf mul(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		mul(q, this);
		
		return this;
	}
	
	public Quatf mul(Quatf q, @Nullable Quatf res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		if(res == null) res = new Quatf();
		
		float w_ = this.w * q.getW() - this.x * q.getX() - this.y * q.getY() - this.z * q.getZ(); // w * w' - v * v'
		float x_ = this.w * q.getX() + q.getW() * this.x + this.y * q.getZ() - this.z * q.getY(); // s * v'.x + s' * v.x + (V x V').x
		float y_ = this.w * q.getY() + q.getW() * this.y + this.z * q.getX() - this.x * q.getZ(); // s * v'.y + s' * v.y + (V x V').y
		float z_ = this.w * q.getZ() + q.getW() * this.z + this.x * q.getY() - this.y * q.getX(); // s * v'.z + s' * v.z + (V x V').z

		res.set(w_, x_, y_, z_);

		return res;
	}
	
	public Quatf mul(Tup3fR v)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		mul(v, this);
		
		return this;
	}
	
	public Quatf mul(Tup3fR v, @Nullable Quatf res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Quatf();
		
		float w_ = -this.x * v.getX() - this.y * v.getY() - this.z * v.getZ(); // - v * v'
		float x_ =  this.w * v.getX() + this.y * v.getZ() - this.z * v.getY(); // s * v'.x ...
		float y_ =  this.w * v.getY() + this.z * v.getX() - this.x * v.getZ(); // s * v'.y ...
		float z_ =  this.w * v.getZ() + this.x * v.getY() - this.y * v.getX(); // s * v*.z ...

		res.set(w_, x_, y_, z_);

		return res;
	}
	
	public Vec3f transform(Tup3fR v, @Nullable Vec3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Vec3f();
		
		Quatf r = mul(v, QuatfPool.get());
		Quatf c = conjugate(QuatfPool.get());
		
		r.mul(c, r);
		
		res.set(r.getX(),r.getY(), r.getZ());
		
		QuatfPool.store(r, c);
		
		return res;
	}
	
	public float length() { return (float)Maths.sqrt(squaredLength()); }
	public float squaredLength() { return this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z; }
	public float reciprocalLength() { return 1.0f / length(); }
	
	public Quatf normal()
	{
		return normal(this);
	}
	
	public Quatf normal(@Nullable Quatf res)
	{
		if(res == null) res = new Quatf();
		
		float l = reciprocalLength();
		
		res.set(this.w * l, this.x * l, this.y * l, this.z * l);

		return res;
	}

	public float dot(Quatf q)
	{
		return this.w * q.w + this.x * q.x + this.y * q.y + this.z * q.z;
	}

	public String toString()
	{
		return "quat(w=" + this.w + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
	}
	
	public Quatf clone()
	{
		return new Quatf(this);
	}
}
