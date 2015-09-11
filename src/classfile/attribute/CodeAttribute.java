package classfile.attribute;

import classfile.*;
/*
4.8.3 The Code Attribute
The Code attribute is a variable-length attribute used in the attributes table of
method_info structures. A Code attribute contains the Java virtual machine
instructions and auxiliary information for a single method, instance initialization
method (§3.9), or class or interface initialization method (§3.9). Every Java virtual
machine implementation must recognize Code attributes. If the method is either
native or abstract, its method_info structure must not have a Code attribute.
Otherwise, its method_info structure must have exactly one Code attribute.
The Code attribute has the following format:
Code_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 max_stack;
u2 max_locals;
u4 code_length;
u1 code[code_length];
u2 exception_table_length;
{ u2 start_pc;
u2 end_pc;
u2 handler_pc;
u2 catch_type;
} exception_table[exception_table_length];
u2 attributes_count;
attribute_info attributes[attributes_count];
}
The items of the Code_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "Code".
attribute_length
The value of the attribute_length item indicates the length of
the attribute, excluding the initial six bytes.

 */
public class CodeAttribute extends AttributeInfo {
    /**
The value of the max_stack item gives the maximum depth
(§3.6.2) of the operand stack of this method at any point during
execution of the method.
     */
    public short maxStack;

    /**
The value of the max_locals item gives the number of local
variables in the local variable array allocated upon invocation of
this method, including the local variables used to pass parameters
to the method on its invocation.
The greatest local variable index for a value of type long or
double is max_locals−2. The greatest local variable index for a
value of any other type is max_locals−1.
     */
    public short maxLocals;

    /**
The value of the code_length item gives the number of bytes in
the code array for this method. The value of code_length must
be greater than zero; the code array must not be empty.
     */
    public int codeLength;

    /**
The code array gives the actual bytes of Java virtual machine
code that implement the method.
When the code array is read into memory on a byteaddressable
machine, if the first byte of the array is aligned on a
4-byte boundary, the tableswitch and lookupswitch 32-bit offsets
will be 4-byte aligned. (Refer to the descriptions of those
instructions for more information on the consequences of code
array alignment.)
The detailed constraints on the contents of the code array are
extensive and are given in a separate section (§4.10).
     */
    public byte[] code;//[codeLength];

    /**
The value of the exception_table_length item gives the
number of entries in the exception_table table.
     */
    public short exceptionTableLength;

    /**
exception_table[]
Each entry in the exception_table array describes one
exception handler in the code array. The order of the handlers in
the exception_table array is significant. See Section 3.10 for
more details.
Each exception_table entry contains the following four
items:
     */
    public ExceptionHandler[] exceptionTable;//[exceptionTableLength];

    /**
The value of the attributes_count item indicates the number
of attributes of the Code attribute.
     */
    public short attributesCount;

    /**
Each value of the attributes table must be an attribute structure
(§4.8). A Code attribute can have any number of optional
attributes associated with it.
Currently, the LineNumberTable (§4.8.12) and
LocalVariableTable (§4.8.13),attributes which contain
debugging information,
as well as the StackMapTable attribute (§4.8.4), are defined
and used with the Code attribute.
A Java virtual machine implementation is permitted to
silently ignore any or all attributes in the attributes table of a
Code attribute, except for the StackMapTable attribute.\, which
must be recognized if the class file version number is 50.0 or
above. Attributes not defined in this specification are not allowed
to affect the semantics of the class file, but only to provide
additional descriptive information (§4.8.1).
     */
    public AttributeInfo[] attributes;//[attributesCount];

    public static class ExceptionHandler {

/**
The values of the two items start_pc and end_pc indicate
the ranges in the code array at which the exception handler is
active. The value of start_pc must be a valid index into the
code array of the opcode of an instruction. The value of
end_pc either must be a valid index into the code array of the
opcode of an instruction or must be equal to code_length,
the length of the code array. The value of start_pc must be
less than the value of end_pc.
The start_pc is inclusive and end_pc is exclusive; that
is, the exception handler must be active while the program
counter is within the interval [start_pc, end_pc).4

 */
        public short startPc;

        public short endPc;

        /**
The value of the handler_pc item indicates the start of the
exception handler. The value of the item must be a valid
index into the code array and must be the index of the opcode
of an instruction.
         */
        public short handlerPc;

        /**
If the value of the catch_type item is nonzero, it must be a
valid index into the constant_pool table. The
constant_pool entry at that index must be a
CONSTANT_Class_info (§4.5.1) structure representing a
 * class of exceptions that this exception handler is designated
to catch. The exception handler will be called only if the
thrown exception is an instance of the given class or one of its
subclasses.
If the value of the catch_type item is zero, this
exception handler is called for all exceptions. This is used to
implement finally (see Section 7.13, “Compiling
finally”).
         */
        public short catchType;
    }
}
