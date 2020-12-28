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
import org.barghos.math.point.Point3f;
import org.barghos.math.vec3.Vec3f;

/**
 * @author picatrix1899
 *
 */
public class LinearSystem3 implements CoordinateSpaceSystem3
{
	private final Vec3f up = new Vec3f();
	private final Vec3f forward = new Vec3f();
	private final Vec3f right = new Vec3f();
	private final Point3f origin = new Point3f();
	
	public LinearSystem3()
	{
		set();
	}
	
	public LinearSystem3(Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		set(forward, right, up);
	}
	
	public LinearSystem3 set()
	{
		this.up.set(0.0f, 1.0f, 0.0f);
		this.forward.set(0.0f, 0.0f, 1.0f);
		this.right.set(1.0f, 0.0f, 0.0f);
		return this;
	}
	
	public LinearSystem3 set(Tup3fR forward, Tup3fR right, Tup3fR up)
	{
		this.forward.set(forward);
		this.right.set(right);
		this.up.set(up);
		
		return this;
	}

	public Vec3f getUp(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		
		return res.set(this.up);
	}
	
	public Vec3f getUp()
	{
		return new Vec3f(this.up);
	}
	
	public Vec3f getForward(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		
		return res.set(this.forward);
	}
	
	public Vec3f getForward()
	{
		return new Vec3f(this.forward);
	}
	
	public Vec3f getRight(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		
		return res.set(this.right);
	}
	
	public Vec3f getRight()
	{
		return new Vec3f(this.right);
	}
	
	public Vec3f getDown(Vec3f res)
	{
		return this.up.invert(res);
	}
	
	public Vec3f getDown()
	{
		return this.up.invert(null);
	}
	
	public Vec3f getBack(Vec3f res)
	{
		return this.forward.invert(res);
	}
	
	public Vec3f getBack()
	{
		return this.forward.invert(null);
	}
	
	public Vec3f getLeft(Vec3f res)
	{
		return this.right.invert(res);
	}
	
	public Vec3f getLeft()
	{
		return this.right.invert(null);
	}
	
	public LinearSystem3 invert()
	{
		this.forward.invert();
		this.right.invert();
		this.up.invert();
		
		return this;
	}
	
	public String toString()
	{
		return "linearSystem3(forward=" + this.forward + ", right=" + this.right + ", up=" + this.up + ")";
	}
}
