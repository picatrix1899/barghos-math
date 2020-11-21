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
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.util.Nullable;

import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.point.Point2f;
import org.barghos.math.quat.Quatf;
import org.barghos.math.utils.EulerAngles2f;
import org.barghos.math.utils.EulerAngles3f;
import org.barghos.math.utils.EulerAnglesRad2f;
import org.barghos.math.utils.EulerAnglesRad3f;
import org.barghos.math.utils.LinearSystem3;
import org.barghos.math.utils.Maths;
import org.barghos.math.utils.api.EulerRotationOrder3;
import org.barghos.math.utils.api.ITransform2f;
import org.barghos.math.vec2.Vec2f;

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
	
	public Mat3f initRotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		float rad = angles.getAngle() * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		setRow(0, Maths.cos(angles.getAngle()), 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angles.getAngle()), 0.0f);
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
	
	public Mat3f initRotation2DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angle), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRotation3D(Quatf q)
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
		
		setColumn(0, left);
		setColumn(1, up);
		setColumn(2, forward);
		
		return this;
	}
	
	public Mat3f initPitchRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(rad), Maths.sin(rad));
		setRow(2, 0.0f, -Maths.sin(rad), Maths.cos(rad));
		
		return this;
	}

	public Mat3f initPitchRotation3DRad(float angle)
	{
		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(angle), Maths.sin(angle));
		setRow(2, 0.0f, -Maths.sin(angle), Maths.cos(angle));
		
		return this;
	}
	
	public Mat3f initPitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(rad), Maths.sin(rad));
		setRow(2, 0.0f, -Maths.sin(rad), Maths.cos(rad));
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}

	public Mat3f initPitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(angle), Maths.sin(angle));
		setRow(2, 0.0f, -Maths.sin(angle), Maths.cos(angle));
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initYawRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), 0.0f, -Maths.sin(rad));
		setRow(1, 0.0f, 1, 0.0f);
		setRow(2, Maths.sin(rad), 0.0f, Maths.cos(rad));
		
		return this;
	}
	
	public Mat3f initYawRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, -Maths.sin(angle));
		setRow(1, 0.0f, 1, 0.0f);
		setRow(2, Maths.sin(angle), 0.0f, Maths.cos(angle));
		
		return this;
	}
	
	public Mat3f initYawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, -Maths.sin(rad));
		setRow(1, 0.0f, 1, 0.0f);
		setRow(2, Maths.sin(rad), 0.0f, Maths.cos(rad));
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initYawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, Maths.cos(angle), 0.0f, -Maths.sin(angle));
		setRow(1, 0.0f, 1, 0.0f);
		setRow(2, Maths.sin(angle), 0.0f, Maths.cos(angle));
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initRollRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), Maths.sin(rad), 0.0f);
		setRow(1, -Maths.sin(rad), Maths.cos(rad), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRollRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), Maths.sin(angle), 0.0f);
		setRow(1, -Maths.sin(angle), Maths.cos(angle), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat3f initRollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), Maths.sin(rad), 0.0f);
		setRow(1, -Maths.sin(rad), Maths.cos(rad), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initRollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, Maths.cos(angle), Maths.sin(angle), 0.0f);
		setRow(1, -Maths.sin(angle), Maths.cos(angle), 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initRotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initRotation3D(angles, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat3f initRotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initRotation3DRad(angles, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat3f initRotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initRotation3D(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat3f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initRotation3DRad(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat3f initRotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
		}
		
		return this;
	}
	
	public Mat3f initRotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
		}
		
		return this;
	}
	
	public Mat3f initRotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
		}
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
		}
		
		mul(Mat3f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat3f initRotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setColumn(0, system.getLeft());
		setColumn(1, system.getUp());
		setColumn(2, system.getForward());
		
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
	
	public Mat3f mul(Mat3fR r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		mul(r, this);
		
		return this;
	}
	
	public Mat3f transpose()
	{
		return super.transpose(this);
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
	
	
	
	public Mat3f scale3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat3f.scaling3D(t));
	}
	
	public Mat3f scale3D(float x, float y, float z)
	{
		return mul(Mat3f.scaling3D(x, y, z));
	}
	
	public Mat3f scale2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat3f.scaling2D(t));
	}
	
	public Mat3f scale2D(float x, float y)
	{
		return mul(Mat3f.scaling2D(x, y));
	}
	
	public Mat3f rotate2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat3f.rotation2D(angles));
	}
	
	public Mat3f rotate2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat3f.rotation2DRad(angles));
	}
	
	public Mat3f rotate2D(float angle)
	{
		return mul(Mat3f.rotation2D(angle));
	}
	
	public Mat3f rotate2DRad(float angle)
	{
		return mul(Mat3f.rotation2DRad(angle));
	}
	
	public Mat3f rotate3D(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return mul(Mat3f.rotation3D(q));
	}

	public Mat3f rotate3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(forward == null) throw new ArgumentNullException("forward");
			if(left == null) throw new ArgumentNullException("left");
			if(up == null) throw new ArgumentNullException("up");
		}
		
		return mul(Mat3f.rotation3D(forward, left, up));
	}
	
	public Mat3f rotate3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.rotation3D(system));
	}
	
	public Mat3f pitchRotate3D(float angle)
	{
		return mul(Mat3f.pitchRotation3D(angle));
	}
	
	public Mat3f pitchRotate3DRad(float angle)
	{
		return mul(Mat3f.pitchRotation3DRad(angle));
	}
	
	public Mat3f pitchRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.pitchRotation3D(angle, system));
	}
	
	public Mat3f pitchRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.pitchRotation3DRad(angle, system));
	}
	
	public Mat3f yawRotate3D(float angle)
	{
		return mul(Mat3f.yawRotation3D(angle));
	}
	
	public Mat3f yawRotate3DRad(float angle)
	{
		return mul(Mat3f.yawRotation3DRad(angle));
	}
	
	public Mat3f yawRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.yawRotation3D(angle, system));
	}
	
	public Mat3f yawRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.yawRotation3DRad(angle, system));
	}
	
	public Mat3f rollRotate3D(float angle)
	{
		return mul(Mat3f.rollRotation3D(angle));
	}
	
	public Mat3f rollRotate3DRad(float angle)
	{
		return mul(Mat3f.rollRotation3DRad(angle));
	}
	
	public Mat3f rollRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.rollRotation3D(angle, system));
	}
	
	public Mat3f rollRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.rollRotation3DRad(angle, system));
	}
	
	public Mat3f rotate3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat3f.rotation3D(angles));
	}
	
	public Mat3f rotate3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat3f.rotation3DRad(angles));
	}
	
	public Mat3f rotate3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.rotation3D(angles, system));
	}
	
	public Mat3f rotate3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat3f.rotation3DRad(angles, system));
	}
	
	public Mat3f rotate3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat3f.rotation3D(angles, order));
	}
	
	public Mat3f rotate3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat3f.rotation3DRad(angles, order));
	}
	
	public Mat3f rotate3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat3f.rotation3D(angles, system, order));
	}
	
	public Mat3f rotate3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat3f.rotation3DRad(angles, system, order));
	}
	
	public Mat3f translate2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat3f.translation2D(t));
	}
	
	public Mat3f translate2D(float x, float y)
	{
		return mul(Mat3f.translation2D(x, y));
	}
	
	public Mat3f clean()
	{
		super.clean();
		
		return this;
	}
	
	public String toString()
	{
		return 	"mat3f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ")";
	}
	
	public Mat3f clone()
	{
		return new Mat3f(this);
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
	
	public static Mat3f rotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat3f().initRotation2D(angles);
	}
	
	public static Mat3f rotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat3f().initRotation2DRad(angles);
	}
	
	public static Mat3f rotation2D(float angle)
	{
		return new Mat3f().initRotation2D(angle);
	}
	
	public static Mat3f rotation2DRad(float angle)
	{
		return new Mat3f().initRotation2DRad(angle);
	}
	
	public static Mat3f rotation3D(Quatf q)
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
	
	public static Mat3f rotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initRotation3D(system);
	}
	
	public static Mat3f pitchRotation3D(float angle)
	{
		return new Mat3f().initPitchRotation3D(angle);
	}
	
	public static Mat3f pitchRotation3DRad(float angle)
	{
		return new Mat3f().initPitchRotation3DRad(angle);
	}
	
	public static Mat3f pitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initPitchRotation3D(angle, system);
	}
	
	public static Mat3f pitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initPitchRotation3DRad(angle, system);
	}
	
	public static Mat3f yawRotation3D(float angle)
	{
		return new Mat3f().initYawRotation3D(angle);
	}
	
	public static Mat3f yawRotation3DRad(float angle)
	{
		return new Mat3f().initYawRotation3DRad(angle);
	}
	
	public static Mat3f yawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initYawRotation3D(angle, system);
	}
	
	public static Mat3f yawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initYawRotation3DRad(angle, system);
	}
	
	public static Mat3f rollRotation3D(float angle)
	{
		return new Mat3f().initRollRotation3D(angle);
	}
	
	public static Mat3f rollRotation3DRad(float angle)
	{
		return new Mat3f().initRollRotation3DRad(angle);
	}
	
	public static Mat3f rollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initRollRotation3D(angle);
	}
	
	public static Mat3f rollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initRollRotation3DRad(angle, system);
	}
	
	public static Mat3f rotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat3f().initRotation3D(angles);
	}
	
	public static Mat3f rotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat3f().initRotation3DRad(angles);
	}
	
	public static Mat3f rotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initRotation3D(angles, system);
	}
	
	public static Mat3f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat3f().initRotation3DRad(angles, system);
	}
	
	public static Mat3f rotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat3f().initRotation3D(angles, order);
	}
	
	public static Mat3f rotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat3f().initRotation3DRad(angles, order);
	}
	
	public static Mat3f rotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat3f().initRotation3D(angles, system, order);
	}
	
	public static Mat3f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat3f().initRotation3DRad(angles, system, order);
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
}
