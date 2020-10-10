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

package org.barghos.math.matrix;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple3.pool.Tup3fPool;
import org.barghos.core.util.Nullable;

import org.barghos.math.BarghosMath;
import org.barghos.math.EulerAngles2f;
import org.barghos.math.ITransform2f;
import org.barghos.math.Maths;
import org.barghos.math.point.Point2f;
import org.barghos.math.vector.quat.Quat;
import org.barghos.math.vector.vec2.Vec2f;

public class Mat3f extends SimpleMat3f
{
	public Mat3f() { }
	
	public Mat3f(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}

	public Mat3f set(Mat3f m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		super.set(m); return this;
	}
	
	public Mat3f initIdentity()
	{
		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 1.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		return this;
	}

	public Mat3f initZero()
	{
		setRow(0, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 0.0f);

		return this;
	}
	
	public Mat3f initScaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat3f initScaling3D(float x, float y, float z)
	{
		setRow(0, x,	0.0f,	0.0f);
		setRow(1, 0.0f,	y,		0.0f);
		setRow(2, 0.0f,	0.0f,	z);
		
		return this;
	}
	
	public Mat3f initScaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling2D(t.getX(), t.getY());
	}
	
	public Mat3f initScaling2D(float x, float y)
	{
		setRow(0, x,	0.0f,	0.0f);
		setRow(1, 0.0f,	y,		0.0f);
		setRow(2, 0.0f,	0.0f,	1.0f);
		
		return this;
	}
	
	public Mat3f initTranslation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation2D(t.getX(), t.getY());
	}
	
	public Mat3f initTranslation2D(float x, float y)
	{
		setRow(0, 1.0f, 0.0f, x);
		setRow(1, 0.0f, 1.0f, y);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRotation3D(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
			
		this.m[0][0] = 1.0f - 2.0f	*	(q.getY() * q.getY() + q.getZ() * q.getZ());
		this.m[0][1] = 2.0f			*	(q.getX() * q.getY() - q.getW() * q.getZ());
		this.m[0][2] = 2.0f			*	(q.getX() * q.getZ() + q.getW() * q.getY());
		
		this.m[1][0] = 2.0f			*	(q.getX() * q.getY() + q.getW() * q.getZ());
		this.m[1][1] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getZ() * q.getZ());
		this.m[1][2] = 2.0f			*	(q.getY() * q.getZ() - q.getW() * q.getX());
		
		this.m[2][0] = 2.0f			*	(q.getX() * q.getZ() - q.getW() * q.getY());
		this.m[2][1] = 2.0f			*	(q.getY() * q.getZ() + q.getW() * q.getX());
		this.m[2][2] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getY() * q.getY());;

		return this;
	}
	
	public Mat3f initRotation3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(left == null) throw new ArgumentNullException("left");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		setRow(0, left);
		setRow(1, up);
		setRow(2, forward);
		
		return this;
	}
	
	public Mat3f initRotation2D(EulerAngles2f angles)
	{
		float rad = angles.getAngle() * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRotation2D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initTranform2D(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		initIdentity();
		
		rotate2D(t.getOrientation());
		translate2D(t.getPosition());
		scale2D(t.getScale());

		return this;
	}
	

	
	public static Mat3f mul(Mat3f l, Mat3f r, @Nullable Mat3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		if(res == null) res = new Mat3f();
		
		l.mul(r, res);
		
		return res;
	}
	
	public Mat3f transpose()
	{
		Tup3f r0 = getRow(0, Tup3fPool.get());
		Tup3f r1 = getRow(1, Tup3fPool.get());
		Tup3f r2 = getRow(2, Tup3fPool.get());
		
		setColumn(0, r0).setColumn(1, r1).setColumn(2, r2);
		
		Tup3fPool.store(r0, r1, r2);
		
		return this;
	}
	
	public static Point2f transform(Mat3f l, Point2f r, Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * 1;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * 1;

		res.set(x_, y_);

		return res;
	}
	
	public static Vec2f transform(Mat3f l, Vec2f r, Vec2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY();

		res.set(x_, y_);

		return res;
	}
	
	public static <T extends Tup3fW> T transform(Mat3f l, Tup3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ();
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ();

		res.set(x_, y_, z_);

		return res;
	}
	
	public static <T extends Tup3fR & Tup3fW> T transform(Mat3f l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat3f.transform(l, r, r);
	}
	

	
	public Point2f transform(Point2f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat3f.transform(this, r, r);
	}
	
	public Point2f transform(Point2f r, Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat3f.transform(this, r, res);
	}
	
	public Vec2f transform(Vec2f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat3f.transform(this, r, r);
	}
	
	public Vec2f transform(Vec2f r, Vec2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat3f.transform(this, r, res);
	}
	
		public static Mat3f identity()
	{
		return new Mat3f().initIdentity();
	}
	
	public static Mat3f zero()
	{
		return new Mat3f().initZero();
	}
	
	public static Mat3f scaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public static Mat3f scaling3D(float x, float y, float z)
	{
		return new Mat3f().initScaling3D(x, y,z);
	}
	
	public static Mat3f scaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling2D(t.getX(), t.getY());
	}
	
	public static Mat3f scaling2D(float x, float y)
	{
		return new Mat3f().initScaling2D(x, y);
	}
	
	public static Mat3f rotation3D(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return new Mat3f().initRotation3D(q);
	}
	
	public static Mat3f rotation3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(left == null) throw new ArgumentNullException("left");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat3f().initRotation3D(forward, left, up);
	}
	
	public static Mat3f rotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat3f().initRotation2D(angles);
	}
	
	public static Mat3f rotation2D(float angle)
	{
		return new Mat3f().initRotation2D(angle);
	}
	
	public static Mat3f translation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat3f().initTranslation2D(t);
	}
	
	public static Mat3f translation2D(float x, float y)
	{
		return new Mat3f().initTranslation2D(x, y);
	}
	
	public static Mat3f transform2D(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat3f().initTranform2D(t);
	}
	
	public Mat3f scale3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat3f.mul(Mat3f.scaling3D(t), this, this);
	}
	
	public Mat3f scale3D(float x, float y, float z)
	{
		return Mat3f.mul(Mat3f.scaling3D(x, y, z), this, this);
	}
	
	public Mat3f scale2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat3f.mul(Mat3f.scaling2D(t), this, this);
	}
	
	public Mat3f scale2D(float x, float y)
	{
		return Mat3f.mul(Mat3f.scaling2D(x, y), this, this);
	}
	
	public Mat3f rotate3D(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return Mat3f.mul(Mat3f.rotation3D(q), this, this);
	}

	public Mat3f rotate3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(left == null) throw new ArgumentNullException("left");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return Mat3f.mul(Mat3f.rotation3D(forward, left, up), this, this);
	}
	
	public Mat3f rotate2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return Mat3f.mul(Mat3f.rotation2D(angles), this, this);
	}
	
	public Mat3f rotate2D(float angle)
	{
		return Mat3f.mul(Mat3f.rotation2D(angle), this, this);
	}
	
	public Mat3f translate2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat3f.mul(Mat3f.translation2D(t), this, this);
	}
	
	public Mat3f translate2D(float x, float y)
	{
		return Mat3f.mul(Mat3f.translation2D(x, y), this, this);
	}
	
	public Mat3f clone()
	{
		return new Mat3f(this);
	}
}
