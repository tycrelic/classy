package classfile.attribute;

import classfile.*;
/**
4.8.15 The Deprecated Attribute
The Deprecated attribute7 is an optional fixed-length attribute in the attributes
table of ClassFile (§4.2), field_info (§4.6), and method_info (§4.7) structures.
A class, interface, method, or field may be marked using a Deprecated
attribute to indicate that the class, interface, method, or field has been superseded. A
runtime interpreter or tool that reads the class file format, such as a compiler, can
use this marking to advise the user that a superseded class, interface, method, or
field is being referred to. The presence of a Deprecated attribute does not alter the
semantics of a class or interface.
The Deprecated attribute has the following format:
Deprecated_attribute {
u2 attribute_name_index;
u4 attribute_length;
}
The items of the Deprecated_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "Deprecated".
attribute_length
The value of the attribute_length item is zero.

7 The Deprecated attribute was introduced in JDK release 1.1 to support the @deprecated tag in documentation comments.
 */
public class DeprecatedAttribute extends AttributeInfo {
	//public short attributeNameIndex;
	//public int attributeLength=0;
    {
        attributeLength = 0;
    }
}              
