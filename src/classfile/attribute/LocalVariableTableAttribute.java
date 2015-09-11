package classfile.attribute;

import classfile.*;
/**
4.8.13 The LocalVariableTable Attribute
The LocalVariableTable attribute is an optional variable-length attribute of a
Code (§4.8.3) attribute. It may be used by debuggers to determine the value of a
given local variable during the execution of a method. If LocalVariableTable
attributes are present in the attributes table of a given Code attribute, then they
may appear in any order. There may be no more than one LocalVariableTable
attribute per local variable in the Code attribute.
The LocalVariableTable attribute has the following format:
LocalVariableTable_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 local_variable_table_length;
{ u2 start_pc;
u2 length;
u2 name_index;
u2 descriptor_index;
u2 index;
} local_variable_table[
local_variable_table_length];
}
The items of the LocalVariableTable_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "LocalVariableTable".
attribute_length
The value of the attribute_length item indicates the length of
the attribute, excluding the initial six bytes.
 */
public class LocalVariableTableAttribute extends AttributeInfo {
    /**
The value of the local_variable_table_length item
indicates the number of entries in the local_variable_table
array.
     */
    public short localVariableTableLength;

    /**
Each entry in the local_variable_table array indicates a
range of code array offsets within which a local variable has a
value. It also indicates the index into the local variable array of
the current frame at which that local variable can be found. Each
entry must contain the following five items:
     */
    public LocalVariable[] localVariableTable;

    public static class LocalVariable {
        /**
The given local variable must have a value at indices into the
code array in the interval [start_pc, start_pc+length),
that is, between start_pc and start_pc+length
exclusive. The value of start_pc must be a valid index into
the code array of this Code attribute and must be the index
of the opcode of an instruction. The value of
start_pc+length must either be a valid index into the
code array of this Code attribute and be the index of the
opcode of an instruction, or it must be the first index beyond
the end of that code array.
         */
        public short startPc;
        public short length;



        /**
The value of the name_index item must be a valid index
into the constant_pool table. The constant_pool entry
at that index must contain a CONSTANT_Utf8_info (§4.5.7)
structure representing a valid unqualified name (§4.3.2)
denoting a local variable.
         */
        public short nameIndex;
        /**
The value of the descriptor_index item must be a valid
index into the constant_pool table. The constant_pool
entry at that index must contain a CONSTANT_Utf8_info
(§4.5.7) structure. That CONSTANT_Utf8_info structure must
represent a field descriptor (§4.4.2) encoding the type of a
local variable in the source program.
         */
        public short descriptorIndex;

        /**
The given local variable must be at index in the local
variable array of the current frame. If the local variable at
index is of type double or long, it occupies both index
and index+1.
         */
        public short index;
    }
}              
               
               