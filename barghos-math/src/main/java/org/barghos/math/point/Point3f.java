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

package org.barghos.math.point;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.math.vec3.Vec3f;

public class Point3f extends Vec3f
{
	public Point3f() { super(); }
	public Point3f(Tup3fR t) { super(t); }
	public Point3f(float x, float y, float z) { super(x, y, z); }
	
	public Point3f setX(float x) { super.setX(x); return this; }
	public Point3f setY(float y) { super.setY(y); return this; }
	public Point3f setZ(float z) { super.setZ(z); return this; }
	
	public Point3f set(Tup3fR t) { if(t == null) throw new ArgumentNullException("t"); super.set(t.getX(), t.getY(), t.getZ()); return this; }
	public Point3f set(float value) { super.set(value); return this; }
	public Point3f set(float x, float y, float z) { super.set(x, y, z); return this; }

	public String toString()
	{
		return "point3(" + this.x + "f, " + this.y +"f, " + this.z + "f)";
	}

	public Point3f clone()
	{
		return new Point3f(this);
	}

	@Override
	public Point3f add(Tup3fR t)
	{
		super.add(t);
		return this;
	}
	
	@Override
	public Point3f add(float scalar)
	{
		super.add(scalar);
		return this;
	}
	
	@Override
	public Point3f add(float x, float y, float z)
	{
		super.add(x, y, z);
		return this;
	}
	
	@Override
	public Point3f addN(Tup3fR t)
	{
		return (Point3f)super.addN(t);
	}
	
	@Override
	public Point3f addN(float scalar)
	{
		return (Point3f)super.addN(scalar);
	}
	
	@Override
	public Point3f addN(float x, float y, float z)
	{
		return (Point3f)super.addN(x, y, z);
	}
	
	@Override
	public Point3f sub(Tup3fR t)
	{
		super.sub(t);
		return this;
	}
	
	@Override
	public Point3f sub(float scalar)
	{
		super.sub(scalar);
		return this;
	}
	
	@Override
	public Point3f sub(float x, float y, float z)
	{
		super.sub(x, y, z);
		return this;
	}
	
	@Override
	public Point3f subN(Tup3fR t)
	{
		return (Point3f)super.subN(t);
	}
	
	@Override
	public Point3f subN(float scalar)
	{
		return (Point3f)super.subN(scalar);
	}
	
	@Override
	public Point3f subN(float x, float y, float z)
	{
		return (Point3f)super.subN(x, y, z);
	}
	
	@Override
	public Point3f mul(Tup3fR t)
	{
		super.mul(t);
		return this;
	}
	
	@Override
	public Point3f mul(float scalar)
	{
		super.mul(scalar);
		return this;
	}
	
	@Override
	public Point3f mul(float x, float y, float z)
	{
		super.mul(x, y, z);
		return this;
	}
	
	@Override
	public Point3f mulN(Tup3fR t)
	{
		return (Point3f)super.mulN(t);
	}
	
	@Override
	public Point3f mulN(float scalar)
	{
		return (Point3f)super.mulN(scalar);
	}
	
	@Override
	public Point3f mulN(float x, float y, float z)
	{
		return (Point3f)super.mulN(x, y, z);
	}
	
	@Override
	public Point3f div(Tup3fR t)
	{
		super.div(t);
		return this;
	}
	
	@Override
	public Point3f div(float scalar)
	{
		super.div(scalar);
		return this;
	}
	
	@Override
	public Point3f div(float x, float y, float z)
	{
		super.div(x, y, z);
		return this;
	}
	
	@Override
	public Point3f divN(Tup3fR t)
	{
		return (Point3f)super.divN(t);
	}
	
	@Override
	public Point3f divN(float scalar)
	{
		return (Point3f)super.divN(scalar);
	}
	
	@Override
	public Point3f divN(float x, float y, float z)
	{
		return (Point3f)super.divN(x, y, z);
	}
	
	@Override
	public Point3f normal()
	{
		super.normal();
		return this;
	}
	
	@Override
	public Point3f normalN()
	{
		return (Point3f)super.normalN();
	}
	
	@Override
	public Point3f normalSafe()
	{
		super.normalSafe();
		return this;
	}
	
	@Override
	public Point3f normalSafe(float tolerance)
	{
		super.normalSafe(tolerance);
		return this;
	}
	
	@Override
	public Point3f normalSafeN()
	{
		return (Point3f)super.normalSafeN();
	}
	
	@Override
	public Point3f normalSafeN(float tolerance)
	{
		return (Point3f)super.normalSafeN(tolerance);
	}
	
	@Override
	public Point3f invert()
	{
		super.invert();
		return this;
	}

	@Override
	public Point3f invertN()
	{
		return (Point3f)super.invertN();
	}
	
	@Override
	public Point3f cross(Tup3fR t)
	{
		super.cross(t);
		return this;
	}
	
	@Override
	public Point3f cross(float x, float y, float z)
	{
		super.cross(x, y, z);
		return this;
	}

	@Override
	public Point3f crossN(Tup3fR t)
	{
		return (Point3f)super.crossN(t);
	}
	
	@Override
	public Point3f crossN(float x, float y, float z)
	{
		return (Point3f)super.crossN(x, y, z);
	}
	
	@Override
	public Point3f snapToGrid(Tup3fR grid)
	{
		super.snapToGrid(grid);
		return this;
	}
	
	@Override
	public Point3f snapToGrid(float scalar)
	{
		super.snapToGrid(scalar);
		return this;
	}
	
	@Override
	public Point3f snapToGrid(float gx, float gy, float gz)
	{
		super.snapToGrid(gx, gy, gz);
		return this;
	}
	
	@Override
	public Point3f snapToGridN(Tup3fR grid)
	{
		return (Point3f)super.snapToGridN(grid);
	}
	
	@Override
	public Point3f snapToGridN(float scalar)
	{
		return (Point3f)super.snapToGridN(scalar);
	}
	
	@Override
	public Point3f snapToGridN(float gx, float gy, float gz)
	{
		return (Point3f)super.snapToGridN(gx, gy, gz);
	}
}
