package classfile.constant;

import classfile.CpInfo;
/**
4.5.1 The CONSTANT_Class_info Structure
The CONSTANT_Class_info structure is used to represent a class or an interface:
CONSTANT_Class_info {
u1 tag;
u2 name_index;
}
The items of the CONSTANT_Class_info structure are the following:
tag
The tag item has the value CONSTANT_Class (7).
name_index
The value of the name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index
must be a CONSTANT_Utf8_info (§4.5.7) structure representing a
valid fully qualified class or interface name encoded in internal
form (§4.3.1).
Because arrays are objects, the opcodes anewarray and multianewarray can
reference array “classes” via CONSTANT_Class_info (§4.5.1) structures in the
constant_pool table. For such array classes, the name of the class is the
descriptor of the array type. For example, the class name representing a twodimensional
int array type
int[][]
is
[[I
The class name representing the type array of class Thread
Thread[]
is
[Ljava/lang/Thread;
An array type descriptor is valid only if it represents 255 or fewer dimensions.
 */
public class ClassInfo extends CpInfo{
    {
        tag = CLASS;
    }
	public short nameIndex;
	
	public byte[] getInfo(){
		return new byte[]{(byte)((nameIndex>>8)&0xff), (byte)(nameIndex&0xff)}; 
	}

}