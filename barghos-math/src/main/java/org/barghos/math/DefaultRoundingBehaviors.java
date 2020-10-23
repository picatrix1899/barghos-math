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

package org.barghos.math;

/**
 * @author picatrix1899
 *
 */
public enum DefaultRoundingBehaviors implements RoundingBehavoir
{
	ROUND {
		public int roundi(double value) { return (int)Math.round(value); }
		public long roundl(double value) { return Math.round(value); }
		public float roundf(double value) { return (float) Math.round(value); }
		public double roundd(double value) { return (double) Math.round(value); }
		
		public int roundi(float value) { return (int)Math.round(value); }
		public long roundl(float value) { return Math.round(value); }
		public float roundf(float value) { return (float) Math.round(value); }
		public double roundd(float value) { return (double) Math.round(value); }
	},
	FLOOR {
		public int roundi(double value) { return (int)Math.floor(value); }
		public long roundl(double value) { return (long)Math.floor(value); }
		public float roundf(double value) { return (float) Math.floor(value); }
		public double roundd(double value) { return Math.floor(value); }
		
		public int roundi(float value) { return (int)Math.floor(value); }
		public long roundl(float value) { return (long)Math.floor(value); }
		public float roundf(float value) { return (float) Math.floor(value); }
		public double roundd(float value) { return Math.floor(value); }
	},
	CEIL {
		public int roundi(double value) { return (int)Math.ceil(value); }
		public long roundl(double value) { return (long)Math.ceil(value); }
		public float roundf(double value) { return (float) Math.ceil(value); }
		public double roundd(double value) { return Math.ceil(value); }
		
		public int roundi(float value) { return (int)Math.ceil(value); }
		public long roundl(float value) { return (long)Math.ceil(value); }
		public float roundf(float value) { return (float) Math.ceil(value); }
		public double roundd(float value) { return Math.ceil(value); }
	},
	INT {
		public int roundi(double value) { return (int)value; }
		public long roundl(double value) { return (long)value; }
		public float roundf(double value) { return (float)((int)value); }
		public double roundd(double value) { return (double)((long)value); }
		
		public int roundi(float value) { return (int)(value); }
		public long roundl(float value) { return (long)(value); }
		public float roundf(float value) { return (float)((int)value); }
		public double roundd(float value) { return (double)((long)value); }
	};


}
