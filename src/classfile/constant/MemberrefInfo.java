package classfile.constant;

import classfile.CpInfo;

/**
4.5.2 The CONSTANT_Fieldref_info, CONSTANT_Methodref_info,
and CONSTANT_InterfaceMethodref_info Structures
Fields, methods, and interface methods are represented by similar structures:
CONSTANT_Fieldref_info {
u1 tag;
u2 class_index;
u2 name_and_type_index;
}
CONSTANT_Methodref_info {
u1 tag;
u2 class_index;
u2 name_and_type_index;
}
CONSTANT_InterfaceMethodref_info {
u1 tag;
u2 class_index;
u2 name_and_type_index;
}
The items of these structures are as follows:
tag
The tag item of a CONSTANT_Fieldref_info structure has the
value CONSTANT_Fieldref (9).
The tag item of a CONSTANT_Methodref_info structure has
the value CONSTANT_Methodref (10).
The tag item of a CONSTANT_InterfaceMethodref_info
structure has the value CONSTANT_InterfaceMethodref (11).
 */
public class MemberrefInfo extends CpInfo {

    /**
    The value of the class_index item must be a valid index into the
    constant_pool table. The constant_pool entry at that index
    must be a CONSTANT_Class_info (§4.5.1) structure representing
    a class or interface type that has the field or method as a member.
    The class_index item of a CONSTANT_Methodref_info
    structure must be a class type, not an interface type. The
    class_index item of a CONSTANT_InterfaceMethodref_info
    structure must be an interface type. The class_index item of a
    CONSTANT_Fieldref_info structure may be either a class type
    or an interface type.
     */
    public short classIndex;
    /**
    The value of the name_and_type_index item must be a valid
    index into the constant_pool table. The constant_pool entry
    at that index must be a CONSTANT_NameAndType_info (§4.5.6)
    structure. This constant_pool entry indicates the name and
    descriptor of the field or method. In a CONSTANT_Fieldref_info
    the indicated descriptor must be a field descriptor (§4.4.2).
    Otherwise, the indicated descriptor must be a method descriptor
    (§4.4.3).
    If the name of the method of a CONSTANT_Methodref_info
    structure begins with a '&lt;' ('\u003c'), then the name must be
    the special name <init>, representing an instance initialization
    method (§3.9). The return type of such a method must be
    void.
     */
    public short nameAndTypeIndex;
}
