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

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.util.Nullable;

import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.matrix.api.Mat4fR;
import org.barghos.math.utils.api.EulerRotationOrder3;
import org.barghos.math.utils.api.HirarchicalTransform3f;
import org.barghos.math.utils.api.Transform3f;

import org.barghos.math.BarghosMath;
import org.barghos.math.point.Point3f;
import org.barghos.math.quat.Quatf;
import org.barghos.math.utils.EulerAngles2f;
import org.barghos.math.utils.EulerAngles3f;
import org.barghos.math.utils.EulerAnglesRad2f;
import org.barghos.math.utils.EulerAnglesRad3f;
import org.barghos.math.utils.LinearSystem3;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.api.Vec3fR;
import org.barghos.math.vec3.pool.Vec3fPool;

public class Mat4f extends SimpleMat4f
{	
	public Mat4f() { }
	
	public Mat4f(Mat4f m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Mat4f set(Mat4fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		super.set(m);

		return this;
	}
	
	public Mat4f set(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}

		super.set(m);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t, z, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, t, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, y, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		super.setRow(index, x, y, z, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t, z, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, t, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, y, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		super.setColumn(index, x, y, z, w);
		
		return this;
	}
	
	public Mat4f initIdentity()
	{
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 1.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}

	public Mat4f initZero()
	{
		setRow(0, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 0.0f);
		
		return this;
	}
	
	public Mat4f initScaling4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling4D(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4f initScaling4D(float x, float y, float z, float w)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	z, 		0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	w);
		
		return this;
	}
	
	public Mat4f initScaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4f initScaling3D(float x, float y, float z)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	z, 		0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	1.0f);
		
		return this;
	}
	
	public Mat4f initScaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling2D(t.getX(), t.getY());
	}
	
	public Mat4f initScaling2D(float x, float y)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	1.0f, 	0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	1.0f);
		
		return this;
	}
	
	public Mat4f initTranslation3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation3D(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4f initTranslation3D(float x, float y, float z)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, x	);
		setRow(1, 0.0f, 1.0f, 0.0f, y	);
		setRow(2, 0.0f, 0.0f, 1.0f, z	);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initTranslation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation2D(t.getX(), t.getY());
	}
	
	public Mat4f initTranslation2D(float x, float y)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, x	);
		setRow(1, 0.0f, 1.0f, 0.0f, y	);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		float rad = angles.getAngle() * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		setRow(0, Maths.cos(angles.getAngle()), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angles.getAngle()), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angle), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation3D(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		this.m[0][0] = 1.0f - 2.0f	*	(q.getY() * q.getY() + q.getZ() * q.getZ());
		this.m[0][1] = 2.0f			*	(q.getX() * q.getY() - q.getW() * q.getZ());
		this.m[0][2] = 2.0f			*	(q.getX() * q.getZ() + q.getW() * q.getY());
		this.m[0][3] = 0.0f;
		
		this.m[1][0] = 2.0f			*	(q.getX() * q.getY() + q.getW() * q.getZ());
		this.m[1][1] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getZ() * q.getZ());
		this.m[1][2] = 2.0f			*	(q.getY() * q.getZ() - q.getW() * q.getX());
		this.m[1][3] = 0.0f;
		
		this.m[2][0] = 2.0f			*	(q.getX() * q.getZ() - q.getW() * q.getY());
		this.m[2][1] = 2.0f			*	(q.getY() * q.getZ() + q.getW() * q.getX());
		this.m[2][2] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getY() * q.getY());
		this.m[2][3] = 0.0f;
		
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);

		return this;
	}
	
	public Mat4f initPitchRotation3D(float angle)
	{
		return initPitchRotation3DRad(angle * Maths.DEG_TO_RADf);
	}

	public Mat4f initPitchRotation3DRad(float angle)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(angle), Maths.sin(angle), 0.0f);
		setRow(2, 0.0f, -Maths.sin(angle), Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initPitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}

		return initPitchRotation3DRad(angle * Maths.DEG_TO_RADf, system);
	}

	public Mat4f initPitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initPitchRotation3DRad(angle);
		
		Mat4f.rotation3D(system).mul(this, this);
		
		return this;
	}
	
	public Mat4f initPitchRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}

		return initPitchRotation3DRad(angle * Maths.DEG_TO_RADf, forward, right, up);
	}

	public Mat4f initPitchRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		initPitchRotation3DRad(angle);
		
		Mat4f.rotation3D(forward, right, up).mul(this, this);
		
		return this;
	}
	
	public Mat4f initYawRotation3D(float angle)
	{
		return initYawRotation3DRad(angle * Maths.DEG_TO_RADf);
	}
	
	public Mat4f initYawRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, -Maths.sin(angle), 0.0f);
		setRow(1, 0.0f, 1, 0.0f, 0.0f);
		setRow(2, Maths.sin(angle), 0.0f, Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}

	public Mat4f initYawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}

		return initYawRotation3DRad(angle * Maths.DEG_TO_RADf, system);
	}
	
	public Mat4f initYawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initYawRotation3DRad(angle);

		Mat4f.rotation3D(system).mul(this, this);
		
		return this;
	}
	
	public Mat4f initYawRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}

		return initYawRotation3DRad(angle * Maths.DEG_TO_RADf, forward, right, up);
	}
	
	public Mat4f initYawRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		initYawRotation3DRad(angle);

		Mat4f.rotation3D(forward, right, up).mul(this, this);
		
		return this;
	}
	
	public Mat4f initRollRotation3D(float angle)
	{
		return initRollRotation3DRad(angle * Maths.DEG_TO_RADf);
	}
	
	public Mat4f initRollRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), Maths.sin(angle), 0.0f, 0.0f);
		setRow(1, -Maths.sin(angle), Maths.cos(angle), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}

		return initRollRotation3DRad(angle * Maths.DEG_TO_RADf, system);
	}
	
	public Mat4f initRollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initRollRotation3DRad(angle);

		Mat4f.rotation3D(system).mul(this, this);
		
		return this;
	}
	
	public Mat4f initRollRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}

		return initRollRotation3DRad(angle * Maths.DEG_TO_RADf, forward, right, up);
	}
	
	public Mat4f initRollRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		initRollRotation3DRad(angle);

		Mat4f.rotation3D(forward, right, up).mul(this, this);
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return initRotation3D(angles.getPitch(), angles.getYaw(), angles.getRoll());
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll)
	{
		return initRotation3D(pitch, yaw, roll, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return initRotation3DRad(angles.getPitch(), angles.getYaw(), angles.getRoll());
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll)
	{
		return initRotation3DRad(pitch, yaw, roll, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return initRotation3D(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return initRotation3D(pitch, yaw, roll, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			
		}
		
		return initRotation3D(angles, forward, right, up, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return initRotation3D(pitch, yaw, roll, forward, right, up, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return initRotation3DRad(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return initRotation3DRad(pitch, yaw, roll, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return initRotation3DRad(angles, forward, right, up, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return initRotation3DRad(pitch, yaw, roll, forward, right, up, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3D(angles.getPitch(), angles.getYaw(), angles.getRoll(), order);
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initPitchRotation3D(pitch);
				applyYawRotation3D(yaw);
				applyRollRotation3D(roll);
				
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initPitchRotation3D(pitch);
				applyRollRotation3D(roll);
				applyYawRotation3D(yaw);
				
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initYawRotation3D(yaw);
				applyPitchRotation3D(pitch);
				applyRollRotation3D(roll);

				break;
			}
			case YAW_ROLL_PITCH:
			{
				initYawRotation3D(yaw);
				applyRollRotation3D(roll);
				applyPitchRotation3D(pitch);

				break;
			}
			case ROLL_PITCH_YAW:
			{
				initRollRotation3D(roll);
				applyPitchRotation3D(pitch);
				applyYawRotation3D(yaw);
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initRollRotation3D(roll);
				applyYawRotation3D(yaw);
				applyPitchRotation3D(pitch);

				break;
			}
		}
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3DRad(angles.getPitch(), angles.getYaw(), angles.getRoll(), order);
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initPitchRotation3DRad(pitch);
				applyYawRotation3DRad(yaw);
				applyRollRotation3DRad(roll);
				
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initPitchRotation3DRad(pitch);
				applyRollRotation3DRad(roll);
				applyYawRotation3DRad(yaw);

				break;
			}
			case YAW_PITCH_ROLL:
			{
				initYawRotation3DRad(yaw);
				applyPitchRotation3DRad(pitch);
				applyRollRotation3DRad(roll);

				break;
			}
			case YAW_ROLL_PITCH:
			{
				initYawRotation3DRad(yaw);
				applyRollRotation3DRad(roll);
				applyPitchRotation3DRad(pitch);

				break;
			}
			case ROLL_PITCH_YAW:
			{
				initRollRotation3DRad(roll);
				applyPitchRotation3DRad(pitch);
				applyYawRotation3DRad(yaw);
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initRollRotation3DRad(roll);
				applyYawRotation3DRad(yaw);
				applyPitchRotation3DRad(pitch);

				break;
			}
		}
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3D(angles.getPitch(), angles.getYaw(), angles.getRoll(), system, order);
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		initRotation3D(pitch, yaw, roll, order);
		
		Mat4f.rotation3D(system).mul(this, this);
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3D(angles.getPitch(), angles.getYaw(), angles.getRoll(), forward, right, up, order);
	}
	
	public Mat4f initRotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		initRotation3D(pitch, yaw, roll, order);
		
		Mat4f.rotation3D(forward, right, up).mul(this, this);
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3DRad(angles.getPitch(), angles.getYaw(), angles.getRoll(), system, order);
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		initRotation3DRad(pitch, yaw, roll, order);
		
		mul(Mat4f.rotation3D(system));
	
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return initRotation3DRad(angles.getPitch(), angles.getYaw(), angles.getRoll(), forward, right, up, order);
	}
	
	public Mat4f initRotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		initRotation3DRad(pitch, yaw, roll, order);
		
		mul(Mat4f.rotation3D(forward, right, up));
	
		return this;
	}
	
	public Mat4f initRotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, system.getRight(), 0.0f);
		setRow(1, system.getUp(), 0.0f);
		setRow(2, system.getForward(), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation3D(Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		setColumn(0, right, 0.0f);
		setColumn(1, up, 0.0f);
		setColumn(2, forward, 0.0f);
		setColumn(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initTransformMatrix3D(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		initScaling3D(t.getScale());
		applyRotation3D(t.getOrientation());	
		applyTranslation3D(t.getPosition());
		
		return this;
	}
	
	public Mat4f initTransformMatrix3D(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		initScaling3D(t.getRelativeScale());
		applyRotation3D(t.getRelativeOrientation());	
		applyTranslation3D(t.getRelativePosition());
		
		return this;
	}
	
	public Mat4f initTransformMatrix3D(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		if(scale == null) scale = new Tup3f(1.0f);

		initScaling3D(scale);
		applyRotation3D(rot);
		applyTranslation3D(pos);

		return this;
	}
	
	public Mat4f initTransformMatrix3D(Tup3fR pos, EulerAngles3f angles, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		if(scale == null) scale = new Tup3f(1.0f);

		initScaling3D(scale);
		applyRotation3D(angles);
		applyTranslation3D(pos);

		return this;
	}
	
	public Mat4f initPerspective(Tup2fR t, float fov, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		
		return initPerspective(t.getX(), t.getY(), fov, near, far);
	}
	
	public Mat4f initPerspective(float width, float height, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = y_scale / (width / height);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0f,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4f initPerspective(float fovX, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = 1.0f / (float)Math.tan(fovX * 0.5 * Maths.DEG_TO_RAD);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4f initPerspective(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2f * near / (right - left),		0f,									0f,									0f	);
		setRow(1, 0f,								2f * near / (top - bottom),			0f,									0f	);
		setRow(2, (right + left) / (right - left),	(top + bottom) / (top - bottom),	-(far + near) / (far - near),		-1f	);
		setRow(3, 0f,								0f,									-2f * far * near / (far - near),	0f	);
		
		return this;
	}
	
	public Mat4f initOrtho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initOrtho(t.getX(), t.getY(), near, far);
	}
	
	public Mat4f initOrtho(float width, float height, float length)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / length,			0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4f initOrtho(float width, float height, float near, float far)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / (far - near),	0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4f initOrtho(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								0f);
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								0f);
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			0f);
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}

	public Mat4f initViewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		applyRotation3D(rot.conjugate(new Quatf()));
		
		return this;
	}
	
	public Mat4f initViewMatrix(float posX, float posY, float posZ, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		initTranslation3D(-posX, -posY, -posZ);
		applyRotation3D(rot.conjugate(new Quatf()));
		
		return this;
	}
	
	public Mat4f initViewMatrix(Tup3fR pos, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		
		Quatf q = new Quatf(Mat4f.rotation3D(orientation));
		
		applyRotation3D(q.conjugate());
		
		return this;
	}
	
	public Mat4f initViewMatrix(float posX, float posY, float posZ, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		initTranslation3D(-posX, -posZ, -posZ);
		
		EulerAngles3f angles = new EulerAngles3f(Mat4f.rotation3D(orientation));
		angles.invert();
		
		applyRotation3D(angles);
		
		return this;
	}
	
	public Mat4f initViewMatrix(Tup3fR pos, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		
		EulerAngles3f angles = new EulerAngles3f(Mat4f.rotation3D(forward, right, up));
		angles.invert();
		
		applyRotation3D(angles);
		
		return this;
	}
	
	public Mat4f initViewMatrix(float posX, float posY, float posZ, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		initTranslation3D(-posX, -posZ, -posZ);
		
		EulerAngles3f angles = new EulerAngles3f(Mat4f.rotation3D(forward, right, up));
		angles.invert();
		
		applyRotation3D(angles);
		
		return this;
	}
	
	public Mat4f initViewMatrix(Tup3fR pos, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		applyRotation3D(angles.invertN());
		
		return this;
	}
	
	public Mat4f initViewMatrix(float posX, float posY, float posZ, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initTranslation3D(-posX, -posY, -posZ);
		applyRotation3D(angles.invertN());
		
		return this;
	}
	
	public Mat4f initViewMatrixRad(Tup3fR pos, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		applyRotation3DRad(angles.invertN());
		
		return this;
	}
	
	public Mat4f initViewMatrixRad(float posX, float posY, float posZ, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initTranslation3D(-posX, -posY, -posZ);
		applyRotation3DRad(angles.invertN());
		
		return this;
	}
	
	public Mat4f initViewMatrix(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		applyRotation3D(-pitch, -yaw, -roll);
		
		return this;
	}
	
	public Mat4f initViewMatrix(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		initTranslation3D(-posX, -posY, -posZ);
		applyRotation3D(-pitch, -yaw, -roll);
		
		return this;
	}
	
	public Mat4f initViewMatrixRad(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		initTranslation3D(-pos.getX(), -pos.getY(), -pos.getZ());
		applyRotation3DRad(-pitch, -yaw, -roll);
		
		return this;
	}
	
	public Mat4f initViewMatrixRad(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		initTranslation3D(-posX, -posY, -posZ);
		applyRotation3DRad(-pitch, -yaw, -roll);
		
		return this;
	}
	
	public Mat4f initLookAtMatrix(Tup3fR pos, Tup3fR target, Tup3fR worldUp)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(target == null) throw new ArgumentNullException("target");
			if(worldUp == null) throw new ArgumentNullException("worldUp");
		}
	
		Vec3f forward = Vec3fPool.get(target).sub(pos).normalSafe();
		Vec3f right = Vec3fPool.get(worldUp).cross(forward).normalSafe();
		Vec3f up = forward.cross(right).normalSafe();
		
		initRotation3D(forward, right, up);

		Vec3fPool.store(forward, right);
		
		return this;
	}

	public Mat4f mul(Mat4fR left)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(left == null) throw new ArgumentNullException("left");
		}
		
		mul(left, this);
		
		return this;
	}
	
	public Mat4f mulN(Mat4fR left)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(left == null) throw new ArgumentNullException("left");
		}
		
		return mul(left, new Mat4f());
	}
	
	public Mat4f transpose()
	{
		return super.transpose(this);
	}
	
	public Point3f transform(Point3f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}

		return Mat4f.transform(this, r, r);
	}
	
	public <T extends Tup3fW> T transform(Point3f r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}

		return Mat4f.transform(this, r, res);
	}
	
	public Vec3f transform(Vec3f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(this, r, r);
	}
	
	public <T extends Tup3fW> T transform(Vec3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return Mat4f.transform(this, r, res);
	}
	
	public <T extends Tup2fW> T transform(Tup2fR r, T res)
	{
		return Mat4f.transform(this, r, res);
	}

	public Mat4f applyScaling4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling4D(t));
	}
	
	public Mat4f applyScaling4D(float x, float y, float z, float w)
	{
		return mul(Mat4f.scaling4D(x, y, z, w));
	}
	
	public Mat4f applyScaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling3D(t));
	}
	
	public Mat4f applyScaling3D(float x, float y, float z)
	{
		return mul(Mat4f.scaling3D(x, y, z));
	}
	
	public Mat4f applyScaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling2D(t));
	}
	
	public Mat4f applyScaling2D(float x, float y)
	{
		return mul(Mat4f.scaling2D(x, y));
	}
	
	public Mat4f applyTranslation3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.translation3D(t));
	}
	public Mat4f applyTranslation3D(float x, float y, float z)
	{
		return mul(Mat4f.translation3D(x, y, z));
	}
	
	public Mat4f applyTranslation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.translation2D(t));
	}
	public Mat4f applyTranslation2D(float x, float y)
	{
		return mul(Mat4f.translation2D(x, y));
	}
	
	public Mat4f applyRotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation2D(angles));
	}
	
	public Mat4f applyRotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation2DRad(angles));
	}
	
	public Mat4f applyRotation2D(float angle)
	{
		return mul(Mat4f.rotation2D(angle));
	}
	
	public Mat4f applyRotation2DRad(float angle)
	{
		return mul(Mat4f.rotation2DRad(angle));
	}
	
	public Mat4f applyRotation3D(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return mul(Mat4f.rotation3D(q));
	}
	
	public Mat4f applyPitchRotation3D(float angle)
	{
		return mul(Mat4f.pitchRotation3D(angle));
	}
	
	public Mat4f applyPitchRotation3DRad(float angle)
	{
		return mul(Mat4f.pitchRotation3DRad(angle));
	}
	
	public Mat4f applyPitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.pitchRotation3D(angle, system));
	}
	
	public Mat4f applyPitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.pitchRotation3DRad(angle, system));
	}
	
	public Mat4f applyPitchRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.pitchRotation3D(angle, forward, right, up));
	}
	
	public Mat4f applyPitchRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.pitchRotation3DRad(angle, forward, right, up));
	}
	
	public Mat4f applyYawRotation3D(float angle)
	{
		return mul(Mat4f.yawRotation3D(angle));
	}
	
	public Mat4f applyYawRotation3DRad(float angle)
	{
		return mul(Mat4f.yawRotation3DRad(angle));
	}
	
	public Mat4f applyYawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.yawRotation3D(angle, system));
	}
	
	public Mat4f applyYawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.yawRotation3DRad(angle, system));
	}
	
	public Mat4f applyYawRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.yawRotation3D(angle, forward, right, up));
	}
	
	public Mat4f applyYawRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.yawRotation3DRad(angle, forward, right, up));
	}
	
	public Mat4f applyRollRotation3D(float angle)
	{
		return mul(Mat4f.rollRotation3D(angle));
	}
	
	public Mat4f applyRollRotation3DRad(float angle)
	{
		return mul(Mat4f.rollRotation3DRad(angle));
	}
	
	public Mat4f applyRollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rollRotation3D(angle, system));
	}
	
	public Mat4f applyRollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rollRotation3DRad(angle, system));
	}
	
	public Mat4f applyRollRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rollRotation3D(angle, forward, right, up));
	}
	
	public Mat4f applyRollRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rollRotation3DRad(angle, forward, right, up));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation3D(angles));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll)
	{
		return mul(Mat4f.rotation3D(pitch, yaw, roll));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation3DRad(angles));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll)
	{
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3D(angles, system));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3D(pitch, yaw, roll, system));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rotation3D(angles, forward, right, up));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rotation3D(pitch, yaw, roll, forward, right, up));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3DRad(angles, system));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll, system));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rotation3DRad(angles, forward, right, up));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll, forward, right, up));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(angles, order));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(pitch, yaw, roll, order));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(angles, order));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll, order));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(angles, system, order));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(pitch, yaw, roll, system, order));
	}
	
	public Mat4f applyRotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(angles, forward, right, up, order));
	}
	
	public Mat4f applyRotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(pitch, yaw, roll, forward, right, up, order));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(angles, system, order));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll, system, order));
	}
	
	public Mat4f applyRotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(angles, forward, right, up, order));
	}
	
	public Mat4f applyRotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(pitch, yaw, roll, forward, right, up, order));
	}
	
	public Mat4f applyRotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3D(system));
	}
	
	public Mat4f applyRotation3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		return mul(Mat4f.rotation3D(forward, left, up));
	}
	
	public Mat4f applyTransformMatrix3D(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		return mul(Mat4f.transformMatrix3D(t));
	}
	
	public Mat4f applyTransformMatrix3D(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		return mul(Mat4f.transformMatrix3D(t));
	}
	
	public Mat4f applyTransformMatrix3D(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}

		return mul(Mat4f.transformMatrix3D(pos, rot, scale));
	}
	
	public Mat4f applyTransformMatrix3D(Tup3fR pos, EulerAngles3f angles, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}

		return mul(Mat4f.transformMatrix3D(pos, angles, scale));
	}
	
	public Mat4f applyPerspective(Tup2fR t, float fov, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.perspective(t, fov, near, far));
	}
	
	public Mat4f applyPerspective(float width, float height, float fovY, float near, float far)
	{
		return mul(Mat4f.perspective(width, height,  fovY, near, far));
	}
	
	public Mat4f applyPerspective(float fovX, float fovY, float near, float far)
	{
		return mul(Mat4f.perspective(fovX, fovY, near, far));
	}
	
	public Mat4f applyPerspective(float left, float right, float bottom, float top, float near, float far)
	{
		return mul(Mat4f.perspective(left, right, bottom, top, near, far));
	}
	
	public Mat4f applyOrtho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.ortho(t, near, far));
	}
	
	public Mat4f applyOrtho(float width, float height, float length)
	{
		return mul(Mat4f.ortho(width, height, length));
	}
	
	public Mat4f applyOrtho(float width, float height, float near, float far)
	{
		return mul(Mat4f.ortho(width, height, near, far));
	}
	
	public Mat4f applyOrtho(float left, float right, float bottom, float top, float near, float far)
	{
		return mul(Mat4f.ortho(left, right,  bottom, top, near, far));
	}

	public Mat4f applyViewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return mul(Mat4f.viewMatrix(pos, rot));
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}

		return mul(Mat4f.viewMatrix(posX, posY, posZ, rot));
	}
	
	public Mat4f applyViewMatrix(Tup3fR pos, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		return mul(Mat4f.viewMatrix(pos, orientation));
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		return mul(Mat4f.viewMatrix(posX, posY, posZ, orientation));
	}
	
	public Mat4f applyViewMatrix(Tup3fR pos, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.viewMatrix(pos, forward, right, up));
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat4f.viewMatrix(posX, posY, posZ, forward, right, up));
	}
	
	public Mat4f applyViewMatrix(Tup3fR pos, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.viewMatrix(pos, angles));
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}

		return Mat4f.viewMatrix(posX, posY, posZ, angles);
	}
	
	public Mat4f applyViewMatrixRad(Tup3fR pos, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.viewMatrixRad(pos, angles));
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}

		return Mat4f.viewMatrixRad(posX, posY, posZ, angles);
	}
	
	public Mat4f applyViewMatrix(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return Mat4f.viewMatrix(pos, pitch, yaw, roll);
	}
	
	public Mat4f applyViewMatrix(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		return Mat4f.viewMatrix(posX,  posY, posZ, pitch, yaw, roll);
	}
	
	public Mat4f applyViewMatrixRad(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return Mat4f.viewMatrixRad(pos, pitch, yaw, roll);
	}
	
	public Mat4f applyViewMatrixRad(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		return Mat4f.viewMatrixRad(posX,  posY, posZ, pitch, yaw, roll);
	}
	
	public Mat4f applyLookAtMatrix(Tup3fR pos, Tup3fR target, Tup3fR worldUp)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(target == null) throw new ArgumentNullException("target");
			if(worldUp == null) throw new ArgumentNullException("worldUp");
		}
		
		return mul(Mat4f.lookAtMatrix(pos, target, worldUp));
	}
	
	public Mat4f invert()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1.0 / determinant;

			// first row
			double _m0x =  MatUtils.det3x3(m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m0y = -MatUtils.det3x3(m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m0z =  MatUtils.det3x3(m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m0w = -MatUtils.det3x3(m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// second row
			double _m1x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m1y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m1z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m1w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// third row
			double _m2x =  MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[3][1], m[3][2], m[3][3]);
			double _m2y = -MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[3][0], m[3][2], m[3][3]);
			double _m2z =  MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[3][0], m[3][1], m[3][3]);
			double _m2w = -MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[3][0], m[3][1], m[3][2]);
			// fourth row
			double _m3x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3]);
			double _m3y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3]);
			double _m3z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3]);
			double _m3w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2]);

			// transpose and divide by the determinant
			m[0][0] = (float)(_m0x * determinant_inv);
			m[1][1] = (float)(_m1y * determinant_inv);
			m[2][2] = (float)(_m2z * determinant_inv);
			m[3][3] = (float)(_m3w * determinant_inv);
			m[0][1] = (float)(_m1x * determinant_inv);
			m[1][0] = (float)(_m0y * determinant_inv);
			m[2][0] = (float)(_m0z * determinant_inv);
			m[0][2] = (float)(_m2x * determinant_inv);
			m[1][2] = (float)(_m2y * determinant_inv);
			m[2][1] = (float)(_m1z * determinant_inv);
			m[0][3] = (float)(_m3x * determinant_inv);
			m[3][0] = (float)(_m0w * determinant_inv);
			m[1][3] = (float)(_m3y * determinant_inv);
			m[3][1] = (float)(_m1w * determinant_inv);
			m[3][2] = (float)(_m2w * determinant_inv);
			m[2][3] = (float)(_m3z * determinant_inv);
			
			return this;
		}
		
		return null;
	}
	
	public Mat4f invertN()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1.0 / determinant;

			// first row
			double _m0x =  MatUtils.det3x3(m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m0y = -MatUtils.det3x3(m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m0z =  MatUtils.det3x3(m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m0w = -MatUtils.det3x3(m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// second row
			double _m1x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m1y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m1z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m1w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// third row
			double _m2x =  MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[3][1], m[3][2], m[3][3]);
			double _m2y = -MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[3][0], m[3][2], m[3][3]);
			double _m2z =  MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[3][0], m[3][1], m[3][3]);
			double _m2w = -MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[3][0], m[3][1], m[3][2]);
			// fourth row
			double _m3x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3]);
			double _m3y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3]);
			double _m3z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3]);
			double _m3w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2]);

			Mat4f out = new Mat4f();
			
			// transpose and divide by the determinant
			out.m[0][0] = (float)(_m0x * determinant_inv);
			out.m[1][1] = (float)(_m1y * determinant_inv);
			out.m[2][2] = (float)(_m2z * determinant_inv);
			out.m[3][3] = (float)(_m3w * determinant_inv);
			out.m[0][1] = (float)(_m1x * determinant_inv);
			out.m[1][0] = (float)(_m0y * determinant_inv);
			out.m[2][0] = (float)(_m0z * determinant_inv);
			out.m[0][2] = (float)(_m2x * determinant_inv);
			out.m[1][2] = (float)(_m2y * determinant_inv);
			out.m[2][1] = (float)(_m1z * determinant_inv);
			out.m[0][3] = (float)(_m3x * determinant_inv);
			out.m[3][0] = (float)(_m0w * determinant_inv);
			out.m[1][3] = (float)(_m3y * determinant_inv);
			out.m[3][1] = (float)(_m1w * determinant_inv);
			out.m[3][2] = (float)(_m2w * determinant_inv);
			out.m[2][3] = (float)(_m3z * determinant_inv);
			
			return out;
		}
		
		return null;
	}
	
	public Mat4f clean()
	{
		super.clean();
		
		return this;
	}
	
	public String toString()
	{
		return 	"mat4f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + ", " + this.m[0][3] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + ", " + this.m[1][3] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ", " + this.m[2][3] + "\n"
			  + "      " + this.m[3][0] + ", " + this.m[3][1] + ", " + this.m[3][2] + ", " + this.m[3][3] + ")";
	}
	
	public Mat4f clone()
	{
		return new Mat4f(this);
	}
	
	public static Mat4f mul(Mat4f l, Mat4f r, @Nullable Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		if(res == null) res = new Mat4f();
		
		l.mul(r, res);
		
		return res;
	}
	
	public static <T extends Tup3fW> T transform(Mat4f l, Point3f r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * 1.0f;
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * 1.0f;
		
		res.set(x_, y_, z_);

		return res;
	}
	
	public static <T extends Tup3fW> T transform(Mat4f l, Vec3f r, T res)
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
	
	public static <T extends Tup4fW> T transform(Mat4f l, Tup4fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * r.getW();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * r.getW();
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * r.getW();
		float w_ = l.m[3][0] * r.getX() + l.m[3][1] * r.getY() + l.m[3][2] * r.getZ() + l.m[3][3] * r.getW();
		
		res.set(x_, y_, z_, w_);

		return res;
	}
	
	public static <T extends Tup3fW> T transform(Mat4f l, Tup3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}

		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * 1.0f;
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * 1.0f;

		res.set(x_, y_, z_);

		return res;
	}
	
	public static <T extends Tup2fW> T transform(Mat4f l, Tup2fR r, T res)
	{
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * 1.0f + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * 1.0f + l.m[1][3] * 1.0f;

		res.set(x_, y_);

		return res;
	}
	
	public static <T extends Tup4fR & Tup4fW> T transform(Mat4f l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(l, r, r);
	}
	
	public static <T extends Tup3fR & Tup3fW> T transform(Mat4f l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(l, r, r);
	}
	
	public static Mat4f identity()
	{
		return new Mat4f().initIdentity();
	}
	
	public static Mat4f zero()
	{
		return new Mat4f().initZero();
	}
	
	public static Mat4f scaling4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling4D(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public static Mat4f scaling4D(float x, float y, float z, float w)
	{
		return new Mat4f().initScaling4D(x, y, z, w);
	}
	
	public static Mat4f scaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public static Mat4f scaling3D(float x, float y, float z)
	{
		return new Mat4f().initScaling3D(x, y, z);
	}
	
	public static Mat4f scaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling2D(t.getX(), t.getY());
	}
	
	public static Mat4f scaling2D(float x, float y)
	{
		return new Mat4f().initScaling2D(x, y);
	}
	
	public static Mat4f translation3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation3D(t.getX(), t.getY(), t.getZ());
	}
	
	public static Mat4f translation3D(float x, float y, float z)
	{
		return new Mat4f().initTranslation3D(x, y, z);
	}
	
	public static Mat4f translation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation2D(t.getX(),t.getY());
	}
	
	public static Mat4f translation2D(float x, float y)
	{
		return new Mat4f().initTranslation2D(x, y);
	}
	
	public static Mat4f rotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation2D(angles);
	}
	
	public static Mat4f rotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation2DRad(angles);
	}
	
	public static Mat4f rotation2D(float angle)
	{
		return new Mat4f().initRotation2D(angle);
	}
	
	public static Mat4f rotation2DRad(float angle)
	{
		return new Mat4f().initRotation2DRad(angle);
	}
	
	public static Mat4f rotation3D(Quatf q)
	{
		return new Mat4f().initRotation3D(q);
	}
	
	
	
	public static Mat4f pitchRotation3D(float angle)
	{
		return new Mat4f().initPitchRotation3D(angle);
	}
	
	public static Mat4f pitchRotation3DRad(float angle)
	{
		return new Mat4f().initPitchRotation3DRad(angle);
	}
	
	public static Mat4f pitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initPitchRotation3D(angle, system);
	}
	
	public static Mat4f pitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initPitchRotation3DRad(angle, system);
	}
	
	public static Mat4f pitchRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initPitchRotation3D(angle, forward, right, up);
	}
	
	public static Mat4f pitchRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initPitchRotation3DRad(angle, forward, right, up);
	}
	
	public static Mat4f yawRotation3D(float angle)
	{
		return new Mat4f().initYawRotation3D(angle);
	}
	
	public static Mat4f yawRotation3DRad(float angle)
	{
		return new Mat4f().initYawRotation3DRad(angle);
	}
	
	public static Mat4f yawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initYawRotation3D(angle, system);
	}
	
	public static Mat4f yawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initYawRotation3DRad(angle, system);
	}
	
	public static Mat4f yawRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initYawRotation3D(angle, forward, right, up);
	}
	
	public static Mat4f yawRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initYawRotation3DRad(angle, forward, right, up);
	}
	
	public static Mat4f rollRotation3D(float angle)
	{
		return new Mat4f().initRollRotation3D(angle);
	}
	
	public static Mat4f rollRotation3DRad(float angle)
	{
		return new Mat4f().initRollRotation3DRad(angle);
	}
	
	public static Mat4f rollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRollRotation3D(angle, system);
	}
	
	public static Mat4f rollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRollRotation3DRad(angle, system);
	}
	
	public static Mat4f rollRotation3D(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRollRotation3D(angle, forward, right, up);
	}
	
	public static Mat4f rollRotation3DRad(float angle, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRollRotation3DRad(angle, forward, right, up);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation3D(angles);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll)
	{
		return new Mat4f().initRotation3D(pitch, yaw, roll);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation3DRad(angles);
	}
	
	public static Mat4f rotation3DRad(float pitch, float yaw, float roll)
	{
		return new Mat4f().initRotation3DRad(pitch, yaw, roll);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3D(angles, system);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3D(pitch, yaw, roll, system);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRotation3D(angles, forward, right, up);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRotation3D(pitch, yaw, roll, forward, right, up);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3DRad(angles, system);
	}
	
	public static Mat4f rotation3DRad(float pitch ,float yaw, float roll, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3DRad(pitch, yaw, roll, system);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRotation3DRad(angles, forward, right, up);
	}
	
	public static Mat4f rotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRotation3DRad(pitch, yaw, roll, forward, right, up);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(angles, order);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(pitch, yaw, roll, order);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(angles, order);
	}
	
	public static Mat4f rotation3DRad(float pitch, float yaw, float roll, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(pitch, yaw, roll, order);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(angles, system, order);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(pitch, yaw, roll, system, order);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(angles, forward, right, up, order);
	}
	
	public static Mat4f rotation3D(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(pitch, yaw, roll, forward, right, up, order);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(angles, system, order);
	}
	
	public static Mat4f rotation3DRad(float pitch, float yaw, float roll, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(pitch, yaw, roll, system, order);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(angles, forward, right, up, order);
	}
	
	public static Mat4f rotation3DRad(float pitch, float yaw, float roll, Tup3fR forward, Tup3fR right, Tup3fR up, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(pitch, yaw, roll, forward, right, up, order);
	}
	
	public static Mat4f rotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3D(system);
	}
	
	public static Mat4f rotation3D(Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initRotation3D(forward, right, up);
	}
	
	public static Mat4f transformMatrix3D(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat4f().initTransformMatrix3D(t);
	}
	
	public static Mat4f transformMatrix3D(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat4f().initTransformMatrix3D(t);
	}
	
	public static Mat4f transformMatrix3D(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		return new Mat4f().initTransformMatrix3D(pos, rot, scale);
	}
	
	public static Mat4f transformMatrix3D(Tup3fR pos, EulerAngles3f angles, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		return new Mat4f().initTransformMatrix3D(pos, angles, scale);
	}
	
	public static Mat4f perspective(Tup2fR t, float fovY, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat4f().initPerspective(t, fovY, near, far);
	}
	public static Mat4f perspective(float width, float height, float fov, float near, float far)
	{
		return new Mat4f().initPerspective(width, height, fov, near, far);
	}
	
	public static Mat4f perspective(float fovX, float fovY, float near, float far)
	{
		return new Mat4f().initPerspective(fovX, fovY, near, far);
	}
	
	public static Mat4f perspective(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4f().initPerspective(left, right, bottom, top, near, far);
	
	}
	public static Mat4f ortho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Mat4f().initOrtho(t, near, far);
	}
	
	public static Mat4f ortho(float width, float height, float length)
	{
		return new Mat4f().initOrtho(width, height, length);
	}
	
	public static Mat4f ortho(float width, float height, float near, float far)
	{
		return new Mat4f().initOrtho(width, height, near, far);
	}
	
	public static Mat4f ortho(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4f().initOrtho(left, right, bottom, top, near, far);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return new Mat4f().initViewMatrix(pos, rot);
	}
	
	public static Mat4f viewMatrix(float posX, float posY, float posZ, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return new Mat4f().initViewMatrix(posX, posY, posZ, rot);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		return new Mat4f().initViewMatrix(pos, orientation);
	}
	
	public static Mat4f viewMatrix(float posX, float posY, float posZ, LinearSystem3 orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		return new Mat4f().initViewMatrix(posX, posY, posZ, orientation);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initViewMatrix(pos, forward, right, up);
	}
	
	public static Mat4f viewMatrix(float posX, float posY, float posZ, Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(right == null) throw new ArgumentNullException("right");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return new Mat4f().initViewMatrix(posX, posY, posZ, forward, right, up);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initViewMatrix(pos, angles);
	}
	
	public static Mat4f viewMatrix(float posX, float posY, float posZ, EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initViewMatrix(posX, posY, posZ, angles);
	}
	
	public static Mat4f viewMatrixRad(Tup3fR pos, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initViewMatrixRad(pos, angles);
	}
	
	public static Mat4f viewMatrixRad(float posX, float posY, float posZ, EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initViewMatrixRad(posX, posY, posZ, angles);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return new Mat4f().initViewMatrix(pos, pitch, yaw, roll);
	}
	
	public static Mat4f viewMatrix(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		return new Mat4f().initViewMatrix(posX, posY, posZ, pitch, yaw, roll);
	}
	
	public static Mat4f viewMatrixRad(Tup3fR pos, float pitch, float yaw, float roll)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return new Mat4f().initViewMatrixRad(pos, pitch, yaw, roll);
	}
	
	public static Mat4f viewMatrixRad(float posX, float posY, float posZ, float pitch, float yaw, float roll)
	{
		return new Mat4f().initViewMatrixRad(posX, posY, posZ, pitch, yaw, roll);
	}
	
	public static Mat4f lookAtMatrix(Tup3fR pos, Tup3fR target, Tup3fR worldUp)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(target == null) throw new ArgumentNullException("target");
			if(worldUp == null) throw new ArgumentNullException("worldUp");
		}
		
		return new Mat4f().initLookAtMatrix(pos, target, worldUp);
	} 
}
