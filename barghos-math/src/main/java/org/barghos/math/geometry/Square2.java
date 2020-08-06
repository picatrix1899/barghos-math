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
import org.barghos.core.util.Nullable;
import org.barghos.math.point.Point2;

/**
 * @author picatrix1899
 *
 */
public class Square2
{
	protected final Point2 center = new Point2();
	protected float halfExtend;
	
	public Square2()
	{
		set(0.0f, 0.0f, 1.0f);
	}
	
	public Square2(Square2 s)
	{
		set(s);
	}
	
	public Square2(Tup2fR center, float halfExtend)
	{
		set(center, halfExtend);
	}

	public Square2(float x, float y, float halfExtend)
	{
		set(x, y, halfExtend);
	}
	
	public Square2 set(Square2 s)
	{
		s.getCenter(this.center);
		setHalfExtend(s.getHalfExtend());
		return this;
	}
	
	public Square2 set(Tup2fR center, float halfExtend)
	{
		return setCenter(center).setHalfExtend(halfExtend);
	}

	public Square2 set(float x, float y, float halfExtend)
	{
		return setCenter(x, y).setHalfExtend(halfExtend);
	}
	
	public Square2 setCenter(Tup2fR center)
	{
		this.center.set(center); return this;
	}

	public Square2 setCenter(float x, float y)
	{
		this.center.set(x, y); return this;
	}
	
	public Square2 setHalfExtend(float halfExtend)
	{
		this.halfExtend = halfExtend; return this;
	}
	
	public Point2 getCenter()
	{
		return new Point2(this.center);
	}
	
	public Point2 getCenter(@Nullable Point2 res)
	{
		if(res == null) res = new Point2();
		return res.set(this.center);
	}
	
	public float getHalfExtend()
	{
		return this.halfExtend;
	}
	
	public String toString()
	{
		return "square2(center=" + this.center + ", halfExtend=" + this.halfExtend + "f)";
	}
}
