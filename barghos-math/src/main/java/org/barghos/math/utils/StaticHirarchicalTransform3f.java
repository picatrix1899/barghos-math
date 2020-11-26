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
import org.barghos.math.utils.api.HirarchicalTransform3f;
import org.barghos.math.utils.api.Transform3f;
import org.barghos.math.utils.api.Transformable3f;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public class StaticHirarchicalTransform3f implements HirarchicalTransform3f
{
	protected final Point3f position = new Point3f();
	protected final EulerAngles3f orientation = new EulerAngles3f();
	protected final Vec3f scale = new Vec3f();
	
	private Transformable3f parent;
	
	public StaticHirarchicalTransform3f()
	{
		this.scale.set(1.0f, 1.0f, 1.0f);
	}
	
	public StaticHirarchicalTransform3f(Transform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t);
	}
	
	public StaticHirarchicalTransform3f(HirarchicalTransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t);
	}
	
	public StaticHirarchicalTransform3f(Tup3fR position, EulerAngles3f orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(position, orientation, scale);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, EulerAngles3f orientation, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(posX, posY, posZ, orientation, scale);
	}

	public StaticHirarchicalTransform3f(Tup3fR position, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(position, pitch, yaw, roll, scale);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		set(posX, posY, posZ, pitch, yaw, roll, scale);
	}
	
	public StaticHirarchicalTransform3f(Tup3fR position, EulerAngles3f orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(position, orientation, scale);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, EulerAngles3f orientation, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(posX, posY, posZ, orientation, scale);
	}

	public StaticHirarchicalTransform3f(Tup3fR position, float pitch, float yaw, float roll, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
		}
		
		set(position, pitch, yaw, roll, scale);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale)
	{
		set(posX, posY, posZ, pitch, yaw, roll, scale);
	}
	
	public StaticHirarchicalTransform3f(Tup3fR position, EulerAngles3f orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(position, orientation, scaleX, scaleY, scaleZ);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, EulerAngles3f orientation, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		set(posX, posY, posZ, orientation, scaleX, scaleY, scaleZ);
	}

	public StaticHirarchicalTransform3f(Tup3fR position, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(position == null) throw new ArgumentNullException("position");
		}
		
		set(position, pitch, yaw, roll, scaleX, scaleY, scaleZ);
	}

	public StaticHirarchicalTransform3f(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		set(posX, posY, posZ, pitch, yaw, roll, scaleX, scaleY, scaleZ);
	}
	
	public StaticHirarchicalTransform3f set(Transform3f t)
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
	
	public StaticHirarchicalTransform3f set(HirarchicalTransform3f t)
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
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, EulerAngles3f orientation, Tup3fR scale)
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
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3f orientation, Tup3fR scale)
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
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(pos);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.position.set(posX, posY, posZ);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, EulerAngles3f orientation, float scale)
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
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3f orientation, float scale)
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
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, float scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scale)
	{
		this.position.set(posX, posY, posZ);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, EulerAngles3f orientation, float scaleX, float scaleY, float scaleZ)
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
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, EulerAngles3f orientation, float scaleX, float scaleY, float scaleZ)
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
	
	public StaticHirarchicalTransform3f set(Tup3fR pos, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f set(float posX, float posY, float posZ, float pitch, float yaw, float roll, float scaleX, float scaleY, float scaleZ)
	{
		this.position.set(posX, posY, posZ);
		this.orientation.set(pitch, yaw, roll);
		this.scale.set(scaleX, scaleY, scaleZ);
		
		return this;
	}
	
	public Point3f getRelativePosition()
	{
		return this.position.clone();
	}
	
	public <T extends Tup3fW> T getRelativePosition(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.position);
		
		return res;
	}
	
	public EulerAngles3f getRelativeOrientation()
	{
		return this.orientation.clone();
	}
	
	public <T extends Tup3fW> T getRelativeOrientation(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.orientation);
		
		return res;
	}
	
	public Vec3f getRelativeScale()
	{
		return this.scale.clone();
	}
	
	public <T extends Tup3fW> T getRelativeScale(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.scale);
		
		return res;
	}
	
	public Point3f getPosition()
	{
		if(hasParent())
			return getParent().getTransform().getTransformationMatrix4f().transform(getRelativePosition());
		
		return getRelativePosition(new Point3f());
	}
	
	public <T extends Tup3fW> T getPosition(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return getParent().getTransform().getTransformationMatrix4f().transform(getRelativePosition(), res);
		
		return getRelativePosition(res);
	}
	
	public EulerAngles3f getOrientation()
	{
		if(hasParent())
			return new EulerAngles3f(getRelativeOrientation().toRotationMatrix4f().mul(getParent().getTransform().getTransformationMatrix4f()));
		
		return getRelativeOrientation(new EulerAngles3f());
	}
	
	public <T extends Tup3fW> T getOrientation(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
		{
			res.set(new EulerAngles3f(getRelativeOrientation().toRotationMatrix4f().mul(getParent().getTransform().getTransformationMatrix4f())));
			
			return res;
		}

		return getRelativeOrientation(res);
	}
	
	public Vec3f getScale()
	{
		if(hasParent())
			return getParent().getTransform().getScalingMatrix4f().transform(getRelativeScale());
		
		return getRelativeScale();
	}
	
	public <T extends Tup3fW> T getScale(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return getParent().getTransform().getScalingMatrix4f().transform(getRelativeScale(), res);
		
		return getRelativeScale(res);
	}
	
	public StaticHirarchicalTransform3f setPosition(Tup3fR pos)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		this.position.set(pos);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setPosition(float x, float y, float z)
	{
		this.position.set(x, y, z);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setPosX(float x)
	{
		this.position.setX(x);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setPosY(float y)
	{
		this.position.setY(y);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setPosZ(float z)
	{
		this.position.setZ(z);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setOrientation(Tup3fR orientation)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(orientation == null) throw new ArgumentNullException("orientation");
		}
		
		this.orientation.set(orientation);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setOrientation(float pitch, float yaw, float roll)
	{
		this.orientation.set(pitch, yaw, roll);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setPitch(float pitch)
	{
		this.orientation.setPitch(pitch);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setYaw(float yaw)
	{
		this.orientation.setYaw(yaw);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setRoll(float roll)
	{
		this.orientation.setRoll(roll);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScale(Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(scale == null) throw new ArgumentNullException("scale");
		}
		
		this.scale.set(scale);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScale(float factor)
	{
		this.scale.set(factor);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScale(float x, float y, float z)
	{
		this.scale.set(x, y, z);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScaleX(float x)
	{
		this.scale.setX(x);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScaleY(float y)
	{
		this.scale.setY(y);
		
		return this;
	}
	
	public StaticHirarchicalTransform3f setScaleZ(float z)
	{
		this.scale.setZ(z);
		
		return this;
	}
	
	public Mat4f getTranslationMatrix4f()
	{
		if(hasParent())
			return Mat4f.translation3D(getParent().getTransform().getTransformationMatrix4f().transform(this.position.clone()));
		
		return getRelativeTranslationMatrix4f();
	}
	
	public Mat4f getTranslationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return res.initTranslation3D(getParent().getTransform().getTransformationMatrix4f().transform(this.position.clone()));
		
		return getRelativeTranslationMatrix4f(res);
	}
	
	public Mat4f getOrientationMatrix4f()
	{
		if(hasParent())
			return getRelativeOrientationMatrix4f().mul(getParent().getTransform().getOrientationMatrix4f());
		
		return getRelativeOrientationMatrix4f();
	}
	
	public Mat4f getOrientationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return getRelativeOrientationMatrix4f(res).mul(getParent().getTransform().getOrientationMatrix4f());
		
		return getRelativeOrientationMatrix4f(res);
	}
	
	public Mat4f getScalingMatrix4f()
	{
		if(hasParent())
			return getRelativeScalingMatrix4f().mul(getParent().getTransform().getScalingMatrix4f());
		
		return getRelativeScalingMatrix4f();
	}
	
	public Mat4f getScalingMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return getRelativeScalingMatrix4f(res).mul(getParent().getTransform().getScalingMatrix4f());
		
		return getRelativeScalingMatrix4f(res);
	}
	
	public Mat4f getTransformationMatrix4f()
	{
		if(hasParent())
			return getRelativeTransformationMatrix4f().mul(getParent().getTransform().getTransformationMatrix4f());
			
		return getRelativeTransformationMatrix4f();
	}
	
	public Mat4f getTransformationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(hasParent())
			return getRelativeTransformationMatrix4f(res).mul(getParent().getTransform().getTransformationMatrix4f());
			
		return getRelativeTransformationMatrix4f(res);
	}
	
	public Mat4f getRelativeTranslationMatrix4f()
	{
		return Mat4f.translation3D(this.position);
	}
	
	public Mat4f getRelativeTranslationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initTranslation3D(this.position);
	}
	
	public Mat4f getRelativeOrientationMatrix4f()
	{
		return Mat4f.rotation3D(this.orientation);
	}
	
	public Mat4f getRelativeOrientationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initRotation3D(this.orientation);
	}
	
	public Mat4f getRelativeScalingMatrix4f()
	{
		return Mat4f.scaling3D(this.scale);
	}
	
	public Mat4f getRelativeScalingMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initScaling3D(this.scale);
	}
	
	public Mat4f getRelativeTransformationMatrix4f()
	{
		return Mat4f.transformMatrix3D(this);
	}
	
	public Mat4f getRelativeTransformationMatrix4f(Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.initTransformMatrix3D(this);
	}
	
	public Transformable3f getParent()
	{
		return this.parent;
	}
	
	public StaticHirarchicalTransform3f setParent(Transformable3f parent)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(parent == null) throw new ArgumentNullException("parent");
		}
		
		this.parent = parent;
		
		return this;
	}
	
	public boolean hasParent()
	{
		return this.parent != null;
	}
	
	public StaticHirarchicalTransform3f getTransform()
	{
		return this;
	}
	
	public String toString()
	{
		return "staticTransform3f(position= " + this.position + ", orientation=" + this.orientation + ", scale=" + this.scale + ")";
	}
}
