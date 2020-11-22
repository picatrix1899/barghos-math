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

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.matrix.Mat4f;
import org.barghos.math.point.Point3f;
import org.barghos.math.utils.api.ITransform3f;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public class Transform3f implements ITransform3f
{
	protected final Point3f position = new Point3f();
	protected final EulerAngles3f orientation = new EulerAngles3f();
	protected final Vec3f scale = new Vec3f();
	
	private ITransform3f parent;
	
	public Transform3f()
	{
		this.scale.set(1.0f, 1.0f, 1.0f);
	}
	
	public Transform3f(Transform3f t)
	{
		set(t);
	}
	
	public Transform3f(Tup3fR position, EulerAngles3f orientation, Tup3fR size)
	{
		set(position, orientation, size);
	}

	public Transform3f set(Transform3f t)
	{
		t.getRelativePosition(this.position);
		t.getRelativeOrientation(this.orientation);
		t.getRelativeScale(this.scale);
		
		return this;
	}
	
	public Transform3f set(Tup3fR position, EulerAngles3f orientation, Tup3fR size)
	{
		return setRelativePosition(position).setRelativeOrientation(orientation).setRelativeScale(size);
	}
	
	public Transform3f setRelativePosition(Tup3fR position)
	{
		this.position.set(position);
		
		return this;
	}
	
	public Transform3f setRelativePosition(float x, float y, float z)
	{
		this.position.set(x, y, z);
		
		return this;
	}
	
	public Transform3f setRelativeOrientation(EulerAngles3f orientation)
	{
		this.orientation.set(orientation);
		
		return this;
	}
	
	public Transform3f setRelativeOrientation(float pitch, float yaw, float roll)
	{
		this.orientation.set(pitch, yaw, roll);
		
		return this;
	}
	
	public Transform3f setRelativeScale(Tup3fR size)
	{
		this.scale.set(size);
		
		return this;
	}
	
	public Transform3f setRelativeScale(float scalar)
	{
		this.scale.set(scalar, scalar, scalar);
		
		return this;
	}
	
	public Transform3f setRelativeScale(float x, float y, float z)
	{
		this.scale.set(x, y, z);
		
		return this;
	}
	
	public Transform3f setParent(ITransform3f parent)
	{
		this.parent = parent;
		return this;
	}
	
	public Transform3f rotate(float pitch, float yaw, float roll)
	{
		this.orientation.rotate(pitch, yaw, roll);
		
		return this;
	}
	
	public Point3f getRelativePosition()
	{
		return this.position.clone();
	}
	
	public <T extends Tup3fW> T getRelativePosition(T res)
	{
		res.set(this.position);
		
		return res;
	}
	
	public EulerAngles3f getRelativeOrientation()
	{
		return new EulerAngles3f(this.orientation);
	}

	public EulerAngles3f getRelativeOrientation(EulerAngles3f res)
	{
		return res.set(this.orientation);
	}
	
	public Vec3f getRelativeScale()
	{
		return new Vec3f(this.scale);
	}
	
	public <T extends Tup3fW> T getRelativeScale(T res)
	{
		res.set(this.scale);
		
		return res;
	}
	
	public ITransform3f getParent()
	{
		return this.parent;
	}
	
	public Mat3f getRelativeRotationMatrix3f()
	{
		return this.orientation.toRotationMatrix3f();
	}
	
	public Mat3f getRelativeRotationMatrix3f(Mat3f res)
	{
		return this.orientation.toRotationMatrix3f(res);
	}
	
	public Mat4f getRelativeRotationMatrix4f()
	{
		return this.orientation.toRotationMatrix4f();
	}
	
	public Mat4f getRelativeRotationMatrix4f(Mat4f res)
	{
		return this.orientation.toRotationMatrix4f(res);
	}
	
	public Mat4f getRelativeTranslationMatrix4f()
	{
		return Mat4f.translation3D(this.position);
	}
	
	public Mat4f getRelativeTranslationMatrix4f(Mat4f res)
	{
		return res.initTranslation3D(this.position);
	}
	
	public Mat3f getRelativeScaleMatrix3f()
	{
		return Mat3f.scaling3D(this.scale);
	}
	
	public Mat3f getRelativeScaleMatrix3f(Mat3f res)
	{
		return res.initScaling3D(this.scale);
	}
	
	public Mat4f getRelativeScaleMatrix4f()
	{
		return Mat4f.scaling3D(this.scale);
	}
	
	public Mat4f getRelativeScaleMatrix4f(Mat4f res)
	{
		return res.initScaling3D(this.scale);
	}
	
	public Mat4f getRelativeTransformationMatrix4f()
	{
		return Mat4f.identity().initTransform3D(this);
	}
	
	public Mat4f getRelativeTransformationMatrix4f(Mat4f res)
	{
		return res.initTransform3D(this);
	}
	
	public Mat4f getAbsoluteTransformationMatrix4f()
	{
		if(this.parent != null)
			return getRelativeTransformationMatrix4f().mul(this.parent.getAbsoluteTransformationMatrix4f());
		
		return getRelativeTransformationMatrix4f();
	}
	
	public Mat4f getAbsulteTransformationMatrix4f(Mat4f res)
	{
		if(this.parent != null)
			return getRelativeTransformationMatrix4f(res).mul(this.parent.getAbsoluteTransformationMatrix4f());
		
		return getRelativeTransformationMatrix4f(res);
	}
	
	public String toString()
	{
		return "transform3f(position= " + this.position + ", orientation=" + this.orientation + ", size=" + this.scale + ")";
	}
}
