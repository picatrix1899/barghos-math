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

package org.barghos.math.geometry;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;

import org.barghos.math.BarghosMath;
import org.barghos.math.point.Point2f;

/**
 * @author picatrix1899
 *
 */
public class Triangle2f
{
	protected final Point2f p1 = new Point2f();
	protected final Point2f p2 = new Point2f();
	protected final Point2f p3 = new Point2f();
	
	public Triangle2f() { }
	
	public Triangle2f(Triangle2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t);
	}

	public Triangle2f(Tup2fR p1, Tup2fR p2, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		set(p1, p2, p3);
	}
	
	public Triangle2f(Tup2fR p1, Tup2fR p2, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		set(p1, p2, p3X, p3Y);
	}
	
	public Triangle2f(Tup2fR p1, float p2X, float p2Y, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		set(p1, p2X, p2Y, p3);
	}
	
	public Triangle2f(float p1X, float p1Y, Tup2fR p2, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		set(p1X, p1Y, p2, p3);
	}
	
	public Triangle2f(Tup2fR p1, float p2X, float p2Y, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		set(p1, p2X, p2Y, p3X, p3Y);
	}
	
	public Triangle2f(float p1X, float p1Y, Tup2fR p2, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		set(p1X, p1Y, p2, p3X, p3Y);
	}
	
	public Triangle2f(float p1X, float p1Y, float p2X, float p2Y, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		set(p1X, p1Y, p2X, p2Y, p3);
	}
	
	public Triangle2f(float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y)
	{
		set(p1X, p1Y, p2X, p2Y, p3X, p3Y);
	}
	
	public Triangle2f set(Triangle2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		t.getP1(this.p1);
		t.getP2(this.p2);
		t.getP3(this.p3);
		
		return this;
	}
	
	public Triangle2f set(Tup2fR p1, Tup2fR p2, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		return setP1(p1).setP2(p2).setP3(p3);
	}
	
	public Triangle2f set(Tup2fR p1, Tup2fR p2, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		return setP1(p1).setP2(p2).setP3(p3X, p3Y);
	}
	
	public Triangle2f set(Tup2fR p1, float p2X, float p2Y, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		return setP1(p1).setP2(p2X, p2Y).setP3(p3);
	}
	
	public Triangle2f set(float p1X, float p1Y, Tup2fR p2, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		return setP1(p1X, p1Y).setP2(p2).setP3(p3);
	}
	
	public Triangle2f set(Tup2fR p1, float p2X, float p2Y, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		return setP1(p1).setP2(p2X, p2Y).setP3(p3X, p3Y);
	}
	
	public Triangle2f set(float p1X, float p1Y, Tup2fR p2, float p3X, float p3Y)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		return setP1(p1X, p1Y).setP2(p2).setP3(p3X, p3Y);
	}
	
	public Triangle2f set(float p1X, float p1Y, float p2X, float p2Y, Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		return setP1(p1X, p1Y).setP2(p2X, p2Y).setP3(p3);
	}
	
	public Triangle2f set(float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y)
	{
		return setP1(p1X, p1Y).setP2(p2X, p2Y).setP3(p3X, p3Y);
	}
	
	public Triangle2f setP1(Tup2fR p1)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		this.p1.set(p1);
		
		return this;
	}
	
	public Triangle2f setP1(float p1X, float p1Y)
	{
		this.p1.set(p1X, p1Y);
		
		return this;
	}
	
	public Triangle2f setP2(Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		this.p2.set(p2);
		
		return this;
	}
		
	public Triangle2f setP2(float p2X, float p2Y)
	{
		this.p2.set(p2X, p2Y);
		
		return this;
	}
	
	public Triangle2f setP3(Tup2fR p3)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p3 == null) throw new ArgumentNullException("p3");
		}
		
		this.p3.set(p3);
		
		return this;
	}
	
	public Triangle2f setP3(float p3X, float p3Y)
	{
		this.p3.set(p3X, p3Y);
		
		return this;
	}
	
	public Point2f getP1()
	{
		return this.p1.clone();
	}
	
	public Point2f getP1(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.p1);
	}
	
	public Point2f getP2()
	{
		return this.p2.clone();
	}
	
	public Point2f getP2(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.p2);
	}
	
	public Point2f getP3()
	{
		return this.p3.clone();
	}
	
	public Point2f getP3(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.p3);
	}
	
	public String toString()
	{
		return "triangle2f(p1=" + this.p1 + ", p2=" + this.p2 + ", p3=" + this.p3 + ")";
	}
	
	public Triangle2f clone()
	{
		return new Triangle2f(this);
	}
}
