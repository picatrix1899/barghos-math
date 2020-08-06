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

import org.barghos.math.Maths;

public enum Vec2Axis implements Vec2R
{
	/** The Zero Vector as constant. */
	ZERO {
		public float getX() { return 0; }
		public float getY() { return 0; }
	},
	
	/** The One Vector as constant. */
	ONE {
		public float getX() { return 1; }
		public float getY() { return 1; }
	},

	/** The positive x-axis normal as constant. */
	AXIS_X {
		public float getX() { return 1; }
		public float getY() { return 0; }
	},
	
	/** The positive y-axis normal as constant. */
	AXIS_Y {
		public float getX() { return 0; }
		public float getY() { return 1; }
	},

	/** The negative x-axis normal as constant. */
	AXIS_NX {
		public float getX() { return 0; }
		public float getY() { return -1; }
	},
	
	/** The negative y-axis normal as constant. */
	AXIS_NY {
		public float getX() { return 0; }
		public float getY() { return -1; }
	},

	/** The diagonal axis normal of positive x and positive y as constant. */
	AXIS_XY {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of negative x and positive y as constant. */
	AXIS_NXY {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of positive x and negative y as constant. */
	AXIS_XNY {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return -Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of negative x and negative y as constant. */
	AXIS_NXNY {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return -Maths.INV_SQRT2f; }
	},
;
}
