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

package org.barghos.math;

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec3.Vec3;

/**
 * @author picatrix1899
 *
 */
public class Transform3 implements ITransform3
{
	protected final Point3 position = new Point3();
	protected final EulerAngles3f orientation = new EulerAngles3f();
	protected final Vec3 scale = new Vec3();
	
	private ITransform3 parent;
	
	public Transform3()
	{
		this.scale.set(1.0f, 1.0f, 1.0f);
	}
	
	public Transform3(Transform3 t)
	{
		set(t);
	}
	
	public Transform3(Tup3fR position, EulerAngles3f orientation, Tup3fR size)
	{
		set(position, orientation, size);
	}

	public Transform3 set(Transform3 t)
	{
		t.getPosition(this.position);
		t.getOrientation(this.orientation);
		t.getScale(this.scale);
		return this;
	}
	
	public Transform3 set(Tup3fR position, EulerAngles3f orientation, Tup3fR size)
	{
		return setPosition(position).setOrientation(orientation).setScale(size);
	}
	
	public Transform3 setPosition(Tup3fR position)
	{
		this.position.set(position); return this;
	}
	
	public Transform3 setPosition(float x, float y, float z)
	{
		this.position.set(x, y, z); return this;
	}
	
	public Transform3 setOrientation(EulerAngles3f orientation)
	{
		this.orientation.set(orientation); return this;
	}
	
	public Transform3 setOrientation(float pitch, float yaw, float roll)
	{
		this.orientation.set(pitch, yaw, roll); return this;
	}
	
	public Transform3 setScale(Tup3fR size)
	{
		this.scale.set(size); return this;
	}
	
	public Transform3 setScale(float scalar)
	{
		this.scale.set(scalar, scalar, scalar); return this;
	}
	
	public Transform3 setScale(float x, float y, float z)
	{
		this.scale.set(x, y, z); return this;
	}
	
	public Transform3 setParent(ITransform3 parent)
	{
		this.parent = parent;
		return this;
	}
	
	public Point3 getPosition()
	{
		return this.position.clone();
	}

	@SuppressWarnings("unchecked")
	public <T extends Tup3fW> T getPosition(T res)
	{
		return (T) res.set(this.position);
	}
	
	public EulerAngles3f getOrientation()
	{
		return new EulerAngles3f(this.orientation);
	}

	public EulerAngles3f getOrientation(EulerAngles3f res)
	{
		if(res == null) res = new EulerAngles3f();
		return res.set(this.orientation);
	}
	
	public Vec3 getScale()
	{
		return new Vec3(this.scale);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Tup3fW> T getScale(T res)
	{
		return (T) res.set(this.scale);
	}
	
	public ITransform3 getParent()
	{
		return this.parent;
	}
	
	public String toString()
	{
		return "transform3(position= " + this.position + ", orientation=" + this.orientation + ", size=" + this.scale + ")";
	}
}
