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

package org.barghos.math.matrix;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author picatrix1899
 *
 */
public class Mat3fStack
{
	private Deque<Mat3f> stack = new ArrayDeque<>();
	private Mat3f result;
	private boolean isDirty;
	
	
	public void push(Mat3f mat)
	{
		this.stack.push(mat);
		this.isDirty = true;
	}
	
	public void pop()
	{
		this.stack.pop();
		this.isDirty = true;
	}
	
	public Mat3f get()
	{
		if(stack.size() == 0) return Mat3f.identity();
		
		if(this.isDirty)
		{
			Mat3f result = Mat3f.identity();
			
			for(Iterator<Mat3f> it = stack.descendingIterator(); it.hasNext();)
			{
				Mat3f.mul(it.next(), result, result);
			}
			
			this.result = result;
			this.isDirty = false;
		}

		return this.result.clone();
	}
}
