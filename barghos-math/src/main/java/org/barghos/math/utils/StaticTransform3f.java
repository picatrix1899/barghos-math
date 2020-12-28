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
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat4f;
import org.barghos.math.point.Point3f;
import org.barghos.math.utils.api.EulerAngles3fR;
import org.barghos.math.utils.api.EulerAngles3fW;
import org.barghos.math.utils.api.HirarchicalTransform3f;
import org.barghos.math.utils.api.Transform3f;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public class StaticTransform3f implements Transform3f
{
	protected final Point3f position = new Point3f();
	protected final EulerAnglesDeg3f orientation = new EulerAnglesDeg3f();
	protected final Vec3f scale = new Vec3f();

	public StaticTransform3f()
	{
		this.scale.set(1.0f, 1.0f, 1.0f);
	}
	
	public StaticTransform3f(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t);
	}
	
	public StaticTransform3f(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t);
	}
	
	public StaticTransform3f(Tup3fR position, EulerAngles3fR orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(position, orientation, scale);
	}

	public StaticTransform3f(float posX, float posY, float posZ, EulerAngles3fR orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(posX, posY, posZ, orientation, scale);
	}

	public StaticTransform3f(Tup3fR position, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(position, pitch, yaw, roll, scale);
	}

	public StaticTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(posX, posY, posZ, pitch, yaw, roll, scale);
	}
	
	public StaticTransform3f(Tup3fR position, EulerAngles3fR orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(position, orientation, scale);
	}

	public StaticTransform3f(float posX, float posY, float posZ, EulerAngles3fR orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(posX, posY, posZ, orientation, scale);
	}

	public StaticTransform3f(Tup3fR position, float pitch, float yaw, float roll, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
		}
		
		set(position, pitch, yaw, roll, scale);
	}

	public StaticTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale)
	{
		set(posX, posY, posZ, pitch, yaw, roll, scale);
	}
	
	public StaticTransform3f(Tup3fR position, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(position, orientation, scaleX, scaleY, scaleZ);
	}

	public StaticTransform3f(float posX, float posY, float posZ, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(posX, posY, posZ, orientation, scaleX, scaleY, scaleZ);
	}

	public StaticTransform3f(Tup3fR position, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
		}
		
		set(position, pitch, yaw, roll, scaleX, scaleY, scaleZ);
	}

	public StaticTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		set(posX, posY, posZ, pitch, yaw, roll, scaleX, scaleY, scaleZ);
	}
	
	public StaticTransform3f set(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		t.getPosition(this.position);
		t.getOrientation(this.orientation);
		t.getScale(this.scale);
		
		return this;
	}
	
	public StaticTransform3f set(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		t.getRelativePosition(this.position);
		t.getRelativeOrientation(this.orientation);
		t.getRelativeScale(this.scale);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, EulerAngles3fR orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(pos);
		this.orientation.set(orientation);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(posX, posY, posZ);
		this.orientation.set(orientation);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(pos);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(posX, posY, posZ);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, EulerAngles3fR orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		this.position.set(pos);
		this.orientation.set(orientation);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		this.position.set(posX, posY, posZ);
		this.orientation.set(orientation);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale)
	{
		this.position.set(posX, posY, posZ);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(pos);
		this.orientation.set(orientation);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, EulerAngles3fR orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(posX, posY, posZ);
		this.orientation.set(orientation);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}
	
	public StaticTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}
	
	public StaticTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		this.position.set(posX, posY, posZ);
		this.orientation.setDeg(pitch, yaw, roll);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}

	
	public Point3f getPosition()
	{
		return this.position.clone();
	}
	
	public <T extends Tup3fW> T getPosition(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}

		res.set(this.position);
		
		return res;
	}
	
	public EulerAngles3fR getOrientation()
	{
		return this.orientation.clone();
	}
	
	public <T extends EulerAngles3fW> T getOrientation(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}

		res.set(this.orientation);
		
		return res;
	}
	
	public Vec3f getScale()
	{
		return this.scale.clone();
	}
	
	public <T extends Tup3fW> T getScale(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}

		res.set(this.scale);
		
		return res;
	}
	
	public StaticTransform3f setPosition(Tup3fR pos)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		
		return this;
	}
	
	public StaticTransform3f setPosition(float x, float y, float z)
	{
		this.position.set(x, y, z);
		
		return this;
	}
	
	public StaticTransform3f setPosX(float x)
	{
		this.position.setX(x);
		
		return this;
	}
	
	public StaticTransform3f setPosY(float y)
	{
		this.position.setY(y);
		
		return this;
	}
	
	public StaticTransform3f setPosZ(float z)
	{
		this.position.setZ(z);
		
		return this;
	}
	
	public StaticTransform3f setOrientation(Tup3fR orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		this.orientation.set(orientation);
		
		return this;
	}
	
	public StaticTransform3f setOrientation(float pitch, float yaw, float roll)
	{
		this.orientation.setDeg(pitch, yaw, roll);
		
		return this;
	}
	
	public StaticTransform3f setPitch(float pitch)
	{
		this.orientation.setPitchDeg(pitch);
		
		return this;
	}
	
	public StaticTransform3f setYaw(float yaw)
	{
		this.orientation.setYawDeg(yaw);
		
		return this;
	}
	
	public StaticTransform3f setRoll(float roll)
	{
		this.orientation.setRollDeg(roll);
		
		return this;
	}
	
	public StaticTransform3f setScale(Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticTransform3f setScale(float factor)
	{
		this.scale.set(factor);
		
		return this;
	}
	
	public StaticTransform3f setScale(float x, float y, float z)
	{
		this.scale.set(x, y, z);
		
		return this;
	}
	
	public StaticTransform3f setScaleX(float x)
	{
		this.scale.setX(x);
		
		return this;
	}
	
	public StaticTransform3f setScaleY(float y)
	{
		this.scale.setY(y);
		
		return this;
	}
	
	public StaticTransform3f setScaleZ(float z)
	{
		this.scale.setZ(z);
		
		return this;
	}
	
	public Mat4f getTranslationMatrix4f()
	{
		return Mat4f.translation3D(this.position);
	}
	
	public Mat4f getTranslationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initTranslation3D(this.position);
	}
	
	public Mat4f getOrientationMatrix4f()
	{
		return Mat4f.rotation3D(this.orientation);
	}
	
	public Mat4f getOrientationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initRotation3D(this.orientation);
	}
	
	public Mat4f getScalingMatrix4f()
	{
		return Mat4f.scaling3D(this.scale);
	}
	
	public Mat4f getScalingMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initScaling3D(this.scale);
	}
	
	public Mat4f getTransformationMatrix4f()
	{
		return Mat4f.transformMatrix3D(this);
	}
	
	public Mat4f getTransformationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}

		return res.initTransformMatrix3D(this);
	}

	public StaticTransform3f getTransform()
	{
		return this;
	}
	
	public String toString()
	{
		return "staticTransform3f(position= " + this.position + ", orientation=" + this.orientation + ", scale=" + this.scale + ")";
	}
}
