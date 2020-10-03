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

package org.barghos.math.vector.vec2;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2iR;
import org.barghos.core.tuple2.api.Tup2iW;
import org.barghos.math.BarghosMath;
import org.barghos.math.vector.vec2.api.Vec2iR;

/**
 * @author picatrix1899
 *
 */
public class SimpleVec2i implements Vec2iR, Tup2iW
{
	protected int x;
	protected int y;
	
	public SimpleVec2i()
	{
		set(0, 0);
	}
	
	public SimpleVec2i(Tup2iR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY());
	}

	public SimpleVec2i(int x, int y)
	{
		set(x, y);
	}
	
	@Override
	public int getX()
	{
		return this.x;
	}
	
	@Override
	public int getY()
	{
		return this.y;
	}
	
	@Override
	public SimpleVec2i setX(int x)
	{
		this.x = x; return this;
	}
	
	@Override
	public SimpleVec2i setY(int y)
	{
		this.y = y; return this;
	}
	
	@Override
	public SimpleVec2i set(Tup2iR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY());
	}
	
	@Override
	public SimpleVec2i set(int value)
	{
		return setX(value).setY(value);
	}
	
	@Override
	public SimpleVec2i set(int x, int y)
	{
		return setX(x).setY(y);
	}
	
	public boolean isZero()
	{
		return this.x == 0.0f && this.y == 0.0f;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + getX();
		result = prime * result + getY();
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof Tup2iR)) return false;
		
		Tup2iR other = (Tup2iR) obj;
		if(getX() != other.getX()) return false;
		if(getY() != other.getY()) return false;
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return "simplevec2i(x=" + this.x + ", y=" + this.y + ")";
	}
	
	@Override
	public SimpleVec2i clone()
	{
		return new SimpleVec2i(this);
	}
}
