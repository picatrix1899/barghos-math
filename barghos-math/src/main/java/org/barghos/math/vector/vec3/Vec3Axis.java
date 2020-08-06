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

package org.barghos.math.vector.vec3;

import org.barghos.math.Maths;

public enum Vec3Axis implements Vec3R
{
	/** The Zero Vector as constant. */
	ZERO {
		public float getX() { return 0; }
		public float getY() { return 0; }
		public float getZ() { return 0; }
	},
	
	/** The One Vector as constant. */
	ONE {
		public float getX() { return 1; }
		public float getY() { return 1; }
		public float getZ() { return 1; }
	},
	
	/** The positive x-axis normal as constant. */
	AXIS_X {
		public float getX() { return 1; }
		public float getY() { return 0; }
		public float getZ() { return 0; }
	},

	/** The positive y-axis normal as constant. */
	AXIS_Y {
		public float getX() { return 0; }
		public float getY() { return 1; }
		public float getZ() { return 0; }
	},
	
	/** The positive z-axis normal as constant. */
	AXIS_Z {
		public float getX() { return 0; }
		public float getY() { return 0; }
		public float getZ() { return 1; }
	},

	/** The negative x-axis normal as constant. */
	AXIS_NX {
		public float getX() { return -1; }
		public float getY() { return 0; }
		public float getZ() { return 0; }
	},
	
	/** The negative y-axis normal as constant.  */
	AXIS_NY {
		public float getX() { return 0; }
		public float getY() { return -1; }
		public float getZ() { return 0; }
	},
	
	/** The negative z-axis normal as constant.  */
	AXIS_NZ {
		public float getX() { return 0; }
		public float getY() { return 0; }
		public float getZ() { return -1; }
	},

	/** The diagonal axis normal of positive x and positive y as constant. */
	AXIS_XY {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return Maths.INV_SQRT2f; }
		public float getZ() { return 0; }
	},
	
	/** The diagonal axis normal of positive x and positive z as constant. */
	AXIS_XZ {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return 0; }
		public float getZ() { return Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of positive y and positive z as constant. */
	AXIS_YZ {
		public float getX() { return 0; }
		public float getY() { return Maths.INV_SQRT2f; }
		public float getZ() { return Maths.INV_SQRT2f; }
	},

	/** The diagonal axis normal of negative x and positive y as constant. */
	AXIS_NXY {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return Maths.INV_SQRT2f; }
		public float getZ() { return 0; }
	},
	
	/** The diagonal axis normal of negative x and positive z as constant. */
	AXIS_NXZ {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return 0; }
		public float getZ() { return Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of negative y and positive z as constant. */
	AXIS_NYZ {
		public float getX() { return 0; }
		public float getY() { return -Maths.INV_SQRT2f; }
		public float getZ() { return Maths.INV_SQRT2f; }
	},

	/** The diagonal axis normal of positive x and negative y as constant. */
	AXIS_XNY {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return -Maths.INV_SQRT2f; }
		public float getZ() { return 0; }
	},
	
	/** The diagonal axis normal of positive x and negative z as constant. */
	AXIS_XNZ {
		public float getX() { return Maths.INV_SQRT2f; }
		public float getY() { return 0; }
		public float getZ() { return -Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of positive x and negative y as constant. */
	AXIS_YNZ {
		public float getX() { return 0; }
		public float getY() { return Maths.INV_SQRT2f; }
		public float getZ() { return -Maths.INV_SQRT2f; }
	},

	/** The diagonal axis normal of negative x and negative y as constant. */
	AXIS_NXNY {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return -Maths.INV_SQRT2f; }
		public float getZ() { return 0; }
	},
	
	/** The diagonal axis normal of negative x and negative z as constant. */
	AXIS_NXNZ {
		public float getX() { return -Maths.INV_SQRT2f; }
		public float getY() { return 0; }
		public float getZ() { return -Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of negative y and negative y as constant. */
	AXIS_NYNZ {
		public float getX() { return 0; }
		public float getY() { return -Maths.INV_SQRT2f; }
		public float getZ() { return -Maths.INV_SQRT2f; }
	},
	
	/** The diagonal axis normal of positive x, positive y and positive z as constant. */
	AXIS_XYZ {
		public float getX() { return Maths.INV_SQRT3f; }
		public float getY() { return Maths.INV_SQRT3f; }
		public float getZ() { return Maths.INV_SQRT3f; }
	},
	
	/** The diagonal axis normal of negative x, positive y and positive z as constant. */
	AXIS_NXYZ {
		public float getX() { return -Maths.INV_SQRT3f; }
		public float getY() { return Maths.INV_SQRT3f; }
		public float getZ() { return Maths.INV_SQRT3f; }
	},
	
	/** The diagonal axis normal of positive x, negative y and positive z as constant. */
	AXIS_XNYZ {
		public float getX() { return Maths.INV_SQRT3f; }
		public float getY() { return -Maths.INV_SQRT3f; }
		public float getZ() { return Maths.INV_SQRT3f; }
	},
	
	/** The diagonal axis normal of positive x, positive y and negative z as constant. */
	AXIS_XYNZ {
		public float getX() { return Maths.INV_SQRT3f; }
		public float getY() { return Maths.INV_SQRT3f; }
		public float getZ() { return -Maths.INV_SQRT3f; }
	},

	/** The diagonal axis normal of negative x, negative y and positive z as constant. */
	AXIS_NXNYZ {
		public float getX() { return -Maths.INV_SQRT3f; }
		public float getY() { return -Maths.INV_SQRT3f; }
		public float getZ() { return Maths.INV_SQRT3f; }
	},
	
	/** The diagonal axis normal of negative x, positive y and negative z as constant. */
	AXIS_NXYNZ {
		public float getX() { return -Maths.INV_SQRT3f; }
		public float getY() { return Maths.INV_SQRT3f; }
		public float getZ() { return -Maths.INV_SQRT3f; }
	},
	
	/** The diagonal axis normal of positive x, negative y and negative z as constant. */
	AXIS_XNYNZ {
		public float getX() { return Maths.INV_SQRT3f; }
		public float getY() { return -Maths.INV_SQRT3f; }
		public float getZ() { return -Maths.INV_SQRT3f; }
	},

	/** The diagonal axis normal of negative x, negative y and negative z as constant. */
	AXIS_NXNYNZ {
		public float getX() { return -Maths.INV_SQRT3f; }
		public float getY() { return -Maths.INV_SQRT3f; }
		public float getZ() { return -Maths.INV_SQRT3f; }
	},
;
}
