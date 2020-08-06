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

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.math.point.Point2;

/**
 * @author picatrix1899
 *
 */
public class Line2
{
	protected final Point2 p1 = new Point2();
	protected final Point2 p2 = new Point2();
	
	public Line2() { }
	
	public Line2(Line2 l)
	{
		set(l);
	}
	
	public Line2(Tup2fR p1, Tup2fR p2)
	{
		set(p1, p2);
	}
	
	public Line2(float x1, float y1, Tup2fR p2)
	{
		set(x1, y1, p2);
	}
	
	public Line2(Tup2fR p1, float x2, float y2)
	{
		set(p1, x2, y2);
	}
	
	public Line2(float x1, float y1, float x2, float y2)
	{
		set(x1, y1, x2, y2);
	}
	
	public Line2 set(Line2 l)
	{
		l.getP1(this.p1);
		l.getP2(this.p2);
		return this;
	}
	
	public Line2 set(Tup2fR p1, Tup2fR p2)
	{
		return setP1(p1).setP2(p2);
	}
	
	public Line2 set(float x1, float y1, Tup2fR p2)
	{
		return setP1(x1, y1).setP2(p2);
	}
	
	public Line2 set(Tup2fR p1, float x2, float y2)
	{
		return setP1(p1).setP2(x2, y2);
	}
	
	public Line2 set(float x1, float y1,float x2, float y2)
	{
		return setP1(x1, y1).setP2(x2, y2);
	}
	
	public Line2 setP1(Tup2fR p1)
	{
		this.p1.set(p1); return this;
	}
	
	public Line2 setP1(float x, float y)
	{
		this.p1.set(x, y); return this;
	}
	
	public Line2 setP2(Tup2fR p2)
	{
		this.p2.set(p2); return this;
	}
	
	public Line2 setP2(float x, float y)
	{
		this.p2.set(x, y); return this;
	}
	
	public Point2 getP1()
	{
		return new Point2(this.p1);
	}
	
	public Point2 getP1(Point2 res)
	{
		if(res == null) res = new Point2();
		return res.set(this.p1);
	}
	
	public Point2 getP2()
	{
		return new Point2(this.p2);
	}
	
	public Point2 getP2(Point2 res)
	{
		if(res == null) res = new Point2();
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

	public String toString()
	{
		return "line2(p1=" + this.p1 + ", p2=" + this.p2 + ")";
	}
}
