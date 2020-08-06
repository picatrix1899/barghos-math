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
import org.barghos.math.vector.vec3.Vec3;

/**
 * @author picatrix1899
 *
 */
public class LinearSystem3 implements CoordinateSpaceSystem3
{
	private final Vec3 up = new Vec3();
	private final Vec3 forward = new Vec3();
	private final Vec3 right = new Vec3();
	
	public LinearSystem3()
	{
		set();
	}
	
	public LinearSystem3(Tup3fR up, Tup3fR forward, Tup3fR right)
	{
		set(up, forward, right);
	}
	
	public LinearSystem3 set()
	{
		this.up.set(0.0f, 1.0f, 0.0f);
		this.forward.set(0.0f, 0.0f, 1.0f);
		this.right.set(1.0f, 0.0f, 0.0f);
		return this;
	}
	
	public LinearSystem3 set(Tup3fR up, Tup3fR forward, Tup3fR right)
	{
		this.up.set(up);
		this.forward.set(forward);
		this.right.set(right);
		return this;
	}

	public Vec3 getUp(Vec3 res)
	{
		if(res == null) res = new Vec3();
		
		return res.set(this.up);
	}
	
	public Vec3 getUp()
	{
		return new Vec3(this.up);
	}
	
	public Vec3 getForward(Vec3 res)
	{
		if(res == null) res = new Vec3();
		
		return res.set(this.forward);
	}
	
	public Vec3 getForward()
	{
		return new Vec3(this.forward);
	}
	
	public Vec3 getRight(Vec3 res)
	{
		if(res == null) res = new Vec3();
		
		return res.set(this.right);
	}
	
	public Vec3 getRight()
	{
		return new Vec3(this.right);
	}
	
	public Vec3 getDown(Vec3 res)
	{
		return this.up.invert(res);
	}
	
	public Vec3 getDown()
	{
		return this.up.invert(null);
	}
	
	public Vec3 getBack(Vec3 res)
	{
		return this.forward.invert(res);
	}
	
	public Vec3 getBack()
	{
		return this.forward.invert(null);
	}
	
	public Vec3 getLeft(Vec3 res)
	{
		return this.right.invert(res);
	}
	
	public Vec3 getLeft()
	{
		return this.right.invert(null);
	}
	
}
