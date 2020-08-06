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
import org.barghos.core.tuple3.api.Tup3dR;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;

/** A 3-Dimensional Quaternion */
public class Quatd
{
	/** The w component. */
	protected double w;
	/** The x component. */
	protected double x;
	/** The y component. */
	protected double y;
	/** The z component. */
	protected double z;

	/**
	 * The default constructor. It sets x,y and z to 0 and w to 1.
	 * It is commonly used in pools.
	 */
	public Quatd()
	{
		set(1.0, 0.0, 0.0, 0.0);
	}
	
	/**
	 * This constructor sets the components to x, y, z and w.
	 * @param w The new w component.
	 * @param x The new x component.
	 * @param y The new y component.
	 * @param z The new z component.
	 */
	public Quatd(double w, double x, double y, double z)
	{
		set(w, x, y, z);
	}
	
	/**
	 * This contructor sets the components to the values of q.
	 * @param q A Quaternion that the components are set to.
	 */
	public Quatd(Quatd q)
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
	public Quatd(Mat4 rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		set(rot);
	}

	public static Quatd getFromAxis(Tup3dR axis, double angle) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, null); }
	
	public static Quatd getFromAxis(double ax, double ay, double az, double angle) { return getFromAxis(ax, ay, az, angle, null); }
	
	public static Quatd getFromAxis(Tup3dR axis, double angle, Quatd res) { return getFromAxis(axis.getX(), axis.getY(), axis.getZ(), angle, res); }
	
	public static Quatd getFromAxis(double ax, double ay, double az, double angle, Quatd res)
	{
		if(res == null) res = new Quatd();
		
		double halfAngle = angle * 0.5 * Maths.DEG_TO_RAD;
		double sinHalfAngle = Maths.sin(halfAngle);
		double cosHalfAngle = Maths.cos(halfAngle);
		
		double rX = ax * sinHalfAngle;
		double rY = ay * sinHalfAngle;
		double rZ = az * sinHalfAngle;
		double rW = cosHalfAngle;
		
		return res.set(rW, rX, rY, rZ).normal();
	}
	
	public static Quatd getFromVectors(Vec3 v1, Vec3 v2)
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
		
		double angle = 1.0 + a.dot(b);

		Quatd out = new Quatd(angle, axis.getX(), axis.getY(), axis.getZ()).normal();
		
		Vec3Pool.store(a, b, axis);
		
		return out;
	}
	
	public double getW() { return this.w; }
	
	public double getX() { return this.x; }
	
	public double getY() { return this.y; }
	
	public double getZ() { return this.z; }
	
	public Quatd rotate(Tup3dR axis, double angle)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(axis == null) throw new ArgumentNullException("axis");
		}
		
		return rotate(angle, axis.getX(), axis.getY(), axis.getZ());
	}
	
	public Quatd rotate(double ax, double ay, double az, double angle)
	{
		return getFromAxis(angle, ax, ay, az).mul(this, this);
	}
	
	public Quatd rotate(Quatd q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return q.mul(this, this);
	}
	
	public Quatd set(Quatd q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return set(q.getW(), q.getX(), q.getY(), q.getZ());
	}
	
	//From Ken Shoemake's "Quaternion Calculus and Fast Animation" article
	public Quatd set(Mat4 rot) 
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		double trace = rot.m[0][0] + rot.m[1][1] + rot.m[2][2];

		if(trace > 0)
		{
			double s = 0.5 / Maths.sqrt(trace + 1.0);
			this.w = 0.25 / s;
			
			
			this.x = (rot.m[1][2] - rot.m[2][1]) * s;
			this.y = (rot.m[2][0] - rot.m[0][2]) * s;
			this.z = (rot.m[0][1] - rot.m[1][0]) * s;
		}
		else
		{
			if(rot.m[0][0] > rot.m[1][1] && rot.m[0][0] > rot.m[2][2])
			{
				double s = 2.0 * Maths.sqrt(1.0 + rot.m[0][0] - rot.m[1][1] - rot.m[2][2]);
				this.w = (rot.m[1][2] - rot.m[2][1]) / s;
				this.x = 0.25 * s;
				this.y = (rot.m[1][0] + rot.m[0][1]) / s;
				this.z = (rot.m[2][0] + rot.m[0][2]) / s;
			}
			else if(rot.m[1][1] > rot.m[2][2])
			{
				double s = 2.0 * Maths.sqrt(1.0 + rot.m[1][1] - rot.m[0][0] - rot.m[2][2]);
				this.w = (rot.m[2][0] - rot.m[0][2]) / s;
				this.x = (rot.m[1][0] + rot.m[0][1]) / s;
				this.y = 0.25 * s;
				this.z = (rot.m[2][1] + rot.m[1][2]) / s;
			}
			else
			{
				double s = 2.0 * Maths.sqrt(1.0 + rot.m[2][2] - rot.m[0][0] - rot.m[1][1]);
				this.w = (rot.m[0][1] - rot.m[1][0] ) / s;
				this.x = (rot.m[2][0] + rot.m[0][2] ) / s;
				this.y = (rot.m[1][2] + rot.m[2][1] ) / s;
				this.z = 0.25 * s;
			}
		}

		normal(this);
		
		return this;
	}
	
	public Quatd set(double w, double x, double y, double z) { return setW(w).setX(x).setY(y).setZ(z); }
	
	public Quatd setW(double w) { this.w = w; return this; }
	
	public Quatd setX(double x) { this.x = x; return this; }
	
	public Quatd setY(double y) { this.y = y; return this; }
	
	public Quatd setZ(double z) { this.z = z; return this; }

	public Quatd conjugate()
	{
		return conjugate(this);
	}
	
	public Quatd conjugate(@Nullable Quatd res)
	{
		if(res == null) res = new Quatd();

		res.set(this.w, -this.x, -this.y, -this.z);

		return res;
	}
	
	public Quatd inverse()
	{
		return inverse(this);
	}
	
	public Quatd inverse(@Nullable Quatd res)
	{
		if(res == null) res = new Quatd();

		double l = reciprocalLength();
		
		res.set(this.w * l, -this.x * l, -this.y * l, -this.z * l);
		
		return res;
	}
	
	public Quatd mul(Quatd q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		mul(q, this);
		
		return this;
	}
	
	public Quatd mul(Quatd q, @Nullable Quatd res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		if(res == null) res = new Quatd();
		
		double w_ = this.w * q.getW() - this.x * q.getX() - this.y * q.getY() - this.z * q.getZ(); // w * w' - v * v'
		double x_ = this.w * q.getX() + q.getW() * this.x + this.y * q.getZ() - this.z * q.getY(); // s * v'.x + s' * v.x + (V x V').x
		double y_ = this.w * q.getY() + q.getW() * this.y + this.z * q.getX() - this.x * q.getZ(); // s * v'.y + s' * v.y + (V x V').y
		double z_ = this.w * q.getZ() + q.getW() * this.z + this.x * q.getY() - this.y * q.getX(); // s * v'.z + s' * v.z + (V x V').z

		res.set(w_, x_, y_, z_).normal();

		return res;
	}
	
	public Quatd mul(Tup3dR v)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		mul(v, this);
		
		return this;
	}
	
	public Quatd mul(Tup3dR v, @Nullable Quatd res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Quatd();
		
		double w_ = -this.x * v.getX() - this.y * v.getY() - this.z * v.getZ(); // - v * v'
		double x_ =  this.w * v.getX() + this.y * v.getZ() - this.z * v.getY(); // s * v'.x ...
		double y_ =  this.w * v.getY() + this.z * v.getX() - this.x * v.getZ(); // s * v'.y ...
		double z_ =  this.w * v.getZ() + this.x * v.getY() - this.y * v.getX(); // s * v*.z ...

		res.set(w_, x_, y_, z_).normal();

		return res;
	}
	
	public Vec3 transform(Tup3dR v, @Nullable Vec3 res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		if(res == null) res = new Vec3();
		
		Quatd r = mul(v, QuatdPool.get());
		Quatd c = conjugate(QuatdPool.get());
		
		r.mul(c, r);
		
		res.set((float)r.getX(), (float)r.getY(), (float)r.getZ());
		
		QuatdPool.store(r, c);
		
		return res;
	}
	
	public double length() { return Maths.sqrt(squaredLength()); }
	public double squaredLength() { return this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z; }
	public double reciprocalLength() { return 1.0 / length(); }
	
	public Quatd normal()
	{
		return normal(this);
	}
	
	public Quatd normal(@Nullable Quatd res)
	{
		if(res == null) res = new Quatd();
		
		double l = reciprocalLength();
		
		res.set(this.w * l, this.x * l, this.y * l, this.z * l);

		return res;
	}

	public double dot(Quatd q)
	{
		return this.w * q.w + this.x * q.x + this.y * q.y + this.z * q.z;
	}

	public String toString()
	{
		return "quatd(" + this.w + ", " + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public Quatd clone()
	{
		return new Quatd(this);
	}
}
