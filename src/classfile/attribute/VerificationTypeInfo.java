package classfile.attribute;

/**
The verification_type_info structure consists of a one-byte tag followed by
zero or more bytes, giving more information about the tag. Each
verification_type_info structure specifies the verification type of one or two
locations.
union verification_type_info {
Top_variable_info;
Integer_variable_info;
Float_variable_info;
Long_variable_info;
Double_variable_info;
Null_variable_info;
UninitializedThis_variable_info;
Object_variable_info;
Uninitialized_variable_info;
}
The Top_variable_info type indicates that the local variable has the
verification type top ( .)
Top_variable_info {
u1 tag = ITEM_Top; /* 0 * /
}
The Integer_variable_info type indicates that the location contains the
verification type int.
Integer_variable_info {
u1 tag = ITEM_Integer; /* 1 * /
}
The Float_variable_info type indicates that the location contains the
verification type float.
Float_variable_info {
u1 tag = ITEM_Float; /* 2 * /
}
The Long_variable_info type indicates that the location contains the
verification type long. If the location is a local variable, then:
• It must not be the local variable with the highest index.
• The next higher numbered local variable contains the verification type .
If the location is an operand stack entry, then:
• The current location must not be the topmost location of the operand stack.
• the next location closer to the top of the operand stack contains the verification type
.
This structure gives the contents of two locations in the operand stack or in the
local variables.
Long_variable_info {
u1 tag = ITEM_Long; /* 4 * /
}
The Double_variable_info type indicates that the location contains the
verification type double. If the location is a local variable, then:
• It must not be the local variable with the highest index.
• The next higher numbered local variable contains the verification type .
If the location is an operand stack entry, then:
• The current location must not be the topmost location of the operand stack.
• the next location closer to the top of the operand stack contains the verification type
.
This structure gives the contents of two locations in in the operand stack or in the
local variables.
Double_variable_info {
u1 tag = ITEM_Double; /* 3 * /
}
The Null_variable_info type indicates that location contains the verification
type null.
Null_variable_info {
u1 tag = ITEM_Null; /* 5 * /
}
The UninitializedThis_variable_info type indicates that the location
contains the verification type uninitializedThis.
UninitializedThis_variable_info {
u1 tag = ITEM_UninitializedThis; /* 6 * /
}
The Object_variable_info type indicates that the location contains an instance
of the class referenced by the constant pool entry.
Object_variable_info {
u1 tag = ITEM_Object; /* 7 * /
u2 cpool_index;
}
The Uninitialized_variable_info indicates that the location contains the
verification type uninitialized(offset). The offset item indicates the offset of
the new instruction that created the object being stored in the location.
Uninitialized_variable_info {
u1 tag = ITEM_Uninitialized /* 8 * /
u2 offset;
}
 */
public class VerificationTypeInfo {
    public static final byte ITEM_TOP = 0;
    public static final byte ITEM_INTEGER = 1;
    public static final byte ITEM_FLOAT = 2;
    public static final byte ITEM_DOUBLE = 3;
    public static final byte ITEM_LONG = 4;
    public static final byte ITEM_NULL = 5;
    public static final byte ITEM_UNINITIALIZED_THIS = 6;
    public static final byte ITEM_OBJECT = 7;
    public static final byte ITEM_UNINITIALIZED = 8;

    public byte tag;
    //public int cpoolIndex;
    //public int offset;
    public int cpoolIndex_offset;
}
