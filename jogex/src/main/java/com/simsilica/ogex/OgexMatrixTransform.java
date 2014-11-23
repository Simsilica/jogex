/*
 * $Id: OgexMatrixTransform.java 3827 2014-11-23 08:13:36Z pspeed $
 * 
 * Copyright (c) 2014, Simsilica, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the 
 *    distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.simsilica.ogex;


/**
 *
 *
 *  @author    Paul Speed
 */
public class OgexMatrixTransform implements OgexTransform {
    private float[] matrix;
    private boolean object;
    
    public OgexMatrixTransform() {
    }
 
    @Override
    public void setObject( boolean b ) {
        this.object = b;
    }
    
    @Override
    public boolean isObject() {
        return object;
    }
    
    public void setMatrix( float[] matrix ) {
        this.matrix = matrix;
    }
    
    public float[] getMatrix() {
        return matrix;
    }
 
    @Override
    public float[] toMatrix() {
        return matrix.clone();
    }
 
    /**
     *  Converts from row major to column major or reverse
     *  in place.  The passed array is returned again.
     */   
    public static float[] transpose( float[] array ) {
        for( int i = 0; i < 4; i++ ) {
            for( int j = 0; j < i; j++ ) {
                // Swap row, col for col, row
                float swap = array[i * 4 + j];
                array[i * 4 + j] = array[j * 4 + i];
                array[j * 4 + i] = swap;
            }
        }
        return array;
    }
    
    public static void main( String... args ) {
        float[] test = new float[] {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
            };
        int index = 0;
        for( int i = 0; i < 4; i++ ) {
            for( int j = 0; j < 4; j++ ) {
                System.out.print("[" + test[i * 4 + j] + "]");
            }
            System.out.println();
        }
 
        transpose(test);
                     
        System.out.println("transposed...");
        index = 0;
        for( int i = 0; i < 4; i++ ) {
            for( int j = 0; j < 4; j++ ) {
                System.out.print("[" + test[i * 4 + j] + "]");
            }
            System.out.println();
        }             
    }
 
    @Override   
    public String toString() {
        if( matrix == null ) {
            return getClass().getSimpleName() + "[]";
        }
        StringBuilder sb = new StringBuilder();
        for( float f : matrix ) {
            if( sb.length() > 0 ) {
                sb.append(", ");
            }
            sb.append(f);
        }
        return getClass().getSimpleName() + "[" + sb + "]";
    }
}
