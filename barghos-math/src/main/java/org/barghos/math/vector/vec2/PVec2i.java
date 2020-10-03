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
import org.barghos.core.tuple2.api.Tup2dR;
import org.barghos.core.tuple2.api.Tup2iR;
import org.barghos.math.BarghosMath;
import org.barghos.math.vector.vec2.api.Vec2iR;

/**
 * Represents a persistent 2-dimensional mathematical int vector.
 * This is a readonly version of a 2-dimensional mathematical int vector with extended protection against modification.
 * It can be used as a more flexible way to create constants.
 * 
 * <p>
 * This class should not be inherited, not direct by extending nor anonymous,
 * as this would render the protection mechanism used as useless.
 * To get an instance you call one of the static generator methods.
 * </p>
 * 
 * @author picatrix1899
 * 
 * @since 1.0
 */
public abstract class PVec2i implements Vec2iR
{
	
	/**
	 * Generates a new readonly {@link PVec2i} from an existing instance of {@link Tup2iR} and adopts the values.
	 * 
	 * @param t An existing implementation of {@link Tup2iR} to adopt the values from.
	 * 
	 * @return A new readonly {@link PVec2i}.
	 * 
	 * @since 1.0
	 */
	public static PVec2i gen(Tup2iR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return gen(t.getX(), t.getY());
	}
	
	/**
	 * Generates a new readonly {@link PVec2i} with the values set to the corresponding parameters.
	 * 
	 * @param x The x value.
	 * @param y The y value.
	 * 
	 * @return A new readonly {@link PVec2i}.
	 * 
	 * @since 1.0
	 */
	public static PVec2i gen(int x, int y)
	{
		return new PVec2i()
		{
			public int getX() { return x; }
			public int getY() { return y; }
		};
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
		if(!(obj instanceof Tup2dR)) return false;
		
		Tup2dR other = (Tup2dR) obj;
		if(getX() != other.getX()) return false;
		if(getY() != other.getY()) return false;
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return "pvec2i(x=" + getX() + ", y=" + getY() + ")";
	}
}
