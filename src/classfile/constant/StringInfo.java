package classfile.constant;

import classfile.CpInfo;

/**
4.5.3 The CONSTANT_String_info Structure
The CONSTANT_String_info structure is used to represent constant objects of the
type String:
CONSTANT_String_info {
u1 tag;
u2 string_index;
}
The items of the CONSTANT_String_info structure are as follows:
tag
The tag item of the CONSTANT_String_info structure has the
value CONSTANT_String (8).

 */
public class StringInfo extends CpInfo {

    {
        tag = STRING;
    }
    /**
    The value of the string_index item must be a valid index into
    the constant_pool table. The constant_pool entry at that
    index must be a CONSTANT_Utf8_info (§4.5.7) structure
    representing the sequence of characters to which the String
    object is to be initialized.
     */
    public short stringIndex;
}
