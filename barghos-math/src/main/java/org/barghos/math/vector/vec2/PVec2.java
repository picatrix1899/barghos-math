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
import org.barghos.core.tuple2.api.Tup2fR;

/**
 * @author picatrix1899
 *
 * Represents a persistent 2-dimensional mathematical vector in euclidean space.
 * This is a readonly version of a 2-dimensional vector with extended protection against modification.
 * It can be used as a more flexible way to create constants.
 */
public abstract class PVec2 implements Vec2R
{
	
	public static PVec2 gen(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return gen(t.getX(), t.getY()); }
	public static PVec2 gen(float x, float y)
	{
		return new PVec2()
		{
			public float getX() { return x; }
			public float getY() { return y; }
		};
	}
	
}
