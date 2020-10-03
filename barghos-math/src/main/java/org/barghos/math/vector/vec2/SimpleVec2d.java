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
import org.barghos.core.tuple2.api.Tup2dW;
import org.barghos.math.BarghosMath;
import org.barghos.math.vector.vec2.api.Vec2dR;

/**
 * @author picatrix1899
 *
 */
public class SimpleVec2d implements Vec2dR, Tup2dW
{
		protected double x;
		protected double y;
		
		public SimpleVec2d() { set(0.0, 0.0); }
		
		public SimpleVec2d(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			set(t.getX(), t.getY());
		}

		public SimpleVec2d(double x, double y) { set(x, y); }
		
		@Override
		public double getX() { return this.x; }
		
		@Override
		public double getY() { return this.y; }
		
		@Override
		public SimpleVec2d setX(double x) { this.x = x; return this; }
		
		@Override
		public SimpleVec2d setY(double y) { this.y = y; return this; }
		
		@Override
		public SimpleVec2d set(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return set(t.getX(), t.getY());
		}
		
		@Override
		public SimpleVec2d set(double value) { return setX(value).setY(value); }
		
		@Override
		public SimpleVec2d set(double x, double y) { return setX(x).setY(y); }
		
		public boolean isZero() { return this.x == 0.0 && this.y == 0.0; }
		public boolean isZero(double tolerance) { return (Math.abs(this.x) <= tolerance) && (Math.abs(this.y) <= tolerance); }
		
		public boolean isFinite() { return Double.isFinite(this.x) && Double.isFinite(this.y); }
		
		@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp = Double.doubleToLongBits(getX());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getY());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

		@Override
		public boolean equals(Object obj)
		{
			if(this == obj) return true;
			if(obj == null) return false;
			if(!(obj instanceof Tup2dR)) return false;
			
			Tup2dR other = (Tup2dR) obj;
			if(Double.doubleToLongBits(getX()) != Double.doubleToLongBits(other.getX())) return false;
			if(Double.doubleToLongBits(getY()) != Double.doubleToLongBits(other.getY())) return false;
			
			return true;
		}
		
		@Override
		public String toString()
		{
			return "simpleVec2d(x=" + this.x + ", y=" + this.y + ")";
		}
		
		@Override
		public SimpleVec2d clone()
		{
			return new SimpleVec2d(this);
		}
}
