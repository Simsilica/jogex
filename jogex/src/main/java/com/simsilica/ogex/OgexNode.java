/*
 * $Id: OgexNode.java 3826 2014-11-23 08:01:12Z pspeed $
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

import java.util.*;

/**
 *
 *
 *  @author    Paul Speed
 */
public class OgexNode extends ArrayList<OgexNode> {
    private String name;
    private List<OgexTransform> transforms = new ArrayList<OgexTransform>();
    private Map<Integer, OgexAnimation> animations; 
    
    public OgexNode() {
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
 
    public void addTransform( OgexTransform o ) {
        transforms.add(o);
    }
 
    public List<OgexTransform> getTransforms() {
        return transforms;
    }
 
    public void addAnimation( OgexAnimation anim ) {
        if( animations == null ) {
            this.animations = new HashMap<Integer, OgexAnimation>();
        }
        this.animations.put(anim.getClip(), anim);
    }
    
    public OgexAnimation getAnimation( int clip ) {
        if( animations == null ) {
            return null;
        }
        return animations.get(clip);
    }
 
    public Collection<OgexAnimation> getAnimations() {
        return animations.values();
    }
 
    public Set<Integer> getAnimationClips() {
        return animations.keySet();
    }
    
    public void dumpTree( String indent ) {
        System.out.println(indent + toString());
        if( animations != null ) {
            for( Map.Entry<Integer, OgexAnimation> e : animations.entrySet() ) {
                System.out.println(indent + "    animation[" + e.getKey() + "]=" + e.getValue());
            }
        }
        for( OgexNode n : this ) {
            n.dumpTree(indent + "    ");
        }
    }
    
    protected void appendFieldStrings( StringBuilder sb ) {
        if( sb.length() > 0 ) {
            sb.append(", ");
        }
        sb.append("name=" + getName() + ", transforms=" + transforms + ", childCount=" + size());
        sb.append(", animationCount=" + (animations != null ? animations.size() : 0));        
    }
 
    @Override   
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        appendFieldStrings(sb);
        return getClass().getSimpleName() + "[" + sb + "]";
    }
    
}
