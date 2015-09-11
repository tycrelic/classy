package classfile.attribute;

import classfile.*;

/**
4.8.2 The ConstantValue Attribute
The ConstantValue attribute is a fixed-length attribute used in the attributes
table of the field_info (§4.6) structures. A ConstantValue attribute represents
the value of a constant field. There can be no more than one ConstantValue
attribute in the attributes table of a given field_info structure. If the field is
static (that is, the ACC_STATIC bit (Table 4.4) in the flags item of the field_info
structure is set) then the constant field represented by the field_info structure is
assigned the value referenced by its ConstantValue attribute as part of the initialization
of the class or interface declaring the constant field (§2.17.4). This
occurs immediately prior to the invocation of the class or interface initialization
method (§3.9) of that class or interface.
If a field_info structure representing a non-static field has a ConstantValue
attribute, then that attribute must silently be ignored. Every Java virtual machine
implementation must recognize ConstantValue attributes.
The ConstantValue attribute has the following format:
ConstantValue_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 constantvalue_index;
}
The items of the ConstantValue_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "ConstantValue".
attribute_length
The value of the attribute_length item of a
ConstantValue_attribute structure must be 2.
 */
public class ConstantValueAttribute extends AttributeInfo {
    {
        attributeLength = 2;
    }
    /**
     The value of the constantvalue_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index gives the constant value represented by this attribute.
The constant_pool entry must be of a type appropriate to the
field, as shown by Table 4.6.
     Table 4.6 Constant value attribute types
Field Type Entry Type
long CONSTANT_Long
float CONSTANT_Float
double CONSTANT_Double
int, short, char, byte, boolean CONSTANT_Integer
String CONSTANT_String

     */
    public short constantValueIndex;
}