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

package org.barghos.math.vec2;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2iR;
import org.barghos.core.tuple2.api.Tup2iW;
import org.barghos.math.BarghosMath;
import org.barghos.math.vec2.api.Vec2iR;

/**
 * Represents a simplified 2-dimensional mathematical int vector in euclidean space.
 * This is a bare minimum version without most operations.
 * 
 *  @author picatrix1899
 *  
 *  @since 1.0
 */
public class SimpleVec2i implements Vec2iR, Tup2iW
{
	/**
	 * The x component of the vector.
	 * 
	 * @since 1.0
	 */
	protected int x;
	
	/**
	 * The y component of the vector.
	 * 
	 * @since 1.0
	 */
	protected int y;
	
	/**
	 * Creates a new instance of {@link SimpleVec2i} with the components set to 0.
	 * 
	 * @since 1.0
	 */
	public SimpleVec2i()
	{
		set(0, 0);
	}
	
	/**
	 * Creates a new instance of {@link SimpleVec2i} with the components adopted from t.
	 * 
	 * @param t An instance of {@link Tup2iR} to adopt the components from.
	 * 
	 * @since 1.0
	 */
	public SimpleVec2i(Tup2iR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY());
	}

	/**
	 * Creates a new instance of {@link SimpleVec2i} with the components set to the corresponding parameters.
	 * 
	 * @param x The value for the x component.
	 * @param y The value for the y component.
	 * 
	 * @since 1.0
	 */
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
		this.x = x;
	
		return this;
	}
	
	@Override
	public SimpleVec2i setY(int y)
	{
		this.y = y;
		
		return this;
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
	
	/**
	 * Checks if the components are exactly positive zero and returns the result.
	 * 
	 * @return True if both components are exactly positive zero. False otherwise.
	 * 
	 * @since 1.0
	 */
	public boolean isZero()
	{
		return this.x == 0 && this.y == 0;
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
