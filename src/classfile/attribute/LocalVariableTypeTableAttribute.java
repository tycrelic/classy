package classfile.attribute;

import classfile.*;
/**
4.8.14 The LocalVariableTypeTable Attribute
The LocalVariableTypeTable attribute is an optional variable-length attribute of
a Code (§4.8.3) attribute. It may be used by debuggers to determine the value of a
given local variable during the execution of a method. If LocalVariableTypeTable
attributes are present in the attributes table of a given Code attribute,
then they may appear in any order. There may be no more than one LocalVariableTypeTable
attribute per local variable in the Code attribute.
The LocalVariableTypeTable attribute differs from the LocalVariableTable
attribute in that it provides signature information rather than descriptor information.
This difference is only significant for variables whose type is a generic reference
type. Such variables will appear in both tables, while variables of other types will
appear only in LocalVariableTable.
The LocalVariableTypeTable attribute has the following format:
LocalVariableTypeTable_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 local_variable_type_table_length;
{ u2 start_pc;
u2 length;
u2 name_index;
u2 signature_index;
u2 index;
} local_variable_type_table[
local_variable_type_table_length];
}
The items of the LocalVariableTypeTable_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The
constant_pool entry at that index must be a
CONSTANT_Utf8_info (§4.5.7) structure representing the
string "LocalVariableTypeTable".
 */
public class LocalVariableTypeTableAttribute extends AttributeInfo {
    //public short attribute_nameIndex;
    //public int attributeLength;

    /**
The value of the
local_variable_type_table_length item indicates the
number of entries in the local_variable_table array.
     */
    public short line_numberTypeTableLength;

    /**
Each entry in the local_variable_table
local_variable_type_table array indicates a range of code
array offsets within which a local variable has a value. It also
indicates the index into the local variable array of the current
frame at which that local variable can be found. Each entry must
contain the following five items:
     */
    public short localVariableTableLength;
    public LocalVariableTable[] localVariableTable;//[localVariableTableLength];

    public static class LocalVariableTable {
        /**
The given local variable must have a value at indices into the
code array in the interval [start_pc, start_pc+length),
that is, between start_pc and start_pc+length
exclusive. The value of start_pc must be a valid index into
the code array of this Code attribute and must be the index
of the opcode of an instruction. The value
of start_pc+length must either be a valid index into the
code array of this Code attribute and be the index of the
opcode of an instruction, or it must be the first index beyond
the end of that code array array.
         */
        public short startPc;
        public short length;

        /**
The value of the name_index item must be a valid index
into the constant_pool table. The
constant_pool entry at that index must contain a
CONSTANT_Utf8_info (§4.5.7) structure representing
a valid unqualified name (§4.3.2) denoting a local
variable.
         */
        public short nameIndex;

        /**
The value of the signature_index item must be a valid
index into the constant_pool table. The constant_pool
entry at that index must contain a CONSTANT_Utf8_info
(§4.5.7) structure representing a field type signature (§4.4.4)
encoding the type of a local variable in the source program.
         */
        public short signatureIndex;

        /**
The given local variable must be at index in the local
variable array of the current frame. If the local variable at
index is of type double or long, it occupies both index
and index+1.
         */
        public short index;
    }
}              
               
               