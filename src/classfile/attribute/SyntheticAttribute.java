package classfile.attribute;

import classfile.*;
/**
4.8.8 The Synthetic Attribute
The Synthetic attribute6 is a fixed-length attribute in the attributes table of
ClassFile (§4.2), field_info (§4.6), and method_info (§4.7) structures. A
class member that does not appear in the source code must be marked using a Synthetic
attribute, or else it must have its ACC_SYNTHETIC bit set. The only
exceptions to this requirement are for default constructors and the class initialization
method.
The Synthetic attribute has the following format:
Synthetic_attribute {
u2 attribute_name_index;
u4 attribute_length;
}
The items of the Synthetic_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "Synthetic".
attribute_length
The value of the attribute_length item is zero.

 */
public class SyntheticAttribute extends AttributeInfo {
    {
        attributeLength = 0;
    }
}