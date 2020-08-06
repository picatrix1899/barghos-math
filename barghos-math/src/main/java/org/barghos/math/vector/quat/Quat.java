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

package org.barghos.math.vector.quat;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;
import org.barghos.math.vector.vec4.Vec4R;

/** A 3-Dimensional Quaternion */
public class Quat implements Vec4R
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
	public Quat()
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
	public Quat(float w, float x, float y, float z)
	{
		set(w, x, y, z);
	}
	
	/**
	 * This contructor sets the components to the values of q.
	 * @param q A Quaternion that the components are set to.
	 */
	public Quat(Quat q)
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
	public Quat(Mat4 rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		set(rot);
	}

	public static Quat getFromAxis(Tup3fR axis, float angle) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, null); }
	
	public static Quat getFromAxis(float ax, float ay, float az, float angle) { return getFromAxis(ax, ay, az, angle, null); }
	
	public static Quat getFromAxis(Tup3fR axis, float angle, Quat res) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, res); }
	
	public static Quat getFromAxis(float ax, float ay, float az, float angle, Quat res)
	{
		if(res == null) res = new Quat();
		
		float halfAngle = angle * 0.5f * (float)Maths.DEG_TO_RAD;
		float sinHalfAngle = (float)Maths.sin(halfAngle);
		float cosHalfAngle = (float)Maths.cos(halfAngle);
		
		float rX = ax * sinHalfAngle;
		float rY = ay * sinHalfAngle;
		float rZ = az * sinHalfAngle;
		float rW = cosHalfAngle;
		
		return res.set(rW, rX, rY, rZ).normal();
	}
	
	public static Quat getFromVectors(Vec3 v1, Vec3 v2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v1 == null) throw new ArgumentNullException("v1");
			if(v2 == null) throw new ArgumentNullException("v2");
		}
		
		
		Vec3 a = v1.normal(Vec3Pool.get());
		Vec3 b = v2.normal(Vec3Pool.get());

		Vec3 axis = a.cross(b, Vec3Pool.get());
		axis.normal(axis);
		
		float angle = 1.0f + a.dot(b);

		Quat out = new Quat(angle, axis.getX(), axis.getY(), axis.getZ()).normal();
		
		Vec3Pool.store(a, b, axis);
		
		return out;
	}
	
	public float getW() { return this.w; }
	
	public float getX() { return this.x; }
	
	public float getY() { return this.y; }
	
	public float getZ() { return this.z; }
	
	public Quat rotate(Tup3fR axis, float angle)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(axis == null) throw new ArgumentNullException("axis");
		}
		
		return rotate(angle, axis.getX(), axis.getY(), axis.getZ());
	}
	
	public Quat rotate(float ax, float ay, float az, float angle)
	{
		return getFromAxis(angle, ax, ay, az).mul(this, this);
	}
	
	public Quat rotate(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return q.mul(this, this);
	}
	
	public Quat set(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return set(q.getW(), q.getX(), q.getY(), q.getZ());
	}
	
	//From Ken Shoemake's "Quaternion Calculus and Fast Animation" article
	public Quat set(Mat4 rot) 
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
	
	public Quat set(float w, float x, float y, float z) { return setW(w).setX(x).setY(y).setZ(z); }
	
	public Quat setW(float w) { this.w = w; return this; }
	
	public Quat setX(float x) { this.x = x; return this; }
	
	public Quat setY(float y) { this.y = y; return this; }
	
	public Quat setZ(float z) { this.z = z; return this; }

	public Quat conjugate()
	{
		return conjugate(this);
	}
	
	public Quat conjugate(@Nullable Quat res)
	{
		if(res == null) res = new Quat();

		res.set(this.w, -this.x, -this.y, -this.z);

		return res;
	}
	
	public Quat inverse()
	{
		return inverse(this);
	}
	
	public Quat inverse(@Nullable Quat res)
	{
		if(res == null) res = new Quat();

		float l = reciprocalLength();
		
		res.set(this.w * l, -this.x * l, -this.y * l, -this.z * l);
		
		return res;
	}
	
	public Quat mul(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		mul(q, this);
		
		return this;
	}
	
	public Quat mul(Quat q, @Nullable Quat res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		if(res == null) res = new Quat();
		
		float w_ = this.w * q.getW() - this.x * q.getX() - this.y * q.getY() - this.z * q.getZ(); // w * w' - v * v'
		float x_ = this.w * q.getX() + q.getW() * this.x + this.y * q.getZ() - this.z * q.getY(); // s * v'.x + s' * v.x + (V x V').x
		float y_ = this.w * q.getY() + q.getW() * this.y + this.z * q.getX() - this.x * q.getZ(); // s * v'.y + s' * v.y + (V x V').y
		float z_ = this.w * q.getZ() + q.getW() * this.z + this.x * q.getY() - this.y * q.getX(); // s * v'.z + s' * v.z + (V x V').z

		res.set(w_, x_, y_, z_).normal();

		return res;
	}
	
	public Quat mul(Tup3fR v)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		mul(v, this);
		
		return this;
	}
	
	public Quat mul(Tup3fR v, @Nullable Quat res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Quat();
		
		float w_ = -this.x * v.getX() - this.y * v.getY() - this.z * v.getZ(); // - v * v'
		float x_ =  this.w * v.getX() + this.y * v.getZ() - this.z * v.getY(); // s * v'.x ...
		float y_ =  this.w * v.getY() + this.z * v.getX() - this.x * v.getZ(); // s * v'.y ...
		float z_ =  this.w * v.getZ() + this.x * v.getY() - this.y * v.getX(); // s * v*.z ...

		res.set(w_, x_, y_, z_).normal();

		return res;
	}
	
	public Vec3 transform(Tup3fR v, @Nullable Vec3 res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Vec3();
		
		Quat r = mul(v, QuatPool.get());
		Quat c = conjugate(QuatPool.get());
		
		r.mul(c, r);
		
		res.set(r.getX(),r.getY(), r.getZ());
		
		QuatPool.store(r, c);
		
		return res;
	}
	
	public float length() { return (float)Maths.sqrt(squaredLength()); }
	public float squaredLength() { return this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z; }
	public float reciprocalLength() { return 1.0f / length(); }
	
	public Quat normal()
	{
		return normal(this);
	}
	
	public Quat normal(@Nullable Quat res)
	{
		if(res == null) res = new Quat();
		
		float l = reciprocalLength();
		
		res.set(this.w * l, this.x * l, this.y * l, this.z * l);

		return res;
	}

	public float dot(Quat q)
	{
		return this.w * q.w + this.x * q.x + this.y * q.y + this.z * q.z;
	}

	public String toString()
	{
		return "quat(" + this.w + ", " + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public Quat clone()
	{
		return new Quat(this);
	}
}
