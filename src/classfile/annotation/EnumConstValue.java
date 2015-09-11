package classfile.annotation;
/**
The enum_const_value item is used if the tag item is 'e'. The
enum_const_value item consists of the following two items:
 */
public class EnumConstValue extends ElementValue {
    /**
The value of the type_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the binary name (JLS 13.1) of the
type of the enum constant represented by this element_value structure.
     */
    short typeNameIndex;

    /**
The value of the const_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the simple name of the enum constant
represented by this element_value structure.
     */
    short constNameIndex;
}
