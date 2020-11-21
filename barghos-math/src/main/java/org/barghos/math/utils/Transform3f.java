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
		t.getPosition(this.position);
		t.getOrientation(this.orientation);
		t.getScale(this.scale);
		return this;
	}
	
	public Transform3f set(Tup3fR position, EulerAngles3f orientation, Tup3fR size)
	{
		return setPosition(position).setOrientation(orientation).setScale(size);
	}
	
	public Transform3f setPosition(Tup3fR position)
	{
		this.position.set(position); return this;
	}
	
	public Transform3f setPosition(float x, float y, float z)
	{
		this.position.set(x, y, z); return this;
	}
	
	public Transform3f setOrientation(EulerAngles3f orientation)
	{
		this.orientation.set(orientation); return this;
	}
	
	public Transform3f setOrientation(float pitch, float yaw, float roll)
	{
		this.orientation.set(pitch, yaw, roll); return this;
	}
	
	public Transform3f setScale(Tup3fR size)
	{
		this.scale.set(size); return this;
	}
	
	public Transform3f setScale(float scalar)
	{
		this.scale.set(scalar, scalar, scalar); return this;
	}
	
	public Transform3f setScale(float x, float y, float z)
	{
		this.scale.set(x, y, z); return this;
	}
	
	public Transform3f setParent(ITransform3f parent)
	{
		this.parent = parent;
		return this;
	}
	
	public Point3f getPosition()
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
	
	public Vec3f getScale()
	{
		return new Vec3f(this.scale);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Tup3fW> T getScale(T res)
	{
		return (T) res.set(this.scale);
	}
	
	public ITransform3f getParent()
	{
		return this.parent;
	}
	
	public String toString()
	{
		return "transform3(position= " + this.position + ", orientation=" + this.orientation + ", size=" + this.scale + ")";
	}
}
