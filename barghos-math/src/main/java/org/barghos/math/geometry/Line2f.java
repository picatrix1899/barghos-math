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
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.point.Point2f;
import org.barghos.math.utils.api.ITransform2f;

/**
 * @author picatrix1899
 *
 */
public class Line2f implements FiniteGeometricObject2f
{
	protected final Point2f p1 = new Point2f();
	protected final Point2f p2 = new Point2f();
	
	public Line2f() { }
	
	public Line2f(Line2f l)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
		}
		
		set(l);
	}
	
	public Line2f(Tup2fR p1, Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		set(p1, p2);
	}
	
	public Line2f(float x1, float y1, Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		set(x1, y1, p2);
	}
	
	public Line2f(Tup2fR p1, float x2, float y2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		set(p1, x2, y2);
	}
	
	public Line2f(float x1, float y1, float x2, float y2)
	{
		set(x1, y1, x2, y2);
	}
	
	public Line2f set(Line2f l)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
		}
		
		l.getP1(this.p1);
		l.getP2(this.p2);
		
		return this;
	}
	
	public Line2f set(Tup2fR p1, Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		return setP1(p1).setP2(p2);
	}
	
	public Line2f set(float x1, float y1, Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		return setP1(x1, y1).setP2(p2);
	}
	
	public Line2f set(Tup2fR p1, float x2, float y2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		return setP1(p1).setP2(x2, y2);
	}
	
	public Line2f set(float x1, float y1,float x2, float y2)
	{
		return setP1(x1, y1).setP2(x2, y2);
	}
	
	public Line2f setP1(Tup2fR p1)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p1 == null) throw new ArgumentNullException("p1");
		}
		
		this.p1.set(p1);
		
		return this;
	}
	
	public Line2f setP1(float x, float y)
	{
		this.p1.set(x, y);
		
		return this;
	}
	
	public Line2f setP2(Tup2fR p2)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(p2 == null) throw new ArgumentNullException("p2");
		}
		
		this.p2.set(p2);
		
		return this;
	}
	
	public Line2f setP2(float x, float y)
	{
		this.p2.set(x, y);
		
		return this;
	}
	
	public Point2f getP1()
	{
		return this.p1.clone();
	}
	
	public Point2f getP1(@Nullable Point2f res)
	{
		if(res == null) res = new Point2f();
		
		return res.set(this.p1);
	}
	
	public Point2f getP2()
	{
		return this.p2.clone();
	}
	
	public Point2f getP2(@Nullable Point2f res)
	{
		if(res == null) res = new Point2f();
		
		return res.set(this.p2);
	}
	
	public float getP1X()
	{
		return this.p1.getX();
	}
	
	public float getP1Y()
	{
		return this.p1.getY();
	}

	public float getP2X()
	{
		return this.p2.getX();
	}
	
	public float getP2Y()
	{
		return this.p2.getY();
	}

	public Line2f transform(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return transform(t, this);
	}
	
	public Line2f transform(ITransform2f t, Line2f l)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(l == null) throw new ArgumentNullException("l");
		}
		
		Mat3f m = Mat3f.transform2D(t);
		
		Point2f p1 = this.p1.clone();
		Point2f p2 = this.p2.clone();
		
		m.transform(p1);
		m.transform(p2);
		
		return l.set(p1, p2);
	}
	
	public String toString()
	{
		return "line2f(p1=" + this.p1 + ", p2=" + this.p2 + ")";
	}

	public Point2f[] getPoints()
	{
		return new Point2f[]{ this.p1.clone(), this.p2.clone() };
	}
}
