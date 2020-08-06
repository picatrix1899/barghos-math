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

import org.barghos.core.tuple2.Tup2fHelper;
import org.barghos.math.point.Point2;
import org.barghos.math.vector.vec2.Vec2;
import org.barghos.math.vector.vec2.Vec2Pool;

/**
 * @author picatrix1899
 *
 */
public class Area2
{
	protected final Point2 center = new Point2();
	protected final Vec2 halfExtend = new Vec2();
	
	public Area2(Point2 min, Point2 max)
	{
		set(min, max);
	}
	
	public Area2(Point2 center, Vec2 halfExtend)
	{
		set(center, halfExtend);
	}
	
	public Area2 set(Point2 min, Point2 max)
	{
		Vec2 extend = Vec2Pool.get();
		max.sub(min, extend);
		
		this.halfExtend.set(extend.mul(0.5f, extend));
		this.center.set(min.add(this.halfExtend, extend));
		
		Vec2Pool.store(extend);
		
		return this;
	}
	
	public Area2 set(Point2 center, Vec2 halfExtend)
	{
		this.center.set(center);
		this.halfExtend.set(halfExtend);
		
		return this;
	}
	
	public Point2 getCenter(Point2 res)
	{
		if(res == null) res = new Point2();
		
		res.set(this.center);
		
		return res;
	}
	
	public Vec2 getHalfExtend(Vec2 res)
	{
		if(res == null) res = new Vec2();
		
		res.set(this.halfExtend);
		
		return res;
	}
	
	public Point2 getMin(Point2 res)
	{
		if(res == null) res = new Point2();
		
		this.center.sub(this.halfExtend, res);
		
		return res;
	}
	
	public Point2 getMax(Point2 res)
	{
		if(res == null) res = new Point2();
		
		this.center.add(this.halfExtend, res);
		
		return res;
	}
	
	public boolean isPointInside(Vec2 point)
	{
		Vec2 d = Vec2Pool.get();
		Tup2fHelper.abs(point.sub(this.center, d), d);
		
		boolean r = d.getX() <= this.halfExtend.getX() && d.getY() <= this.halfExtend.getY();
		
		Vec2Pool.store(d);
		
		return r;
	}
}
