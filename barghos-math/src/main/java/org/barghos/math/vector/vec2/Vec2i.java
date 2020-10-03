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
import org.barghos.math.Maths;

/**
 * Represents a 2-dimensional mathematical float vector in euclidean space.
 * This is a full featured version with common operations.
 * 
 *  @author picatrix1899
 */
public class Vec2i extends SimpleVec2i
{
		public Vec2i() { set(0, 0); }
		
		public Vec2i(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			set(t.getX(), t.getY());
		}

		public Vec2i(int x, int y) { set(x, y); }
		
		@Override
		public Vec2i setX(int x) { super.setX(x); return this; }
		
		@Override
		public Vec2i setY(int y) { super.setY(y); return this; }
		
		@Override
		public Vec2i set(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			super.set(t);
			
			return this;
		}
		
		@Override
		public Vec2i set(int value) { return setX(value).setY(value); }
		
		@Override
		public Vec2i set(int x, int y) { super.set(x, y); return this; }

		public Vec2i add(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return add(t.getX(), t.getY());
		}
		public Vec2i add(int scalar) { return add(scalar, scalar); }
		public Vec2i add(int x, int y) { return set(this.x + x, this.y + y); }
		
		public <T extends Tup2iW> T add(Tup2iR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2iW> T add(int scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T add(int x, int y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x + x, this.y + y);
		}
		
		public Vec2i sub(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return sub(t.getX(), t.getY());
		}
		public Vec2i sub(int scalar) { return sub(scalar, scalar); }
		public Vec2i sub(int x, int y) { return set(this.x - x, this.y - y); }
		
		public <T extends Tup2iW> T sub(Tup2iR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2iW> T sub(int scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T sub(int x, int y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x - x, this.y - y);
		}

		public Vec2i mul(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return mul(t.getX(), t.getY());
		}
		
		public Vec2i mul(int scalar) { return mul(scalar, scalar); }
		public Vec2i mul(int x, int y) { return set(this.x * x, this.y * y); }
		
		public <T extends Tup2iW> T mul(Tup2iR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(x, y, res);
		}
		
		public <T extends Tup2iW> T mul(int scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T mul(int x, int y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x * x, this.y * y);
		}

		public Vec2i div(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return div(t.getX(), t.getY());
		}
		public Vec2i div(int scalar) { return div(scalar, scalar); }
		public Vec2i div(int x, int y) { return set(this.x / x, this.y / y); }
		
		public <T extends Tup2iW> T div(Tup2iR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2iW> T div(int scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T div(int x, int y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x / x, this.y / y);
		}

		public float dot(Tup2iR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return dot(t.getX(), t.getY());
		}
		public float dot(int x, int y) { return this.x * x + this.y * y; }

		public Vec2i snapToGrid(Tup2iR grid)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
			}
			
			return snapToGrid(grid.getX(), grid.getY());
		}
		
		public Vec2i snapToGrid(int gx, int gy) { return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy)); }
		public <T extends Tup2iW> T snapToGrid(Tup2iR grid, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return snapToGrid(grid.getX(), grid.getY(), res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T snapToGrid(int gx, int gy, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy));
		}

		public Vec2i invert() { return set(-this.x, -this.y); }
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2iW> T invert(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			return (T)res.set(-this.x, -this.y);
		}
		
		@Override
		public String toString()
		{
			return "vec2i(x=" + this.x + ", y=" + this.y + ")";
		}
		
		@Override
		public Vec2i clone()
		{
			return new Vec2i(this);
		}
}
