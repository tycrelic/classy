package classfile.attribute;

import classfile.*;
/**
4.8.7 The EnclosingMethod Attribute
The EnclosingMethod attribute is an optional fixed-length attribute in the
attributes table of the ClassFile (§4.2) structure. A class must have an
EnclosingMethod attribute if and only if it is a local class or an anonymous
class. A class may have no more than one EnclosingMethod attribute.
The EnclosingMethod attribute has the following format:
EnclosingMethod_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 class_index
u2 method_index;
}
The items of the EnclosingMethod_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "EnclosingMethod".
attribute_length
The value of the attribute_length item is four.

 */
public class EnclosingMethodAttribute extends AttributeInfo {
    {
        attributeLength = 4;
    }

    /**
The value of the class_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index
must be a CONSTANT_Class_info (§4.5.1) structure representing
the innermost class that encloses the declaration of the current
class.
     */
    public short classIndex;

    /**
If the current class is not immediately enclosed by a method or
constructor, then the value of the method_index item must be
zero. Otherwise, the value of the method_index item must be a
valid index into the constant_pool table. The constant_pool
entry at that index must be a CONSTANT_NameAndType_info
(§4.5.6) structure representing a the name and type of a method in
the class referenced by the class_index attribute above. It is the
responsibility of the Java compiler to ensure that the method
identified via the method_index is indeed the closest lexically
enclosing method of the class that contains this
EnclosingMethod attribute.
     */
    public short methodIndex;
}
