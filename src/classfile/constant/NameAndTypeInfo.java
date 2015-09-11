package classfile.constant;

import classfile.CpInfo;
/**
4.5.6 The CONSTANT_NameAndType_info Structure
The CONSTANT_NameAndType_info structure is used to represent a field or
method, without indicating which class or interface type it belongs to:
CONSTANT_NameAndType_info {
u1 tag;
u2 name_index;
u2 descriptor_index;
}
The items of the CONSTANT_NameAndType_info structure are as follows:
tag
The tag item of the CONSTANT_NameAndType_info structure has
the value CONSTANT_NameAndType (12).
 */
public class NameAndTypeInfo extends CpInfo {

    {
        tag = NAMEANDTYPE;
    }
    /**
     The value of the name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index
must be a CONSTANT_Utf8_info (§4.5.7) structure representing
either the special method name &lt;init> (§3.9)(§3.9) or a valid
unqualified name (§4.3.2) denoting a field or method. .
     */
    public short nameIndex;
    /**
     The value of the descriptor_index item must be a valid index
into the constant_pool table. The constant_pool entry at that
index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing a valid field descriptor (§4.4.2) or method descriptor
(§4.4.3).
     */
    public short descriptorIndex;
}
